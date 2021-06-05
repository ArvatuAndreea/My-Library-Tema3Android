package com.example.mylibrary.data;

import com.example.mylibrary.data.tasks.DeleteBookTask;
import com.example.mylibrary.data.tasks.DeleteByTitleBookTask;
import com.example.mylibrary.data.tasks.GetAllBooksTask;
import com.example.mylibrary.data.tasks.InsertBookTask;
import com.example.mylibrary.data.tasks.UpdateBookTask;
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

    public void insertBook(BookItem bookItem, OnSuccesListener listener) {
        new InsertBookTask(bookDataBase, listener).execute(bookItem);
    }

    public void getAllBooks(OnGetBookListener listener) {
        new GetAllBooksTask(bookDataBase, listener).execute();
    }

    public void updateBook(BookItem bookItem, OnSuccesListener listener) {
        new UpdateBookTask(bookDataBase, listener).execute(bookItem);
    }

    public void deleteBook(BookItem bookItem, OnSuccesListener listener) {
        new DeleteBookTask(bookDataBase, listener).execute(bookItem);
    }

    public void deleteByTitleBook(String bookItem, OnSuccesListener listener) {
        new DeleteByTitleBookTask(bookDataBase, listener).execute(bookItem);
    }
}
