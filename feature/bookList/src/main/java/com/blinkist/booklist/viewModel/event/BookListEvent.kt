package com.blinkist.booklist.viewModel.event

import com.blinkist.booklist.components.SortBy

sealed class BookListEvent {
    object Refresh : BookListEvent()
    data class OnChangeSortType(val sortBy: SortBy): BookListEvent()
}
