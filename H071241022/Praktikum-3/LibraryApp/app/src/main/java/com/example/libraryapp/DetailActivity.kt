package com.example.libraryapp

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bookId = intent.getIntExtra("bookId", -1)
        val book = DataHelper.bookList.find { it.id == bookId }

        book?.let { b ->
            val imgCover = findViewById<ImageView>(R.id.imgDetailCover)
            if (b.imageUri != null) {
                val file = java.io.File(b.imageUri)
                if (file.exists()) {
                    imgCover.setImageURI(Uri.fromFile(file))
                }
            } else {
                imgCover.setImageResource(b.coverImage)
            }

            findViewById<TextView>(R.id.tvDetailTitle).text = b.title
            findViewById<TextView>(R.id.tvDetailAuthor).text = "by ${b.author}"
            findViewById<TextView>(R.id.tvDetailYear).text = "📅 ${b.year}"
            findViewById<TextView>(R.id.tvDetailGenre).text = "🏷️ ${b.genre}"
            findViewById<TextView>(R.id.tvDetailRating).text = "⭐ ${b.rating} / 5.0"
            findViewById<TextView>(R.id.tvDetailPages).text = "📄 ${b.pages} pages"
            findViewById<TextView>(R.id.tvDetailPublisher).text = "🏢 ${b.publisher}"
            findViewById<TextView>(R.id.tvDetailBlurb).text = b.blurb

            val btnLike = findViewById<ImageView>(R.id.btnLike)
            updateLikeButton(btnLike, b.isLiked)

            btnLike.setOnClickListener {
                b.isLiked = !b.isLiked
                updateLikeButton(btnLike, b.isLiked)
                val msg = if (b.isLiked) "Added to favorites! ⭐" else "Removed from favorites"
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }
    }

    private fun updateLikeButton(btn: ImageView, isLiked: Boolean) {
        btn.setImageResource(
            if (isLiked) android.R.drawable.btn_star_big_on
            else android.R.drawable.btn_star_big_off
        )
    }
}