package com.example.libraryapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.libraryapp.DataHelper
import com.example.libraryapp.DetailActivity
import com.example.libraryapp.R
import com.example.libraryapp.adapter.BookAdapter

class FavoritesFragment : Fragment() {

    private lateinit var adapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvFavorites = view.findViewById<RecyclerView>(R.id.rvFavorites)
        rvFavorites.layoutManager = LinearLayoutManager(requireContext())

        adapter = BookAdapter(DataHelper.bookList.filter { it.isLiked }) { book ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("bookId", book.id)
            startActivity(intent)
        }
        rvFavorites.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        val favorites = DataHelper.bookList.filter { it.isLiked }
        adapter.updateList(favorites)

        val tvEmpty = view?.findViewById<TextView>(R.id.tvEmpty)
        tvEmpty?.visibility = if (favorites.isEmpty()) View.VISIBLE else View.GONE
    }
}