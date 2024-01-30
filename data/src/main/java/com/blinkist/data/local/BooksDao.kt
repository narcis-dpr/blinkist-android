package com.blinkist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blinkist.data.model.BookEntity

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(bookEntity: BookEntity)

    @Query("SELECT * FROM Books ")
    suspend fun getAllBooks(): List<BookEntity>

    @Query("SELECT * FROM Books WHERE id = :id ")
    suspend fun getBookById(id: String): BookEntity

    @Delete
    suspend fun deleteBook(bookEntity: BookEntity)

}
