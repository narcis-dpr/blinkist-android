package com.blinkist.domain.useCase

import com.blinkist.core.model.Book
import com.blinkist.data.repository.BooksRepository
import com.blinkist.domain.di.DefaultDispatcherProvider
import com.blinkist.domain.utiles.FlowUseCase
import com.blinkist.domain.utiles.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAllBooksUseCase @Inject constructor(
    private val booksRepository: BooksRepository,
    dispatcher: DefaultDispatcherProvider
) : FlowUseCase<Unit, List<Book>>(dispatcher.io) {
    override fun execute(parameters: Unit): Flow<Result<List<Book>>> = flow {
        try {
            emit(Result.Loading)
            val result = booksRepository.getAllBooks()
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e))
        } catch (e: IOException) {
            emit(Result.Error(e))
        }
    }
}