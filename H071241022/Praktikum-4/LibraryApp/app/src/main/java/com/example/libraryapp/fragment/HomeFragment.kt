package com.example.libraryapp.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.libraryapp.DataHelper
import com.example.libraryapp.DetailActivity
import com.example.libraryapp.R
import com.example.libraryapp.adapter.BookAdapter
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HomeFragment : Fragment() {

    private lateinit var adapter: BookAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var rvBooks: RecyclerView
    private var selectedGenre = "All"
    private var currentQuery = ""

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)
        rvBooks = view.findViewById(R.id.rvBooks)
        rvBooks.layoutManager = LinearLayoutManager(requireContext())

        adapter = BookAdapter(DataHelper.bookList) { book ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("bookId", book.id)
            startActivity(intent)
        }
        rvBooks.adapter = adapter

        // Filter button
        val btnFilter = view.findViewById<TextView>(R.id.btnFilter)
        btnFilter.setOnClickListener {
            showGenreDialog(btnFilter)
        }

        // SearchView
        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                currentQuery = newText ?: ""
                filterBooks()
                return true
            }
        })
    }

    private fun showGenreDialog(btnFilter: TextView) {
        val genres = listOf("All") + DataHelper.bookList.map { it.genre }.distinct()
        val genreArray = genres.toTypedArray()

        AlertDialog.Builder(requireContext())
            .setTitle("Filter by Genre")
            .setItems(genreArray) { _, which ->
                selectedGenre = genreArray[which]
                btnFilter.text = if (selectedGenre == "All") "All Genres ▼" else "$selectedGenre ▼"
                filterBooks()
            }
            .show()
    }

    private fun filterBooks() {
        progressBar.visibility = View.VISIBLE
        rvBooks.visibility = View.GONE

        executor.execute {
            Thread.sleep(500)

            val filtered = DataHelper.bookList.filter { book ->
                val matchesGenre = selectedGenre == "All" || book.genre == selectedGenre
                val matchesQuery = book.title.contains(currentQuery, ignoreCase = true)
                matchesGenre && matchesQuery
            }

            // Update UI di main thread
            mainHandler.post {
                adapter.updateList(filtered)
                progressBar.visibility = View.GONE
                rvBooks.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        filterBooks()
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdown()
    }
}