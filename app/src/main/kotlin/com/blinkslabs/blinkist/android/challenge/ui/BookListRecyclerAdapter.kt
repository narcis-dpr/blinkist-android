package com.blinkslabs.blinkist.android.challenge.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.blinkslabs.blinkist.android.challenge.R
import com.blinkslabs.blinkist.android.challenge.data.model.Book

class BookListRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = ArrayList<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.book_list_item, parent, false)
        return BookViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val bookHolder = holder as BookViewHolder
        val book = items[position]

        bookHolder.titleTextView.text = book.name
        bookHolder.authorTextView.text = book.author

        bookHolder.coverImageView.load(book.coverImageUrl)
    }

    override fun getItemCount() = items.size

    fun setItems(items: List<Book>) {
        this.items.clear()
        this.items.addAll(items)
    }

    private class BookViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var authorTextView: TextView = v.findViewById(R.id.authorTextView)
        var titleTextView: TextView = v.findViewById(R.id.titleTextView)
        var coverImageView: ImageView = v.findViewById(R.id.coverImageView)
    }
}
