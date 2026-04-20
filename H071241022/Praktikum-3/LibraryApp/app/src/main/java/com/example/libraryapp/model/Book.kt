package com.example.libraryapp.model

data class Book (
    val id: Int,
    val title: String,
    val author: String,
    val year: Int,
    val blurb: String,
    val coverImage: Int,
    val genre: String,
    val rating: Float,
    val pages: Int,
    val publisher: String,
    var isLiked: Boolean = false,
    val imageUri: String? = null
)