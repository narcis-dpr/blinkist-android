package com.blinkist.data.di

import com.blinkist.data.repository.BooksRepository
import com.blinkist.data.repository.BooksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBooksRepository(booksRepositoryImpl: BooksRepositoryImpl): BooksRepository
}