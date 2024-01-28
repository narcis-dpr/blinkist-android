package com.blinkist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blinkist.data.model.BookEntity

@Database(
    entities = [BookEntity::class],
    version = 1,
)
abstract class BooksDatabase : RoomDatabase() {
    abstract val booksDao: BooksDao
}
