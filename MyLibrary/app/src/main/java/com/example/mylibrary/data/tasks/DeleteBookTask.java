package com.example.mylibrary.data.tasks;

import android.os.AsyncTask;

import com.example.mylibrary.data.BookDataBase;
import com.example.mylibrary.data.BookRepository;
import com.example.mylibrary.models.entities.BookItem;

public class DeleteBookTask extends AsyncTask<BookItem,Void,Void> {
    private BookDataBase bookDataBase;
    private BookRepository.OnSuccesListener listener;

    public DeleteBookTask(BookDataBase bookDataBase, BookRepository.OnSuccesListener listener) {
        this.bookDataBase = bookDataBase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(BookItem... bookItems) {
        bookDataBase.bookDAO().delete(bookItems[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
