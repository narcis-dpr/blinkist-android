package com.blinkslabs.blinkist.android.challenge.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.blinkslabs.blinkist.android.challenge.CoroutinesTestRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelShould {

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    @Mock
    lateinit var booksService: BooksService

    @InjectMocks
    lateinit var viewModel: BooksViewModel

    private val mockBooks: List<Book> = listOf(mock(), mock(), mock())

    @Test
    fun callGetBooksOnServiceWhenFetchBooksIsCalled() = runTest {
        givenASuccessfulBooksServiceCall(mockBooks)
        viewModel.fetchBooks()
        verify(booksService).getBooks()
    }

    @Test
    fun showBooksOnViewWhenFetchBooksIsSuccessful() {
        givenASuccessfulBooksServiceCall(mockBooks)
        viewModel.fetchBooks()
        viewModel.books().observeForever {
            assertEquals(mockBooks, it)
        }
    }

    private fun givenASuccessfulBooksServiceCall(result: List<Book>) {
        runBlocking { whenever(booksService.getBooks()).thenReturn(result) }
    }
}
