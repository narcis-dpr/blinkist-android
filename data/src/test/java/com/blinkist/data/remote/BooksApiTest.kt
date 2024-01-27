package com.blinkist.data.remote

import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BooksApiTest {
    private val testJson = """{"name": "Eat, Move, Sleep",
    "author": "Tom Rath",
    "publish_date": "2018-07-03T01:00:00.000Z",
    "cover_image_url": "https://images.blinkist.com/images/books/5694b3802f6827000700002a/3_4/640.jpg",
    "id": "d241b2b"}"""

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

    @Test
    fun `get books json from success server should return success`() = runTest {
        server.enqueue(
            MockResponse().setBody(testJson).setResponseCode(200),
        )
        val response = bookService.getBooks()
        Assert.assertNotNull(response)
        Assert.assertEquals("/books.json", server.takeRequest().path)
    }

    @After
    fun afterEach() {
        server.shutdown()
    }
}
