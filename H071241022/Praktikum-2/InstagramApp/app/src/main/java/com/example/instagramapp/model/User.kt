package com.example.instagramapp.model

data class User (
    val id: Int,
    val username: String,
    val fullName: String,
    val bio: String,
    val profileImage: Int,
    val postCount: Int,
    val followerCount: Int,
    val followingCount: Int
)