package com.blinkslabs.blinkist.android.challenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blinkslabs.blinkist.android.challenge.data.BooksService
import javax.inject.Inject

class BooksViewModelFactory @Inject constructor(
    private val booksService: BooksService
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = BooksViewModel(booksService) as T
}
