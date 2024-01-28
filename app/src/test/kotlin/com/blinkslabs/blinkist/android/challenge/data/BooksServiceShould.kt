package com.blinkslabs.blinkist.android.challenge.data

import com.blinkslabs.blinkist.android.challenge.CoroutinesTestRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksServiceShould {

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    @Mock
    lateinit var booksApi: BooksApi

    @InjectMocks
    lateinit var booksService: BooksService

    private val mockBooks: List<Book> = listOf(mock(), mock(), mock())

    @Test
    fun callBooksApiWhenGetBooksIsCalled() = runTest {
        givenASuccessfulBooksApiCall(mockBooks)
        booksService.getBooks()
        verify(booksApi).getBooks()
    }

    @Test
    fun returnBooksApiOutputWhenGetBooksIsSuccessful() = runTest {
        givenASuccessfulBooksApiCall(mockBooks)
        assert(booksService.getBooks() == mockBooks)
    }

    @Test(expected = RuntimeException::class)
    fun propagateExceptionWhenGetBooksIsUnsuccesful() = runTest {
        givenAnUnsuccessfulBooksApiCall(RuntimeException("test"))
        booksService.getBooks()
    }

    private suspend fun givenASuccessfulBooksApiCall(result: List<Book>) {
        whenever(booksApi.getBooks()).thenReturn(result)
    }

    private suspend fun givenAnUnsuccessfulBooksApiCall(exception: Throwable) {
        whenever(booksApi.getBooks()).thenThrow(exception)
    }
}
