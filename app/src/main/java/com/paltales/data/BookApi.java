package com.paltales.data;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.paltales.model.Book;
import com.paltales.utils.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookAPI {

    private static final String API_URL = URLs.BOOKS_URL;
    private RequestQueue requestQueue;

    public BookAPI(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public interface BookAPII {
        void onSuccess(List<Book> books);

        void onError(String errorMessage);
    }

    public void getBooks(final BookAPII callback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, API_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Book> books = parseBooks(response);
                        callback.onSuccess(books);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    private List<Book> parseBooks(JSONObject response) {
        /*
            This is the parsing logic based on the Postman response way
            The resoinse is an array called "works" and the works are
            books and each book has diffrent attributes, and each attribute
            could have diffrent types like arrays etc.
            So based on my desire I only took the attribites that I need in
            the book list item like author link, book url, the title
            and the image
         */
        List<Book> books = new ArrayList<>();

        try {
            JSONArray worksArray = response.getJSONArray("works");

            for (int i = 0; i < worksArray.length(); i++) {
                JSONObject workObject = worksArray.getJSONObject(i);

                String title = workObject.optString("title", "");
                String key = workObject.optString("key", "");

                JSONArray authorsArray = workObject.optJSONArray("authors");
                String authorKey = "";
                if (authorsArray != null && authorsArray.length() > 0) {
                    JSONObject authorObject = authorsArray.getJSONObject(0);
                    authorKey = authorObject.getJSONObject("author").optString("key", "");
                }

                String cover = "";
                JSONArray coversArray = workObject.optJSONArray("covers");
                if (coversArray != null && coversArray.length() > 0) {
                    cover = String.valueOf(coversArray.get(0));
                }

                String description ="";
                JSONArray descriptionArray = workObject.optJSONArray("description");
                if (descriptionArray != null && descriptionArray.length() > 0) {
                    JSONObject descriptionObject = descriptionArray.getJSONObject(0);
                    description = descriptionObject.optString("value", "");
                }

                Book book = new Book(title, cover, key, authorKey, description);
                books.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }
}