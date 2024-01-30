package com.blinkist.booklist.viewModel.state

import com.blinkist.booklist.components.SortBy
import com.blinkist.core.model.Book

data class BookListState(
    val isLoading: Boolean = true,
    val books: Map<String, List<Book>> = emptyMap(),
    val sortBy: SortBy = SortBy.NAME,
    val error: String = "",
    val isRefreshing: Boolean = false,
)
