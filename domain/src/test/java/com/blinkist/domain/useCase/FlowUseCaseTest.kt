package com.blinkist.domain.useCase

import com.blinkist.data.repository.BooksRepository
import com.blinkist.domain.di.DefaultDispatcherProvider
import com.blinkist.domain.mock.getAListOfBooks
import com.blinkist.domain.useCase.GetAllBooksUseCase
import com.blinkist.domain.utiles.Result
import com.blinkist.domain.utiles.data
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FlowUseCaseTest {
    private lateinit var booksRepository: BooksRepository
    private lateinit var getAllBooksUseCase: GetAllBooksUseCase
    private val dispatcherProvider = DefaultDispatcherProvider()

    @Before
    fun setUp() {
        booksRepository = mockk()
        getAllBooksUseCase = GetAllBooksUseCase(booksRepository, dispatcherProvider)
    }

    @Test
    fun `execute should emit Loading and Success when books are fetched successfully`() = runTest {
        val mockBooks = getAListOfBooks()
        coEvery { booksRepository.getAllBooks() } returns mockBooks

        val results = getAllBooksUseCase(Unit).toList()

        assertEquals(2, results.size)
        assertTrue(results[0] is Result.Loading)
        assertTrue(results[1] is Result.Success && results[1].data == mockBooks)
    }

    @Test
    fun `execute should emit Loading and Error when repository throws an exception`() = runTest {
        val exception = Exception("Error fetching books")
        coEvery { booksRepository.getAllBooks() } throws exception

        val results = getAllBooksUseCase(Unit).toList()

        assertEquals(2, results.size)
        assertTrue(results[0] is Result.Loading)
        assertTrue(results[1] is Result.Error)
    }
}