package com.paltales.data;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.paltales.model.Movie;
import com.paltales.utils.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MovieAPI {

    private static final String API_URL = URLs.MOVIES_URL;
    private RequestQueue requestQueue;

    public MovieAPI(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public interface MovieAPII {
        void onSuccess(List<Movie> movies);

        void onError(String errorMessage);
    }

    public void getItems(final MovieAPII callback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                response -> {
                    List<Movie> movies = parseItems(response);
                    callback.onSuccess(movies);
                },
                error -> callback.onError(error.getMessage()));

        requestQueue.add(jsonObjectRequest);
    }

    private List<Movie> parseItems(JSONObject response) {
        List<Movie> movies = new ArrayList<>();

        try {
            JSONArray worksArray = response.getJSONArray("movies");

            for (int i = 0; i < worksArray.length(); i++) {
                JSONObject movieObject = worksArray.getJSONObject(i);

                String id = movieObject.optString("id", "");

                String title = "";
                JSONObject titleTextObject = movieObject.optJSONObject("titleText");
                if (titleTextObject != null) {
                    title = titleTextObject.optString("text", "");
                }

                String primaryImage = "";
                JSONObject primaryImageObject = movieObject.optJSONObject("primaryImage");
                if (primaryImageObject != null) {
                    primaryImage = primaryImageObject.optString("url", "");
                }

                int year = 0;
                JSONObject dateObj = movieObject.optJSONObject("releaseDate");
                if (dateObj != null) {
                    year = dateObj.optInt("year", 0);
                }

                String plot = "";
                JSONObject plotObject = movieObject.optJSONObject("plot");
                if (plotObject != null) {
                    JSONObject plotTextObject = plotObject.optJSONObject("plotText");

                    if (plotTextObject != null) {
                        plot = plotTextObject.optString("plainText", "");
                    }
                }

                Movie movie = new Movie(id, title, primaryImage, year, plot);
                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }
}

