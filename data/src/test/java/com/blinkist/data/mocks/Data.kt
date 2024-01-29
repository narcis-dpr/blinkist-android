package com.blinkist.data.mocks

import com.blinkist.core.model.Book
import com.blinkist.data.utiles.toDateComponents
import org.threeten.bp.LocalDate

val expectedBook = Book(
    id = "1",
    name =" title of book",
    author = "myself",
    publishDate = "2018-06-19T01:00:00.000Z".toDateComponents(),
    coverImageUrl = "https://images.blinkist.com/images/books/5694b3802f6827000700002a/3_4/640.jpg"
)
val bookOne = expectedBook.copy(name = "harry potter", id = "2")
val bookTwo = expectedBook.copy(name = "little women", id = "3")
val bookThree = expectedBook.copy(name = "the oldman and the sea", id = "4")

fun getAListOfBooks() = listOf(
    bookOne,
    bookTwo,
    bookThree
)

val expectedException = TestException("Test Exception")

data class TestException(
    override val message: String? = null
) : Exception(message)