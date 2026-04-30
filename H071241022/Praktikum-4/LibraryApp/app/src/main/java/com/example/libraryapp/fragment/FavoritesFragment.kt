package com.example.libraryapp.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.libraryapp.DataHelper
import com.example.libraryapp.DetailActivity
import com.example.libraryapp.R
import com.example.libraryapp.adapter.BookAdapter
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoritesFragment : Fragment() {

    private lateinit var adapter: BookAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var rvFavorites: RecyclerView
    private lateinit var tvEmpty: TextView

    private val executor: ExecutorService = Executors.newSingleThreadExecutor()
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)
        rvFavorites = view.findViewById(R.id.rvFavorites)
        tvEmpty = view.findViewById(R.id.tvEmpty)

        rvFavorites.layoutManager = LinearLayoutManager(requireContext())
        adapter = BookAdapter(emptyList()) { book ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("bookId", book.id)
            startActivity(intent)
        }
        rvFavorites.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        loadFavorites()
    }

    private fun loadFavorites() {
        progressBar.visibility = View.VISIBLE
        rvFavorites.visibility = View.GONE
        tvEmpty.visibility = View.GONE

        // Background thread untuk load data
        executor.execute {
            Thread.sleep(800)

            val favorites = DataHelper.bookList.filter { it.isLiked }

            // Update UI di main thread
            mainHandler.post {
                adapter.updateList(favorites)
                progressBar.visibility = View.GONE

                if (favorites.isEmpty()) {
                    tvEmpty.visibility = View.VISIBLE
                    rvFavorites.visibility = View.GONE
                } else {
                    tvEmpty.visibility = View.GONE
                    rvFavorites.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdown()
    }
}