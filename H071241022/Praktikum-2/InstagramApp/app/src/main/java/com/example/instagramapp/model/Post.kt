package com.example.instagramapp.model

data class Post (
    val id: Int,
    val userId: Int,
    val username: String,
    val profileImage: Int,
    val postImage: Int,
    val caption: String,
    val likes: Int,
    val timeAgo: String,
    val imageUri: String? = null
)