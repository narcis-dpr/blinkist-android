package com.blinkist.data.repository

import com.blinkist.core.model.Book

interface BooksRepository {
    suspend fun getAllBooks(isRefresh: Boolean): List<Book>
    suspend fun getBookById(id: String): Book
}