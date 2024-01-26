package com.blinkslabs.blinkist.android.challenge.data.api

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class BooksApiModule {

    @Provides
    fun getBooksApi(): BooksApi = Retrofit.Builder()
        .baseUrl("https://64956449b08e17c917920ab2.mockapi.io/")
        .build()
        .create(BooksApi::class.java)
}
