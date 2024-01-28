package com.blinkist.booklist.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.blinkist.booklist.components.BookItem
import com.blinkist.booklist.viewModel.BooksViewModel
import com.blinkist.booklist.viewModel.event.BookListEvent
import com.blinkist.booklist.viewModel.state.BookListState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun BookListScreen(
    viewModel: BooksViewModel = hiltViewModel()
) {
    val state: BookListState = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing,
    )
    var loadingVisibility by remember { mutableStateOf(viewModel.state.isLoading) }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        AnimatedVisibility(
            visible = loadingVisibility,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.onEvent(BookListEvent.Refresh)
            },
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.books) { book ->
                    BookItem(book = book, onItemClick = {})
                }
            }
        }
    }
}