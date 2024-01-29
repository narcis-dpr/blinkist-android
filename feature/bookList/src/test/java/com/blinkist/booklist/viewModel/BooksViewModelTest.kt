package com.blinkist.booklist.viewModel

import com.blinkist.booklist.viewModel.event.BookListEvent
import com.blinkist.domain.useCase.GetAllBooksUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import com.blinkist.domain.utiles.Result
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.blinkist.booklist.mockData.getAListOfBooks
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BooksViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var getAllBooksUseCase: GetAllBooksUseCase
    private lateinit var booksViewModel: BooksViewModel

    @Before
    fun setUp() {
        getAllBooksUseCase = mockk()
        booksViewModel = BooksViewModel(getAllBooksUseCase)
    }

    @Test
    fun `when getAllBooksUseCase returns Success, state should have books`() = runTest {
        val mockBooks = getAListOfBooks()
        coEvery { getAllBooksUseCase(Unit) } returns flow { emit(Result.Success(mockBooks)) }

        booksViewModel.onEvent(BookListEvent.Refresh)

        assertEquals(mockBooks, booksViewModel.state.books)
        assertEquals(false, booksViewModel.state.isLoading)
    }

    @Test
    fun `when getAllBooksUseCase returns Error, state should have error`() = runTest{
        val errorMessage = "Error fetching books"
        coEvery { getAllBooksUseCase(Unit) } returns flow { emit(Result.Error(Exception(errorMessage))) }

        booksViewModel.onEvent(BookListEvent.Refresh)

        assertEquals(errorMessage, booksViewModel.state.error)
        assertEquals(false, booksViewModel.state.isLoading)
    }
}