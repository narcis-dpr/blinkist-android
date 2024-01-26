package com.blinkslabs.blinkist.android.challenge.data.api

import com.blinkslabs.blinkist.android.challenge.data.model.Book
import retrofit2.http.GET

interface BooksApi {

    @GET("books")
    suspend fun getBooks(): List<Book>
}
