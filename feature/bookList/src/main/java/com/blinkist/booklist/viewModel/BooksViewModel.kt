package com.blinkist.booklist.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blinkist.booklist.components.SortOrder
import com.blinkist.booklist.viewModel.event.BookListEvent
import com.blinkist.booklist.viewModel.state.BookListState
import com.blinkist.domain.useCase.GetAllBooksUseCase
import com.blinkist.domain.utiles.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase,
) : ViewModel() {
    var state by mutableStateOf(BookListState())

    init {
        getAllBooks()
    }

    fun onEvent(event: BookListEvent) {
        when (event) {
            BookListEvent.Refresh -> {
                getAllBooks()
            }
        }
    }

    private fun getAllBooks() {
        viewModelScope.launch {
            getAllBooksUseCase(Unit).collect { result ->
                when (result) {
                    is Result.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.exception.toString(),
                        )
                    }

                    Result.Loading -> {
                        state = state.copy(isLoading = true)
                    }

                    is Result.Success -> {
                        result.data.let { books ->
                            state = state.copy(
                                books = when (state.sortOder) {
                                    SortOrder.ASCENDING -> books.sortedBy { state.sortOder.toString() }
                                    SortOrder.DESCENDING -> books.sortedByDescending { state.sortOder.toString() }
                                },
                                isLoading = false,
                            )
                        }
                    }
                }
            }
        }
    }
}
