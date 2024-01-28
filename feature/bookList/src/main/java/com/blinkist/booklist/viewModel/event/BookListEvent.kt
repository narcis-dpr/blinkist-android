package com.blinkist.booklist.viewModel.event

sealed class BookListEvent {
    object Refresh : BookListEvent()
}
