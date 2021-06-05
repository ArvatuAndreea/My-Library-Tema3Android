package com.example.mylibrary.data.tasks;

import android.os.AsyncTask;

import com.example.mylibrary.data.BookDataBase;
import com.example.mylibrary.data.BookRepository;
import com.example.mylibrary.models.entities.BookItem;

import java.util.List;

public class GetAllBooksTask extends AsyncTask<Void,Void, List<BookItem>> {
   private BookDataBase bookDataBase;
   private BookRepository.OnSuccesListener listener;

    public GetAllBooksTask(BookDataBase bookDataBase, BookRepository.OnSuccesListener listener) {
        this.bookDataBase = bookDataBase;
        this.listener = listener;
    }

    @Override
    protected List<BookItem> doInBackground(Void... voids) {
        return bookDataBase.bookDAO().getAll();
    }

    @Override
    protected void onPostExecute(List<BookItem> bookItems) {
        super.onPostExecute(bookItems);
        listener.onSuccess();
    }
}
