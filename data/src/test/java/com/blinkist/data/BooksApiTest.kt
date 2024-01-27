package com.blinkist.data

import com.blinkist.data.remote.BooksApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BooksApiTest {
    private lateinit var server: MockWebServer
    private lateinit var api: BooksApi

    @Before
    fun beforeEach() {
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApi::class.java)
    }

    @After
    fun afterEach() {
        server.shutdown()
    }
}
