package com.blinkist.domain.utiles

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

val <T> Result<T>.data: T?
    get() {
        return (this as? Result.Success)?.data
    }