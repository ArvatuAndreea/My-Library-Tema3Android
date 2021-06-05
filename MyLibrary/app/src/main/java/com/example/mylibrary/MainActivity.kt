package com.example.mylibrary

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mylibrary.activities.LocalDataBaseActivity
import com.example.mylibrary.adapters.BookAdapter
import com.example.mylibrary.interfaces.OnBookItemClick
import com.example.mylibrary.models.Book
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.*

class MainActivity : AppCompatActivity() {
    var books: ArrayList<Book> = ArrayList<Book>()
    var adapter: BookAdapter? = null

    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            goToLocalDataBaseActivity()
        }

        swipeRefreshLayout = findViewById(R.id.srl_list)

    }

    private fun setUpRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.list)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this,
                RecyclerView.VERTICAL, false)

        books.clear()
        books.add(Book("Elantris", "Brandon Sanderson", "Elantris was once " +
                "a place of magic, and the immortal Elantrians were gods in the eyes of people, " +
                "with the divine ability to create and heal with a mere wave of a hand. Anyone in " +
                "Arelon had the potential to become an Elantrian through a magical transformation " +
                "known as the Shaod."))
        books.add(Book("Five feet apart", "Rachael Lippincott",
                "A pair of teenagers with cystic fibrosis meet in a hospital and fall " +
                        "in love, though their disease means they must avoid close physical contact." +
                        " A pair of teenagers with cystic fibrosis meet in a hospital and fall in " +
                        "love, though their disease means they must avoid close physical contact."))
        books.add(Book("The Keeper of Lost Things", "Ruth Hogan", "Once a " +
                "celebrated author of short stories now in his twilight years, Anthony Peardew has " +
                "spent half his life collecting lost objects, trying to atone for a promise broken " +
                "many years before. Realising he is running out of time, he leaves his house and " +
                "all its lost treasures to his assistant Laura, the one person he can trust to " +
                "fulfil his legacy and reunite the thousands of objects with their rightful owners. " +
                "But the final wishes of the 'Keeper of Lost Things' have unforeseen repercussions " +
                "which trigger a most serendipitous series of encounters..."))
        adapter = BookAdapter(books, OnBookItemClick { book -> Toast.makeText(this,
                "${book.title} ${book.author} ${book.description}", Toast.LENGTH_LONG).show() })
        recyclerView.adapter = adapter
    }

    fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    fun goToLocalDataBaseActivity() {
        val intent = Intent(this, LocalDataBaseActivity::class)
        startActivity(intent)
    }
}