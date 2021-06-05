package com.example.mylibrary.data;

import com.example.mylibrary.data.tasks.InsertBookTask;
import com.example.mylibrary.models.ApplicationController;
import com.example.mylibrary.models.entities.BookItem;

import java.util.List;

public class BookRepository {
    public static interface OnSuccesListener {
        void onSuccess();
    }

    public static interface OnGetBookListener {
        void onSuccess(List<BookItem> items);
    }

    private BookDataBase bookDataBase;

    public BookRepository(){
        bookDataBase = ApplicationController.getBookDataBase();
    }

    public void insertBook(BookItem, OnSuccesListener listener) {
        new InsertBookTask(bookDataBase, listener).execute();
    }
}
