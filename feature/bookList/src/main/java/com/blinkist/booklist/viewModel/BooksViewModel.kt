package com.blinkist.booklist.viewModel

import androidx.lifecycle.ViewModel
import com.blinkist.domain.useCase.GetAllBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase
): ViewModel() {

}