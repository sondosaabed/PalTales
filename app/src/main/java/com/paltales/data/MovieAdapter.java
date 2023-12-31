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
import com.paltales.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter  extends ArrayAdapter<Movie> {


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
            Picasso.get().load(movie.getImage()).into(coverImageView);

            titleTextView.setText(movie.getName());
            ratingTextView.setText(movie.getRatingValue());
            linkTextView.setText(movie.getUrl());
        }
        return convertView;
    }
}
