package com.blinkist.data

import com.blinkist.data.local.BooksDao
import com.blinkist.data.mocks.expectedBook
import com.blinkist.data.remote.BooksApi
import com.blinkist.data.repository.BooksRepository
import com.blinkist.data.repository.BooksRepositoryImpl
import com.blinkist.data.utiles.mapToBook
import com.blinkist.data.utiles.mapToBookEntity
import com.blinkist.data.utiles.mapToBookResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class BooksRepositoryImplTest()  {

    private lateinit var booksApi: BooksApi
    private lateinit var booksDao: BooksDao
    private lateinit var booksRepository: BooksRepository

    @Before
    fun setUp() {
        booksApi = mockk()
        booksDao = mockk(relaxed = true)
        booksRepository = BooksRepositoryImpl(booksApi, booksDao)
    }

    @Test
    fun `getAllBooks should fetch from remote when local is empty`() = runTest {
        val mockRemoteBooks = listOf(expectedBook)
        val mockBookEntities = mockRemoteBooks.map { it.mapToBookEntity() }

        coEvery { booksDao.getAllBooks() } returns emptyList()
        coEvery { booksApi.getBooks() } returns mockRemoteBooks.map { it.mapToBookResponse() }
        coEvery { booksDao.insertBook(any()) } coAnswers { /* no-op */ }

        val result = booksRepository.getAllBooks()

        coVerify(exactly = 1) { booksApi.getBooks() }
        coVerify(exactly = mockRemoteBooks.size) { booksDao.insertBook(any()) }

        assertEquals(mockRemoteBooks.size, result.size)
    }
}