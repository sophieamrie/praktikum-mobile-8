package com.example.instagramapp

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.instagramapp.model.Post
import java.io.File
import java.io.FileOutputStream

class UploadPostActivity : AppCompatActivity() {

    private var savedImagePath: String? = null

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            // Copy ke internal storage supaya bisa diakses activity lain
            savedImagePath = copyImageToInternal(this, uri)
            findViewById<ImageView>(R.id.imgUploadPreview).setImageURI(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_post)

        val imgPreview = findViewById<ImageView>(R.id.imgUploadPreview)
        val etCaption = findViewById<EditText>(R.id.etCaption)

        imgPreview.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        findViewById<Button>(R.id.btnShare).setOnClickListener {
            val caption = etCaption.text.toString()
            if (caption.isEmpty()) {
                Toast.makeText(this, "Caption tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (savedImagePath == null) {
                Toast.makeText(this, "Pilih foto dulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val currentUser = DataHelper.currentUser
            val userPosts = DataHelper.userPostsMap[currentUser.id] ?: mutableListOf()
            val newPost = Post(
                id = System.currentTimeMillis().toInt(),
                userId = currentUser.id,
                username = currentUser.username,
                profileImage = currentUser.profileImage,
                postImage = R.drawable.ic_launcher_background,
                caption = caption,
                likes = 0,
                timeAgo = "Just now",
                imageUri = savedImagePath
            )
            userPosts.add(0, newPost)
            DataHelper.userPostsMap[currentUser.id] = userPosts

            Toast.makeText(this, "Post berhasil diupload!", Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_OK)
            finish()
        }

        findViewById<ImageView>(R.id.btnBackUpload).setOnClickListener { finish() }
    }

    private fun copyImageToInternal(context: Context, uri: Uri): String {
        val fileName = "post_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)
        context.contentResolver.openInputStream(uri)?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }
        return file.absolutePath
    }
}