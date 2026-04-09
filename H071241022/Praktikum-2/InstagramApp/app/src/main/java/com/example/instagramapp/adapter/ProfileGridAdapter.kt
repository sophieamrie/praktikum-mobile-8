package com.example.instagramapp.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramapp.R
import com.example.instagramapp.model.Post

class ProfileGridAdapter(
    private val posts: List<Post>,
    private val itemSize: Int,
    private val onClick: (Post) -> Unit
) : RecyclerView.Adapter<ProfileGridAdapter.GridViewHolder>() {

    inner class GridViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPost: ImageView = view.findViewById(R.id.imgGridPost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile_grid, parent, false)
        view.layoutParams.height = itemSize
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val post = posts[position]
        if (post.imageUri != null) {
            val file = java.io.File(post.imageUri)
            if (file.exists()) {
                holder.imgPost.setImageURI(Uri.fromFile(file))
            } else {
                holder.imgPost.setImageResource(post.postImage)
            }
        } else {
            holder.imgPost.setImageResource(post.postImage)
        }

        holder.itemView.setOnClickListener {
            onClick(post)
        }
    }

    override fun getItemCount() = posts.size
}