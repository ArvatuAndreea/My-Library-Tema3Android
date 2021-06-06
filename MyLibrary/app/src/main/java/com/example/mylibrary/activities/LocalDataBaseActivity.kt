package com.example.mylibrary.activities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylibrary.Constants.Companion.ARG_FB_BOOK
import com.example.mylibrary.R
import com.example.mylibrary.adapters.BookAdapter
import com.example.mylibrary.data.BookRepository
import com.example.mylibrary.models.BookItemElement
import com.example.mylibrary.models.entities.BookItemElementFB
import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx
import java.util.*

class LocalDataBaseActivity : AppCompatActivity() {
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

    private var bookAdapter: BookAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Firebase.database.reference

        setContentView(R.layout.activity_local_database)
        setupViews()
    }

    override fun onStart() {
        super.onStart()

        database.addValueEventListener(bookListener)
    }

    override fun onStop() {
        super.onStop()

        database.removeEventListener(bookListener)
    }

    private fun setupViews() {
        setupRecyclerView()

        button = findViewById(R.id.btn_add_update)
        editTextTitle = findViewById(R.id.editable_title)
        editTextAuthor = findViewById(R.id.editable_author)
        editTextDescription = findViewById(R.id.editable_description)
        button?.setOnClickListener {
            insertBook()
        }
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.rv_books)
        val layoutManager: LinearLayoutManager =
                LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.LayoutManager = layoutManager

        bookAdapter = BookAdapter(bookList)
        recyclerView.adapter = bookAdapter
    }

    fun getBooks() {
        database.child(ARG_FB_BOOK).get().addOnSuccessListener { dataSnapshot ->
            Log.i("firebase", "Got value ${dataSnapshot.value}")
            bookList.clear()

            dataSnapshot.children.forEach { itemSnapshot ->
                val bookItemFB = itemSnapshot.getValue(BookItemElementFB::class)
                bookItemFB?.convert()?.let { bookItem ->
                    bookList.add(bookItem.convert())
                }
            }

            bookAdapter?.notifyDataSetChanged()
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    private fun insertBook() {
        val title = editTextTitle?.text?.toString() ?: return
        when (title.isEmpty()) {
            true -> {
                editTextTitle?.error = getString(R.string.error_required)
                return
            }
            false -> editTextTitle?.error = null
        }

        val author = editTextAuthor?.text?.toString() ?: return
        when (author.isEmpty()) {
            true -> {
                editTextAuthor?.error = getString(R.string.error_required)
                return
            }

            false -> editTextDescription?.error = null
        }

        val description = editTextDescription?.text?.toString() ?: return
        when (description.isEmpty()) {
            true -> {
                editTextDescription?.error = getString(R.string.error_required)
                return
            }

            false -> editTextDescription?.error = null
        }

        val bookItemFB = BookItemElementFB(
                title,
                author,
                description
        )

        database.child(ARG_FB_BOOK).push().setValue(bookItemFB)
    }



    val bookListener = object : ValueEventListener {
        fun onDataChange(dataSnapshot: DataSnapshot) {
            bookList.clear()

            val booksSnapshot = dataSnapshot.child(ARG_FB_BOOK)
            booksSnapshot?.children?.forEach { itemSnapshot ->
                val bookItemFB = itemSnapshot.getValue(BookItemElementFB::class)
                bookItemFB?.convert()?.let { bookItem ->
                    bookList.add(bookItem.convert())
                }
            }

            bookAdapter?.notifyDataSetChanged()
            // ...
        }

        fun onCancelled(databaseError: DatabaseError) {
            // Getting Post failed, log a message
            // Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
        }
    }
}