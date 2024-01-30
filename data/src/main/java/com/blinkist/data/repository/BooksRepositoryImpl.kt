package com.blinkist.data.repository

import android.util.Log
import com.blinkist.core.model.Book
import com.blinkist.data.local.BooksDao
import com.blinkist.data.remote.BooksApi
import com.blinkist.data.utiles.mapToBook
import com.blinkist.data.utiles.mapToBookEntity
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val booksApi: BooksApi,
    private val booksDao: BooksDao,
) : BooksRepository {
    override suspend fun getAllBooks(): List<Book> {
        val remoteResult = booksApi.getBooks()
            try {
                val bookEntities = remoteResult.map { it.mapToBook().mapToBookEntity() }
                bookEntities.forEach {
                    booksDao.insertBook(it)
                }
                return bookEntities.map { it.mapToBook() }
            } catch (e: Exception) {
                Log.e("Repository", e.message.toString())
            }
        val localBooks = booksDao.getAllBooks()
        return localBooks.map { it.mapToBook() }
    }

    override suspend fun getBookById(id: String): Book {
        return booksDao.getBookById(id).mapToBook()
    }


}