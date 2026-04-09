package com.example.instagramapp.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramapp.R
import com.example.instagramapp.model.Post

class FeedAdapter (
    private val posts: List<Post>,
    private val onProfileClick: (Post) -> Unit,
    private val onPostClick: (Post) -> Unit
) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    inner class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProfile: ImageView = view.findViewById(R.id.imgProfile)
        val tvUsername: TextView = view.findViewById(R.id.tvUsername)
        val imgPost: ImageView = view.findViewById(R.id.imgPost)
        val tvLikes: TextView = view.findViewById(R.id.tvLikes)
        val tvCaption: TextView = view.findViewById(R.id.tvCaption)
        val tvTime: TextView = view.findViewById(R.id.tvTime)
        val layoutProfile: View = view.findViewById(R.id.layoutProfile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feed, parent, false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val post = posts[position]
        holder.tvUsername.text = post.username
        holder.tvLikes.text = "${post.likes} likes"
        holder.tvCaption.text = "${post.username}  ${post.caption}"
        holder.tvTime.text = post.timeAgo
        holder.imgProfile.setImageResource(post.profileImage)
        holder.imgProfile.clipToOutline = true
        holder.imgProfile.outlineProvider = android.view.ViewOutlineProvider.BACKGROUND

        if (post.imageUri != null) {
            holder.imgPost.setImageURI(android.net.Uri.parse(post.imageUri))
        } else {
            holder.imgPost.setImageResource(post.postImage)
        }

        holder.layoutProfile.setOnClickListener { onProfileClick(post) }
        holder.imgPost.setOnClickListener { onPostClick(post) }
    }
    override fun getItemCount() = posts.size
}