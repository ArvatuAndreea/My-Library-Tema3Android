package com.example.mylibrary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylibrary.R;
import com.example.mylibrary.interfaces.OnBookItemClick;
import com.example.mylibrary.models.Book;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Book> bookList;
    OnBookItemClick onBookItemClick;

    public MyAdapter(ArrayList<Book> bookList, OnBookItemClick onBookItemClick) {
        this.bookList = bookList;
        this.onBookItemClick = onBookItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cell, parent, false);
        BookViewHolder bookViewHolder = new BookViewHolder(view);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Book book = (Book)bookList.get(position);
        ((BookViewHolder) holder).bind(book);
    }

    @Override
    public int getItemCount() {
        return this.bookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView author;
        private TextView description;
        private View view;

        BookViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            author = view.findViewById(R.id.author);
            description = view.findViewById(R.id.description);
            this.view = view;
        }

        void bind(Book book) {
            title.setText(book.getTitle());
            author.setText(book.getAuthor());
            description.setText(book.getDescription());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onBookItemClick != null) {
                        onBookItemClick.onClick(book);
                    }
                }
            });
        }
    }
}
