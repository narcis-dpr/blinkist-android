package com.blinkslabs.blinkist.android.challenge.data

import com.blinkslabs.blinkist.android.challenge.data.api.BooksApi
import com.blinkslabs.blinkist.android.challenge.data.model.Book
import javax.inject.Inject

class BooksService @Inject constructor(private val booksApi: BooksApi) {

    suspend fun getBooks(): List<Book> {
        // TODO getBooks from API
        return booksApi.getBooks()
    }
}
