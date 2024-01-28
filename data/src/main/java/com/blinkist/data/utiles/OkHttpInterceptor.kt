package com.blinkist.data.utiles

import android.util.Log
import okhttp3.*
import okio.Buffer
import java.io.IOException
import java.lang.StringBuilder
import java.util.concurrent.TimeUnit
import kotlin.Throws
class OkHttpInterceptor(
    private var showHeaders: Boolean,
    private var showLongResponsesInChunks: Boolean,
    private var showAuthorizationTokenInOkHttpLogs: Boolean,
) : Interceptor {
    private val maxLogSize = 3200

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val t1 = System.nanoTime()
        val logFilter: Int = chain.hashCode()
        val sbRequest = StringBuilder()
        sbRequest.append("\u2800\n-->\n").append("Request:\n")
        sbRequest.append("Filter Its Response With: ").append(LOG_TAG).append(logFilter)
            .append("\n\n")
        if (showHeaders) {
            sbRequest.append("Headers:").append("\n")
            for (headerName in request.headers.names()) {
                sbRequest.append(headerName).append(":").append(request.header(headerName))
                    .append("\n")
            }
            sbRequest.append("\n")
        }
        sbRequest.append(request.method).append("\n")
        sbRequest.append(request.url).append("\n")
        if (request.method != "GET") {
            val bodyStr = bodyToString(request.body)
            if (bodyStr.length < maxLogSize) {
                sbRequest.append("\nRequest Body:\n").append(bodyToString(request.body))
            } else {
                val chunks = getChunks(bodyStr.length)
                val contentLength = bodyStr.length
                val bodySize = "$contentLength-bytes"
                val firstChunk = bodyStr.substring(0, maxLogSize)
                sbRequest.append("Request Body:\n").append(firstChunk)
                    .append("\n\nREQUEST BODY IS TOO LONG")
                    .append(" (").append(bodySize).append(") ")
                    .append("- Printed only 1 in ").append(chunks)
                    .append(" Parts\n")
            }
        }
        if (showAuthorizationTokenInOkHttpLogs) {
            val authorizationToken = request.header("Authorization")
            sbRequest.append("\n").append("Authorization Header:\n").append(authorizationToken)
        }
        sbRequest.append(line)
            .append("\u2800").append("\n")
        Log.d(LOG_TAG, sbRequest.toString())
        val response: Response = chain.proceed(request)
        val time = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - t1)
        val responseBody = response.peekBody(Long.MAX_VALUE)
        val contentLength = responseBody.contentLength()
        var bodySize = ""
        bodySize = if (contentLength != -1L) {
            "$contentLength-byte"
        } else {
            "unknown-length"
        }
        var responseBodyString = responseBody.string()
        if (responseBodyString.isEmpty()) {
            responseBodyString = ""
        }
        val sbResponse = StringBuilder()
        sbResponse.append("\u2800\n<--\n").append("Response:\n")
        sbResponse.append("Filter Its Request With: ").append(LOG_TAG).append(logFilter)
            .append("\n\n")
        sbResponse.append("Response Code: ").append(response.code).append("\n")
        sbResponse.append(response.request.method).append("\n").append(response.request.url)
            .append("\n\n")
        sbResponse.append("Response Time: ").append(time).append("ms").append("\n")
        sbResponse.append("Response Body Size: ").append(bodySize).append("\n\n")
        if (contentLength < maxLogSize) {
            sbResponse.append("Response Body:\n").append(responseBodyString)
            sbResponse.append(line)
                .append("\u2800").append("\n")
            Log.d(LOG_TAG, sbResponse.toString())
        } else {
            val contentLengthInt = responseBodyString.length
            val chunks = getChunks(contentLengthInt)
            if (showLongResponsesInChunks) {
                showLongResponseInChunks(
                    logFilter,
                    bodySize,
                    responseBodyString,
                    sbResponse,
                    maxLogSize,
                    contentLengthInt,
                    chunks,
                )
            } else {
                showOnlyOneLineInLongResponse(
                    bodySize,
                    responseBodyString,
                    sbResponse,
                    maxLogSize,
                    chunks,
                )
            }
        }
        return response
    }

    private fun getChunks(contentLengthInt: Int): Int {
        return contentLengthInt / maxLogSize
    }

    private fun showLongResponseInChunks(
        logFilter: Int,
        bodySize: String,
        responseBodyString: String,
        sbResponse: StringBuilder,
        maxLogSize: Int,
        contentLengthInt: Int,
        chunks: Int,
    ) {
        sbResponse.append("Response Body:\n")
            .append("RESPONSE IS TOO LONG")
            .append(" (").append(bodySize).append(") ")
            .append("- Printing in ").append(chunks)
            .append(" parts: ")
            .append("\n\n")
        Log.d(LOG_TAG, sbResponse.toString())
        Log.d(
            LOG_TAG,
            tagCreate(logFilter),
        )
        for (i in 0..chunks) {
            val start = i * maxLogSize
            var end = (i + 1) * maxLogSize
            end = Math.min(end, contentLengthInt)
            val bodyChunk = LOG_TAG + logFilter + ": " +
                responseBodyString.substring(start, end)
            Log.d(LOG_TAG, bodyChunk)
        }
        Log.d(
            LOG_TAG,
            tagCreate(logFilter),
        )
    }

    private fun showOnlyOneLineInLongResponse(
        bodySize: String,
        responseBodyString: String,
        sbResponse: StringBuilder,
        maxLogSize: Int,
        chunks: Int,
    ) {
        val firstChunk = responseBodyString.substring(0, maxLogSize)
        sbResponse.append("Response Body:\n").append(firstChunk)
            .append("\n\nRESPONSE IS TOO LONG")
            .append(" (").append(bodySize).append(") ")
            .append("- Printed only 1 in ").append(chunks)
            .append(" Parts ")
            .append(line)
            .append("\u2800").append("\n")
        Log.d(LOG_TAG, sbResponse.toString())
    }

    private fun bodyToString(request: RequestBody?): String {
        return try {
            val buffer = Buffer()
            if (request != null) request.writeTo(buffer) else return "- EMPTY -"
            buffer.readUtf8()
        } catch (e: IOException) {
            "requestBodyToString Error!"
        }
    }

    private infix fun tagCreate(logFilter: Int) = """
    ---------------------- Start Of $LOG_TAG$logFilter Response Body -----------------------
    """
    companion object {
        private const val LOG_TAG = "EkOkHttp"
    }
}

private const val line =
    "\n_________________________________________________________________________________________________\n"
