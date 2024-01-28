package com.blinkist.data.utiles

import com.blinkist.core.model.Book
import com.blinkist.data.model.BookEntity
import com.blinkist.data.model.BookResponse

fun BookEntity.mapToBook() = Book(id, name, author, publishDate, coverImageUrl)

fun Book.mapToBookEntity() = BookEntity(id, name, author, publishDate, coverImageUrl)

fun BookResponse.mapToBook() = Book(id, name, author, publishDate, coverImageUrl)

fun Book.mapToBookResponse() = BookResponse(id, name, author, publishDate, coverImageUrl)