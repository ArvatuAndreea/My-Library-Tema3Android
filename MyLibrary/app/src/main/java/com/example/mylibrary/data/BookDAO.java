package com.example.mylibrary.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mylibrary.models.entities.BookItem;

import java.util.List;

@Dao
public interface BookDAO {
    @Query("SELECT * FROM bookItem")
    List<BookItem> getAll();

    @Insert
    void insertBook(BookItem bookItem);

    @Delete
    void delete(BookItem bookItem);

    @Query("DELETE FROM bookItem WHERE title = :itemTitle")
    void deleteByTitle(String itemTitle);

    @Update
    void update(BookItem bookItem);
}
