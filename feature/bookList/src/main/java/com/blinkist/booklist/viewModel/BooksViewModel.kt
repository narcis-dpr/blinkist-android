package com.blinkist.booklist.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blinkist.booklist.components.SortBy
import com.blinkist.booklist.viewModel.event.BookListEvent
import com.blinkist.booklist.viewModel.state.BookListState
import com.blinkist.core.model.Book
import com.blinkist.domain.useCase.GetAllBooksUseCase
import com.blinkist.domain.utiles.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.threeten.bp.temporal.WeekFields
import java.util.Locale
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

            is BookListEvent.OnChangeSortType -> {
                state = state.copy(
                    books =  state.books.reGroupBooks(event.sortBy)
                    ,
                    sortBy = event.sortBy
                )
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
                                books = when (state.sortBy) {
                                    SortBy.NAME -> {
                                        books.sortedBy { it.name }.groupBooksByWeek(state.sortBy)
                                    }

                                    SortBy.PublishDate -> {
                                        books.sortedBy { it.publishDate }.groupBooksByWeek(state.sortBy)
                                    }
                                },
                                isLoading = false,
                            )
                        }
                    }
                }
            }
        }
    }
    private fun List<Book>.groupBooksByWeek(sortBy: SortBy): Map<String, List<Book>> {
        val weekField = WeekFields.of(Locale.getDefault()).weekOfYear()
        return if (sortBy == SortBy.PublishDate) {
            this.groupBy { book ->
                book.publishDate.get(weekField).toString()
            }
        } else {
            this.groupBy { book ->
                book.name.first().uppercase()
            }
        }
    }
    private fun Map<String, List<Book>>.reGroupBooks(sortBy: SortBy): Map<String, List<Book>> {
        return when (sortBy) {
            SortBy.NAME -> {
                this.values.flatten().groupBy { book ->
                    book.name.first().toString()
                }
            }
            SortBy.PublishDate -> {
                this.values.flatten().groupBy { book ->
                    book.publishDate.toString()
                }
            }
            // Implement other cases as needed
        }
    }
}
