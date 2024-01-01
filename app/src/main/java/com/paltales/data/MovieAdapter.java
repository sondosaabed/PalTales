package com.paltales.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.paltales.R;
import com.paltales.activities.ShowItemActivity;
import com.paltales.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter  extends ArrayAdapter<Movie> {
    /*
        In this class I create a custom Array Adapter for the books listveiw
        Inspired by:
            https://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
     */
    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item, parent, false);
        }

        ImageView coverImageView = convertView.findViewById(R.id.movieCover);
        TextView titleTextView = convertView.findViewById(R.id.movieTitle);
        TextView ratingTextView = convertView.findViewById(R.id.rating);
        TextView linkTextView = convertView.findViewById(R.id.movieLink);

        if (movie != null) {
            Picasso.get().load(movie.getImage()).fit().into(coverImageView);

            titleTextView.setText(movie.getName());
            ratingTextView.setText(String.valueOf(movie.getYear()));
            linkTextView.setText(movie.getUrl());
        }

        RelativeLayout relLay = convertView.findViewById(R.id.relLayMovie);
        relLay.setOnClickListener(v -> {
            String jsonString = new Gson().toJson(movie);
            Intent intent = new Intent(getContext(), ShowItemActivity.class);
            intent.putExtra("item", jsonString);
            intent.putExtra("type", "movies");
            getContext().startActivity(intent);
        });

        return convertView;
    }
}
