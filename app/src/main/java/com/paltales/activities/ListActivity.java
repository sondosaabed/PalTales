package com.paltales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        initialize();
    }

    private void initialize() {
        setOther(findViewById(R.id.other));
        setList(findViewById(R.id.list));

        String type = getIntent().getStringExtra("choice");
        if(type != null)
            if(type.equals("books"))
                getOther().setText(getResources().getString(R.string.see_mov));
            else if(type.equals("movies"))
                getOther().setText(getResources().getString(R.string.see_books));

        set_list_data(type);
        handle_list_onClick(getList(), type);
        handle_other(getOther(), type);
    }

    private void handle_list_onClick(ListView list, String type) {
        list.setOnClickListener(e->{
                //(parent, view, position, id) -> {
            Intent intent = new Intent(this, ShowItemActivity.class);
//            intent.putExtra("selectedTaskID", id);
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
                }

                @Override
                public void onError(String errorMessage) {
                    Log.d("error", errorMessage);
                    Toast.makeText(ListActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }else if(type.equals("movies")){
            MovieAPI movieApiHandler = new MovieAPI(this);
            movieApiHandler.getMovies(new MovieAPI.MovieAPII() {
                @Override
                public void onSuccess(List<Movie> movies) {
                    MovieAdapter listAdapter = new MovieAdapter(ListActivity.this, movies);
                    getList().setAdapter(listAdapter);
                }

                @Override
                public void onError(String errorMessage) {
                    Log.d("error", errorMessage);
                    Toast.makeText(ListActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
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
}