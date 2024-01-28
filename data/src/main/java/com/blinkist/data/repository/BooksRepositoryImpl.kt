package com.blinkist.data.repository

import com.blinkist.core.model.Book
import com.blinkist.data.local.BooksDao
import com.blinkist.data.remote.BooksApi
import com.blinkist.data.utiles.mapToBook
import com.blinkist.data.utiles.mapToBookEntity
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val booksApi: BooksApi,
    private val booksDao: BooksDao
) : BooksRepository {
    override suspend fun getAllBooks(): List<Book> {
        val result = booksDao.getAllBooks().map { it.mapToBook() }
        val remoteResult = booksApi.getBooks()
        if (remoteResult.isNotEmpty()) {
            remoteResult.forEach { booksDao.insertBook(it.mapToBook().mapToBookEntity()) }
        }
        return result
    }

    override suspend fun getBookById(id: String): Book {
        return booksDao.getBookById(id).mapToBook()
    }
}