package com.paltales.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.paltales.R;
import com.paltales.model.Book;
import com.paltales.model.GETRequestData;
import com.paltales.model.Movie;
import com.paltales.utils.URLs;

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
        if(type.equals("books")){
            GETRequestData<Book> requestData = new GETRequestData<>(URLs.BOOKS_URL, this);
            ArrayAdapter<Book> listAdapter = new ArrayAdapter<>(this,
                    R.layout.book_list_item,
                    requestData.getData()
            );
            getList().setAdapter(listAdapter);

        }else if(type.equals("movies")){
            GETRequestData<Movie> requestData = new GETRequestData<>(URLs.MOVIES_URL, this);
            ArrayAdapter<Movie> listAdapter = new ArrayAdapter<>(this,
                    R.layout.movie_list_item,
                    requestData.getData()
            );
            getList().setAdapter(listAdapter);
        }
    }

    private void handle_other(Button other, String type) {
        other.setOnClickListener(v->{
            /*
                Like when they click other they can show the other list
             */
            if(type.equals("movies"))
                set_list_data("books");
            else if(type.equals("books"))
                set_list_data("movies");
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