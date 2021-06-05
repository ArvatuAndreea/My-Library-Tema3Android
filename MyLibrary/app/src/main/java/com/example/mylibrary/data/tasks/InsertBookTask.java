package com.example.mylibrary.data.tasks;

import android.os.AsyncTask;

import com.example.mylibrary.data.BookDataBase;
import com.example.mylibrary.data.BookRepository;
import com.example.mylibrary.models.entities.BookItem;

public class InsertBookTask extends AsyncTask<BookItem,Void,Void> {
    private BookDataBase bookDataBase;
    private BookRepository.OnSuccesListener listener;

    public InsertBookTask(BookDataBase bookDataBase, BookRepository.OnSuccesListener listener) {
        this.bookDataBase = bookDataBase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(BookItem... bookItems) {
        bookDataBase.bookDAO().insertBook(bookItems[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
