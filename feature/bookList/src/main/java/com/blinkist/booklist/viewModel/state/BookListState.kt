package com.blinkist.booklist.viewModel.state

import com.blinkist.core.model.Book

data class BookListState(
    val isLoading: Boolean = false,
    val books: List<Book> = emptyList(),
    val error: String = "",
    val isRefreshing: Boolean = false,
)
