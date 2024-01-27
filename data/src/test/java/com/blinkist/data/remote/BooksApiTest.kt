package com.blinkist.data.remote

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BooksApiTest {

    @get:Rule
    val server = MockWebServer()
    private lateinit var api: BooksApi

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val bookService by lazy {
        retrofit.create(BooksApi::class.java)
    }

    @After
    fun afterEach() {
        server.shutdown()
    }
}
