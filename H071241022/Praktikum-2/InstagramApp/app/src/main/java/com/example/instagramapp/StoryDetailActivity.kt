package com.example.instagramapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StoryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_detail)

        val storyId = intent.getIntExtra("storyId", -1)
        val storyTitle = intent.getStringExtra("storyTitle") ?: "Story"

        // Cari story dari semua highlight
        val allStories = DataHelper.highlightMap.values.flatten()
        val story = allStories.find { it.id == storyId }

        story?.let {
            findViewById<ImageView>(R.id.imgStoryDetail).setImageResource(it.coverImage)
        }
        findViewById<TextView>(R.id.tvStoryDetailTitle).text = storyTitle
        findViewById<ImageView>(R.id.btnCloseStory).setOnClickListener { finish() }
    }
}