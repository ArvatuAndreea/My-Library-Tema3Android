package com.example.mylibrary.activities

import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.mylibrary.data.BookRepository
import com.example.mylibrary.models.BookItemElement
import com.google.firebase.database.DatabaseReference
import java.util.*

class LocalDataBaseActivity : AppCompatActivity {
    private var button: Button? = null
    private var editTextTitle: EditText? = null
    private var editTextAuthor: EditText? = null
    private var editTextDescription: EditText? = null
    private val bookRepository  = BookRepository()
    private var progressBar: ProgressBar? = null

    private val bookList by lazy {
        ArrayList<BookItemElement>()
    }

    private lateinit var database: DatabaseReference
    
}