package com.example.instagramapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramapp.adapter.FeedAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvFeed = findViewById<RecyclerView>(R.id.rvFeed)
        rvFeed.layoutManager = LinearLayoutManager(this)

        val adapter = FeedAdapter(
            posts = DataHelper.homeFeedList,
            onProfileClick = { post ->
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("userId", post.userId)
                startActivity(intent)
            },
            onPostClick = { post ->
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("postId", post.id)
                startActivity(intent)
            }
        )
        rvFeed.adapter = adapter
    }
}