package com.blinkslabs.blinkist.android.challenge.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.blinkslabs.blinkist.android.challenge.BlinkistChallengeApplication
import com.blinkslabs.blinkist.android.challenge.R
import com.blinkslabs.blinkist.android.challenge.data.model.Book
import javax.inject.Inject

class BooksActivity : AppCompatActivity() {

    @Inject lateinit var booksViewModelFactory: BooksViewModelFactory

    private val viewModel by viewModels<BooksViewModel> { booksViewModelFactory }

    private lateinit var recyclerAdapter: BookListRecyclerAdapter

    private lateinit var recyclerView : RecyclerView
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        (application as BlinkistChallengeApplication).component.inject(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = BookListRecyclerAdapter()
        recyclerView.adapter = recyclerAdapter

        swipeRefreshLayout.setOnRefreshListener { viewModel.fetchBooks() }

        viewModel.books().observe(this, Observer { books ->
            showBooks(books)
            hideLoading()
        })

        showLoading()
        viewModel.fetchBooks()
    }

    private fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    private fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    private fun showBooks(books: List<Book>) {
        recyclerAdapter.setItems(books)
        recyclerAdapter.notifyDataSetChanged()
        swipeRefreshLayout.isRefreshing = false
    }
}
