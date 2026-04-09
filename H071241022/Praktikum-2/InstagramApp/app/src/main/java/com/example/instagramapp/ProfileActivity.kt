package com.example.instagramapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramapp.adapter.ProfileGridAdapter
import com.example.instagramapp.adapter.StoryAdapter

class ProfileActivity : AppCompatActivity() {

    private lateinit var rvGrid: RecyclerView
    private lateinit var tvPostCount: TextView

    private val uploadLauncher = registerForActivityResult(
        androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val posts = DataHelper.userPostsMap[DataHelper.currentUser.id] ?: mutableListOf()
            val screenWidth = resources.displayMetrics.widthPixels
            val itemSize = screenWidth / 3
            rvGrid.adapter = ProfileGridAdapter(posts, itemSize) { post ->
                val intent = Intent(this, PostDetailActivity::class.java)
                intent.putExtra("postId", post.id)
                startActivity(intent)
            }
            tvPostCount.text = posts.size.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val userId = intent.getIntExtra("userId", -1)
        val user = if (userId == -1) {
            DataHelper.currentUser
        } else {
            DataHelper.userList.find { it.id == userId } ?: DataHelper.currentUser
        }

        val isMyProfile = user.id == DataHelper.currentUser.id

        // Toolbar
        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }
        findViewById<TextView>(R.id.tvToolbarUsername).text = user.username

        // Profile Info
        tvPostCount = findViewById(R.id.tvPostCount)
        findViewById<TextView>(R.id.tvFullName).text = user.fullName
        findViewById<TextView>(R.id.tvBio).text = user.bio
        findViewById<TextView>(R.id.tvFollowers).text = user.followerCount.toString()
        findViewById<TextView>(R.id.tvFollowing).text = user.followingCount.toString()

        val imgPhoto = findViewById<ImageView>(R.id.imgProfilePhoto)
        imgPhoto.setImageResource(user.profileImage)
        imgPhoto.clipToOutline = true
        imgPhoto.outlineProvider = android.view.ViewOutlineProvider.BACKGROUND

        // Posts
        val posts = DataHelper.userPostsMap[user.id] ?: mutableListOf()
        tvPostCount.text = posts.size.toString()

        // Highlights
        val rvHighlights = findViewById<RecyclerView>(R.id.rvHighlights)
        rvHighlights.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val highlights = DataHelper.highlightMap[user.id] ?: mutableListOf()
        rvHighlights.adapter = StoryAdapter(highlights) { story ->
            val intent = Intent(this, StoryDetailActivity::class.java)
            intent.putExtra("storyId", story.id)
            intent.putExtra("storyTitle", story.title)
            startActivity(intent)
        }

        // Grid Posts
        rvGrid = findViewById(R.id.rvProfilePosts)
        val screenWidth = resources.displayMetrics.widthPixels
        val itemSize = screenWidth / 3

        rvGrid.layoutManager = GridLayoutManager(this, 3)
        rvGrid.isNestedScrollingEnabled = false
        rvGrid.isFocusable = false
        rvGrid.addItemDecoration(object : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: android.graphics.Rect,
                view: android.view.View,
                parent: androidx.recyclerview.widget.RecyclerView,
                state: androidx.recyclerview.widget.RecyclerView.State
            ) {
                outRect.set(1, 1, 1, 1)
            }
        })

        rvGrid.adapter = ProfileGridAdapter(posts, itemSize) { post ->
            val intent = Intent(this, PostDetailActivity::class.java)
            intent.putExtra("postId", post.id)
            startActivity(intent)
        }

        // FAB Upload hanya untuk akun sendiri
        val fabUpload = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabUpload)
        if (isMyProfile) {
            fabUpload.show()
            fabUpload.setOnClickListener {
                uploadLauncher.launch(Intent(this, UploadPostActivity::class.java))
            }
        } else {
            fabUpload.hide()
        }
    }
}