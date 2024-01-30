@file:Suppress("DEPRECATION")

package com.blinkist.booklist.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blinkist.booklist.components.BookItem
import com.blinkist.booklist.components.SortBy
import com.blinkist.booklist.components.SortSectionHeader
import com.blinkist.booklist.viewModel.BooksViewModel
import com.blinkist.booklist.viewModel.event.BookListEvent
import com.blinkist.booklist.viewModel.state.BookListState
import com.blinkist.theme.LightPurple
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
    val books = remember {
        mutableStateOf(state.books)
    }
    val sortType = remember {
        mutableStateOf(SortBy.NAME)
    }
    val loadingVisibility = remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = state) {
        loadingVisibility.value = state.isLoading
        books.value = state.books
        sortType.value = state.sortBy
    }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
            SortSectionHeader(
                sortBy = sortType.value,
                onChangeSortBy = {viewModel.onEvent(BookListEvent.OnChangeSortType(it))},
                )
        AnimatedVisibility(
            visible = loadingVisibility.value,
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


            Spacer(modifier = Modifier.height(4.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
               books.value.forEach { (week, booksInWeek) ->
                   item {
                       Text("${sortType.value} : $week", style = MaterialTheme.typography.labelMedium)
                   }
                   items(booksInWeek) {book ->
                       BookItem(book = book, onItemClick = {})
                   }
               }
            }
        }
    }
}