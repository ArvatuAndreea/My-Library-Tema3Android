package com.example.mylibrary.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.mylibrary.models.Book;

@Entity
public class BookItem {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "author")
    public String author;
    @ColumnInfo(name = "description")
    public String description;

    public BookItem(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }


}
