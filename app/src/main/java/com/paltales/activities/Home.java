package com.paltales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.paltales.R;

/*
This is the home activity where the users choses what they wanna see wether it's movies or books
 */
public class Home extends AppCompatActivity {
    private ImageButton moviesbtn;
    private ImageButton booksbtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        initialize();
    }

    private void initialize() {
        setBooksbtn(findViewById(R.id.books));
        setMoviesbtn(findViewById(R.id.movies));
        /*
            If the books is clicked redirect user to the movies List
            If the movies is clicked redircet user to the books list
            & sent type of the work (book or movie) as extra string
         */
        handle_books(getBooksbtn());
        handle_movies(getMoviesbtn());
    }

    private void handle_movies(ImageButton moviesbtn) {
        moviesbtn.setOnClickListener(e->{
            Intent intent = new Intent(this, ListActivity.class);
            intent.putExtra("choice", "movies");
            startActivity(intent);
        });
    }

    private void handle_books(ImageButton booksbtn) {
        booksbtn.setOnClickListener(e->{
            Intent intent = new Intent(this, ListActivity.class);
            intent.putExtra("choice", "books");
            startActivity(intent);
        });
    }

    /*
    Getters & Setters
     */
    public ImageButton getMoviesbtn() {
        return moviesbtn;
    }
    public void setMoviesbtn(ImageButton moviesbtn) {
        this.moviesbtn = moviesbtn;
    }
    public ImageButton getBooksbtn() {
        return booksbtn;
    }
    public void setBooksbtn(ImageButton booksbtn) {
        this.booksbtn = booksbtn;
    }
}