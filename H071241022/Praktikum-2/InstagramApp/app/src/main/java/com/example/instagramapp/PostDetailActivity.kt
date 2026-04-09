package com.example.instagramapp

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PostDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        val imgComment = findViewById<ImageView>(R.id.imgCommentProfile)
        imgComment.clipToOutline = true
        imgComment.outlineProvider = android.view.ViewOutlineProvider.BACKGROUND

        val postId = intent.getIntExtra("postId", -1)

        val allPosts = DataHelper.homeFeedList +
                DataHelper.userPostsMap.values.flatten()

        val post = allPosts.find { it.id == postId }

        post?.let {
            // Gambar post
            if (it.imageUri != null) {
                val file = java.io.File(it.imageUri)
                if (file.exists()) {
                    findViewById<ImageView>(R.id.imgDetailPost).setImageURI(Uri.fromFile(file))
                }
            } else {
                findViewById<ImageView>(R.id.imgDetailPost).setImageResource(it.postImage)
            }

            // Profile
            findViewById<ImageView>(R.id.imgDetailProfile).setImageResource(it.profileImage)
            findViewById<TextView>(R.id.tvDetailUsername).text = it.username
            findViewById<TextView>(R.id.tvDetailCaption).text = "${it.username}  ${it.caption}"
            findViewById<TextView>(R.id.tvDetailLikes).text = "${it.likes} likes"
            findViewById<TextView>(R.id.tvDetailTime).text = it.timeAgo
        }

        findViewById<ImageView>(R.id.btnBackDetail).setOnClickListener { finish() }
    }
}