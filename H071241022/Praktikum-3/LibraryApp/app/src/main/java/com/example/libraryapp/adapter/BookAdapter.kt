package com.example.libraryapp.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.libraryapp.R
import com.example.libraryapp.model.Book

class BookAdapter(
    private var books: List<Book>,
    private val onClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgCover: ImageView = view.findViewById(R.id.imgCover)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvAuthor: TextView = view.findViewById(R.id.tvAuthor)
        val tvYear: TextView = view.findViewById(R.id.tvYear)
        val tvGenre: TextView = view.findViewById(R.id.tvGenre)
        val tvRating: TextView = view.findViewById(R.id.tvRating)
        val imgLike: ImageView = view.findViewById(R.id.imgLike)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        holder.tvTitle.text = book.title
        holder.tvAuthor.text = book.author
        holder.tvYear.text = book.year.toString()
        holder.tvGenre.text = book.genre
        holder.tvRating.text = "⭐ ${book.rating}"

        if (book.imageUri != null) {
            val file = java.io.File(book.imageUri)
            if (file.exists()) {
                holder.imgCover.setImageURI(Uri.fromFile(file))
            } else {
                holder.imgCover.setImageResource(book.coverImage)
            }
        } else {
            holder.imgCover.setImageResource(book.coverImage)
        }

        holder.imgLike.setImageResource(
            if (book.isLiked) android.R.drawable.btn_star_big_on
            else android.R.drawable.btn_star_big_off
        )

        holder.itemView.setOnClickListener { onClick(book) }
    }

    override fun getItemCount() = books.size

    fun updateList(newBooks: List<Book>) {
        books = newBooks
        notifyDataSetChanged()
    }
}