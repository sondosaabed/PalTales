package com.paltales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.paltales.R;
import com.paltales.model.Book;
import com.paltales.model.Movie;
import com.squareup.picasso.Picasso;
/*
    This activity is used to show an item when clicked on the list item with more details
 */
public class ShowItemActivity extends AppCompatActivity {
    private ImageView image; //Cover & poster
    private TextView title; //title & name
    private TextView url; // URL
    private TextView plot; // plot
    private TextView yearOrAuthor; // year or author
    private Button back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        initialize();
    }

    private void initialize() {
        setBack(findViewById(R.id.btnBack));
        setImage(findViewById(R.id.coverimage));
        setTtitle(findViewById(R.id.titleItem));
        setUrl(findViewById(R.id.url));
        setYearOrAuthor(findViewById(R.id.yearOrAuther));
        setPlot(findViewById(R.id.describtiontxt));

        String type = getIntent().getStringExtra("type");
        String jsonString = getIntent().getStringExtra("item");

        if(type != null && jsonString != null) {
            Object obj = handle_data(type, jsonString);
            if (obj != null){
                if (type.equals("movies")) {
                    Movie movie = (Movie) obj;
                    getTtitle().setText(movie.getName());
                    getUrl().setText(movie.getUrl());
                    getYearOrAuthor().setText(String.valueOf(movie.getYear()));
                    getPlot().setText(movie.getPlot());
                    Picasso.get().load(movie.getImage()).into(getImage());

                } else if (type.equals("books")) {
                    Book book = (Book) obj;
                    getTtitle().setText(book.getTitle());
                    getUrl().setText(book.getKey());
                    getYearOrAuthor().setText(book.getAuthor());
                    getPlot().setText(book.getDescription());
                    Picasso.get().load(book.getCover()).into(getImage());
                }
            }
            handle_back(getBack(), type);
        }
    }

    private <T> T handle_data(String type, String jsonString) {
        if (type.equals("movies")) {
            Movie movie = new Gson().fromJson(jsonString, Movie.class);
            return (T) movie;
        }else if(type.equals("books")){
            Book book = new Gson().fromJson(jsonString, Book.class);
            return (T) book;
        }
        return null;
    }

    private void handle_back(Button back, String type) {
        back.setOnClickListener(e->{
            if(type.equals("movies")){
                Intent intent = new Intent(this, ListActivity.class);
                intent.putExtra("choice","movies");
                startActivity(intent);
            }else if(type.equals("books")){
                Intent intent = new Intent(this, ListActivity.class);
                intent.putExtra("choice","books");
                startActivity(intent);
            }
        });
    }

    /*
    Getters & setters
     */
    public ImageView getImage() {
        return image;
    }
    public void setImage(ImageView image) {
        this.image = image;
    }
    public TextView getTtitle() {
        return title;
    }
    public void setTtitle(TextView title) {
        this.title = title;
    }
    public TextView getUrl() {
        return url;
    }
    public void setUrl(TextView url) {
        this.url = url;
    }
    public TextView getPlot() {
        return plot;
    }
    public void setPlot(TextView plot) {
        this.plot = plot;
    }
    public TextView getYearOrAuthor() {
        return yearOrAuthor;
    }
    public void setYearOrAuthor(TextView yearOrAuthor) {
        this.yearOrAuthor = yearOrAuthor;
    }
    public Button getBack() {
        return back;
    }
    public void setBack(Button back) {
        this.back = back;
    }
}