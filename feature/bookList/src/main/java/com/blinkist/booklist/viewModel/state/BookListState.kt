package com.blinkist.booklist.viewModel.state

import com.blinkist.booklist.components.SortBy
import com.blinkist.booklist.components.SortOrder
import com.blinkist.core.model.Book

data class BookListState(
    val isLoading: Boolean = false,
    val books: List<Book> = emptyList(),
    val sortOder: SortOrder = SortOrder.ASCENDING,
    val sortBy: SortBy = SortBy.PublishDate,
    val error: String = "",
    val isRefreshing: Boolean = false,
)
