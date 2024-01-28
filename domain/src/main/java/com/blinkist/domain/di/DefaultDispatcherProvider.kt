package com.blinkist.domain.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultDispatcherProvider {
    val io: CoroutineDispatcher get() = Dispatchers.IO
}