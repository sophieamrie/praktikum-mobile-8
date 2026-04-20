package com.example.libraryapp.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.libraryapp.DataHelper
import com.example.libraryapp.R
import com.example.libraryapp.model.Book
import java.io.File
import java.io.FileOutputStream

class AddBookFragment : Fragment() {

    private var savedImagePath: String? = null
    private lateinit var imgPreview: ImageView

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            savedImagePath = copyImageToInternal(uri)
            imgPreview.setImageURI(uri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgPreview = view.findViewById(R.id.imgCoverPreview)

        imgPreview.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        view.findViewById<Button>(R.id.btnAddBook).setOnClickListener {
            val title = view.findViewById<EditText>(R.id.etTitle).text.toString()
            val author = view.findViewById<EditText>(R.id.etAuthor).text.toString()
            val year = view.findViewById<EditText>(R.id.etYear).text.toString()
            val blurb = view.findViewById<EditText>(R.id.etBlurb).text.toString()
            val genre = view.findViewById<EditText>(R.id.etGenre).text.toString()

            if (title.isEmpty() || author.isEmpty() || year.isEmpty() || blurb.isEmpty()) {
                Toast.makeText(requireContext(), "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newBook = Book(
                id = System.currentTimeMillis().toInt(),
                title = title,
                author = author,
                year = year.toIntOrNull() ?: 2024,
                blurb = blurb,
                coverImage = R.drawable.ic_launcher_background,
                genre = genre.ifEmpty { "General" },
                rating = 0f,
                pages = 0,
                publisher = "",
                imageUri = savedImagePath
            )

            DataHelper.bookList.add(0, newBook)
            Toast.makeText(requireContext(), "Buku berhasil ditambahkan!", Toast.LENGTH_SHORT).show()

            view.findViewById<EditText>(R.id.etTitle).text.clear()
            view.findViewById<EditText>(R.id.etAuthor).text.clear()
            view.findViewById<EditText>(R.id.etYear).text.clear()
            view.findViewById<EditText>(R.id.etBlurb).text.clear()
            view.findViewById<EditText>(R.id.etGenre).text.clear()
            imgPreview.setImageResource(android.R.drawable.ic_menu_gallery)
            savedImagePath = null
        }
    }

    private fun copyImageToInternal(uri: Uri): String {
        val fileName = "book_${System.currentTimeMillis()}.jpg"
        val file = File(requireContext().filesDir, fileName)
        requireContext().contentResolver.openInputStream(uri)?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }
        return file.absolutePath
    }
}