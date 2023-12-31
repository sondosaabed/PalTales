package com.paltales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.paltales.R;
import com.paltales.data.MovieAdapter;
import com.paltales.model.Book;
import com.paltales.data.BookAdapter;
import com.paltales.model.Movie;
//import com.paltales.model.GETRequestData;

import java.util.ArrayList;

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
        handle_other(getOther(), type);
    }

    private void set_list_data(String type) {
        /*
            In this method the list shown is based on the choice of the user
         */
        if (type.equals("books")) {
            ArrayList<Book> books = new ArrayList<>();

            books.add(new Book("The Catcher in the Rye", "123456", "/works/OL123456W", "/authors/OL654321A"));
            books.add(new Book("To Kill a Mockingbird", "789012", "/works/OL789012W", "/authors/OL123456A"));
            books.add(new Book("1984", "345678", "/works/OL345678W", "/authors/OL987654A"));
            books.add(new Book("Pride and Prejudice", "901234", "/works/OL901234W", "/authors/OL567890A"));

            BookAdapter listAdapter = new BookAdapter(ListActivity.this, books);
            getList().setAdapter(listAdapter);

        }else if(type.equals("movies")){

            ArrayList<Movie> movies = new ArrayList<>();

            movies.add(new Movie("https://www.imdb.com/title/tt6156350/", "HyperNormalisation", "https://m.media-amazon.com/images/M/MV5BNWFlNDlkYWMtODEyOC00MjY1LWIyM2EtMzVjNjQ2YmM4NzE4XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg", 5));
            movies.add(new Movie("https://www.imdb.com/title/tt6156350/", "HyperNormalisation", "https://m.media-amazon.com/images/M/MV5BNWFlNDlkYWMtODEyOC00MjY1LWIyM2EtMzVjNjQ2YmM4NzE4XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg", 4));
            movies.add(new Movie("https://www.imdb.com/title/tt6156350/", "HyperNormalisation", "https://m.media-amazon.com/images/M/MV5BNWFlNDlkYWMtODEyOC00MjY1LWIyM2EtMzVjNjQ2YmM4NzE4XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg", 3));
            movies.add(new Movie("https://www.imdb.com/title/tt6156350/", "HyperNormalisation", "https://m.media-amazon.com/images/M/MV5BNWFlNDlkYWMtODEyOC00MjY1LWIyM2EtMzVjNjQ2YmM4NzE4XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg", 4));


            MovieAdapter listAdapter = new MovieAdapter(ListActivity.this, movies);
            getList().setAdapter(listAdapter);
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