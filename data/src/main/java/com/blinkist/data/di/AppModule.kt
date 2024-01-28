package com.blinkist.data.di

import android.content.Context
import com.blinkist.data.remote.BooksApi
import com.blinkist.data.utiles.Constants.BASE_URL
import com.blinkist.data.utiles.OkHttpInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                addInterceptor(
                    OkHttpInterceptor(
                        showHeaders = true,
                        showLongResponsesInChunks = true,
                        showAuthorizationTokenInOkHttpLogs = true
                    )
                )
            }.hostnameVerifier(HostnameVerifier { hostname, session -> true })
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofitConnection(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBookApi(retrofit: Retrofit): BooksApi =
        retrofit.create(BooksApi::class.java)

}