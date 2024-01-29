package com.blinkist.data.repository

import com.blinkist.data.local.BooksDao
import com.blinkist.data.remote.BooksApi
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class BooksRepositoryImplTest()  {
    private lateinit var cut: BooksRepositoryImpl

    @MockK
    private lateinit var localDataSource: BooksDao

    @MockK
    private lateinit var remoteDataSource: BooksApi

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        cut = BooksRepositoryImpl(remoteDataSource, localDataSource)
    }
    @Test
    fun `get books when list from repository is empty`() = runTest {
       coEvery { localDataSource.getAllBooks() } returns emptyList()
       cut.getAllBooks().forEach { assertEquals(it, null) }

    }
}