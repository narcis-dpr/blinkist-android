package com.blinkist.data.utiles

import com.blinkist.core.model.Book
import com.blinkist.data.model.BookEntity
import com.blinkist.data.model.BookResponse
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

fun BookEntity.mapToBook() = Book(id, name, author, publishDate.toDateComponents(), coverImageUrl)

fun Book.mapToBookEntity() = BookEntity(id, name, author, publishDate.toString(), coverImageUrl)

fun BookResponse.mapToBook() = Book(id, name, author, publishDate.toDateComponents(), coverImageUrl)


private fun String.toDateComponents(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    return LocalDate.parse(this, formatter)
}