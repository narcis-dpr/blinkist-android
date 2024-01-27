package com.blinkist.data.remote

import com.blinkist.data.model.BookResponse
import retrofit2.http.GET

interface BooksApi {
    @GET("books")
    suspend fun getBooks(): List<BookResponse>
}
