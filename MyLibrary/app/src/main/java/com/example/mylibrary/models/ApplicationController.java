package com.example.mylibrary.models;

import android.app.Application;

import androidx.room.Room;

import com.example.mylibrary.data.BookDataBase;

public class ApplicationController extends Application {
    private static ApplicationController instance;
    private static BookDataBase bookDataBase;

    private final String bookDataBaseName = "BookDB";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setupDataBase();
    }

    private void setupDataBase() {
        bookDataBase = Room.databaseBuilder(
                getApplicationContext(),
                BookDataBase.class,
                bookDataBaseName)
               //.addMigrations((BookDataBase.MIGRATION_2_3)
                .build();

    }

    public static ApplicationController getInstance() {
        return instance;
    }

    public static BookDataBase getBookDataBase() {
        return bookDataBase;
    }
}
