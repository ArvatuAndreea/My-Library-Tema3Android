package com.example.mylibrary.data.tasks;

import android.os.AsyncTask;

import com.example.mylibrary.data.BookDataBase;
import com.example.mylibrary.data.BookRepository;

public class DeleteByTitleBookTask extends AsyncTask<String,Void,Void> {
   private BookDataBase bookDataBase;
   private BookRepository.OnSuccesListener listener;

    public DeleteByTitleBookTask(BookDataBase bookDataBase, BookRepository.OnSuccesListener listener) {
        this.bookDataBase = bookDataBase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(String... titles) {
        bookDataBase.bookDAO().deleteByTitle(titles[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
