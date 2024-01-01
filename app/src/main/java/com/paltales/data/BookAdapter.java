package com.paltales.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.paltales.R;
import com.paltales.model.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    /*
        In this class I create a custom Array Adapter for the books listveiw
        Inspired by:
            https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
     */
    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Book book = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_list_item, parent, false);
        }

        ImageView bookCoverImageView = convertView.findViewById(R.id.bookCover);
        TextView titleTextView = convertView.findViewById(R.id.bookTitle);
        TextView authorTextView = convertView.findViewById(R.id.bookAuthor);
        TextView linkTextView = convertView.findViewById(R.id.bookLink);

        if (book != null) {
            Picasso.get().load(book.getCover()).fit().into(bookCoverImageView);

            titleTextView.setText(book.getTitle());
            authorTextView.setText(book.getAuthor());
            linkTextView.setText(book.getKey());
        }
        return convertView;
    }
}