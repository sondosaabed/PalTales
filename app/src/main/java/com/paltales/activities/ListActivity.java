package com.paltales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.paltales.R;
import com.paltales.data.BookAPI;
import com.paltales.data.MovieAPI;
import com.paltales.data.MovieAdapter;
import com.paltales.model.Book;
import com.paltales.data.BookAdapter;
import com.paltales.model.Movie;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView list;
    private Button other;
    List<Book> books;
    List<Movie> movies;
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
                handle_list_onClick(getList(), type, getMovies());
            } else if (type.equals("movies")) {
                getOther().setText(getResources().getString(R.string.see_books));
                handle_list_onClick(getList(), type, getBooks());
            }
            handle_other(getOther(), type);
        }
    }

    private <T> void handle_list_onClick(ListView list, String type, List<T> items) {
        list.setOnItemClickListener((parent, view, position, id) -> {
            T selectedItem = items.get(position);

            String jsonString = new Gson().toJson(selectedItem);

            Intent intent = new Intent(this, ShowItemActivity.class);
            intent.putExtra("item", jsonString);//selectedItem.toString());
            intent.putExtra("type",type); // I will use it to check if the item is book or movie
            startActivity(intent);
        });
    }

    private void set_list_data(String type) {
        /*
            In this method the list shown is based on the choice of the user
         */
        if (type.equals("books")) {
            BookAPI bookApiHandler = new BookAPI(this);
            bookApiHandler.getBooks(new BookAPI.BookAPII() {
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
            MovieAPI movieApiHandler = new MovieAPI(this);
            movieApiHandler.getMovies(new MovieAPI.MovieAPII() {
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
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public List<Movie> getMovies() {
        return movies;
    }
    public void setMovies(List<Movie> movie) {
        this.movies = movie;
    }
}