package com.paltales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.paltales.R;
import com.paltales.data.BookAPI;
import com.paltales.data.MovieAPI;
import com.paltales.data.MovieAdapter;
import com.paltales.model.Book;
import com.paltales.data.BookAdapter;
import com.paltales.model.Movie;

import java.util.List;

/*
     As part of reducing the code redunduncy
     here comes this class to be used for both types of
     works (movies & books) and based on the type diffrent list items are used
 */
public class ListActivity extends AppCompatActivity {
    private ListView list;
    private Button other;
    List<Book> books;
    List<Movie> movies;
    BookAPI bookApiHandler;
    MovieAPI movieApiHandler;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        initialize();
    }

    private void initialize() {
        setOther(findViewById(R.id.other));
        setList(findViewById(R.id.list));

        String type = getIntent().getStringExtra("choice");
        if(type != null) {
            set_list_data(type);
            if (type.equals("books")) {
                getOther().setText(getResources().getString(R.string.see_mov));
            } else if (type.equals("movies")) {
                getOther().setText(getResources().getString(R.string.see_books));
            }
            handle_other(getOther(), type);
        }
    }

    private void set_list_data(String type) {
        /*
            In this method the list shown is based on the choice of the user
         */
        if (type.equals("books")) {
            bookApiHandler = new BookAPI(this);
            bookApiHandler.getItems(new BookAPI.BookAPII() {
                @Override
                public void onSuccess(List<Book> books) {
                    BookAdapter listAdapter = new BookAdapter(ListActivity.this, books);
                    getList().setAdapter(listAdapter);
                    setBooks(books);
                }

                @Override
                public void onError(String errorMessage) {
                    Log.d("error", errorMessage);
                }
            });
        }else if(type.equals("movies")){
            movieApiHandler = new MovieAPI(this);
            movieApiHandler.getItems(new MovieAPI.MovieAPII() {
                @Override
                public void onSuccess(List<Movie> movies) {
                    MovieAdapter listAdapter = new MovieAdapter(ListActivity.this, movies);
                    getList().setAdapter(listAdapter);
                    setMovies(movies);
                }

                @Override
                public void onError(String errorMessage) {
                    Log.d("error", errorMessage);
                }
            });
        }
    }

    private void handle_other(Button other, String type) {
        other.setOnClickListener(v->{
            /*
                Like when they click other they can show the other list
             */
            if(type.equals("movies")){
                Intent intent = new Intent(this, ListActivity.class);
                intent.putExtra("choice", "books");
                startActivity(intent);
            }else if(type.equals("books")) {
                Intent intent = new Intent(this, ListActivity.class);
                intent.putExtra("choice", "movies");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(bookApiHandler != null)
            bookApiHandler.clearQueue();
        if(movieApiHandler != null)
            movieApiHandler.clearQueue();
    }

    /*
                Getters & Setters
             */
    public ListView getList() {
        return list;
    }
    public void setList(ListView list) {
        this.list = list;
    }
    public Button getOther() {
        return other;
    }
    public void setOther(Button other) {
        this.other = other;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void setMovies(List<Movie> movie) {
        this.movies = movie;
    }
}