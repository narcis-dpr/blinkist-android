package com.blinkist.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blinkist.data.local.BooksDao
import com.blinkist.data.local.BooksDatabase
import com.blinkist.data.local.MIGRATIONS
import com.blinkist.data.utiles.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideBooksDatabase(@ApplicationContext context: Context)
            : BooksDatabase {
        return Room.databaseBuilder(
            context,
            BooksDatabase::class.java,
            DATABASE_NAME
        ).addMigrations(*MIGRATIONS)
            .build()
    }

    @Provides
    @Singleton
    fun provideBooksTable(booksDatabase: BooksDatabase): BooksDao =
        booksDatabase.booksDao
}