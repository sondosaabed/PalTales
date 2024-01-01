package com.paltales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.paltales.R;

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

        handle_data(type);
        handle_back(getBack(), type);
    }

    private void handle_data(String type) {
        if (type.equals("movies")) {

        }else if(type.equals("books")){

        }
    }

    private void handle_back(Button back, String type) {
        back.setOnClickListener(e->{
            if(type.equals("movies")){
                Intent intent = new Intent(this, ListActivity.class);
                intent.putExtra("type","movies");
                startActivity(intent);
            }else if(type.equals("books")){
                Intent intent = new Intent(this, ListActivity.class);
                intent.putExtra("type","books");
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