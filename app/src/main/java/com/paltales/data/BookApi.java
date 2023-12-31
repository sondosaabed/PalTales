package com.paltales.data;

import android.content.Context;
import android.util.Log;
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

public class BookApi {

    private static final String BASE_URL = URLs.BOOKS_URL;

    private final Context context;
    private final RequestQueue requestQueue;

    public BookApi(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public interface OnBooksReceivedListener {
        void onBooksReceived(ArrayList<Book> books);

        void onError(String errorMessage);
    }

    public void getBooks(OnBooksReceivedListener listener) {
        String url = BASE_URL;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray worksArray = response.getJSONArray("works");
                            if (worksArray != null) {
                                ArrayList<Book> books = parseBooks(worksArray);
                                listener.onBooksReceived(books);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            listener.onError("Error parsing JSON: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError("Error in the request: " + error.getMessage());
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    private ArrayList<Book> parseBooks(JSONArray worksArray) {
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < worksArray.length(); i++) {
            try {
                JSONObject bookObject = worksArray.getJSONObject(i);
                // Parse the Book object and add it to the list
                Book book = parseBook(bookObject);
                books.add(book);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("JSONException", "Error parsing Book object: " + e.getMessage());
            }
        }
        return books;
    }

    private Book parseBook(JSONObject jsonObject) {
        Book book = new Book();

        JSONArray worksArray = jsonObject.optJSONArray("works");
        if (worksArray != null) {
            for (int i = 0; i < worksArray.length(); i++) {
                try {
                    JSONObject workObject = worksArray.getJSONObject(i);

                    if (workObject != null) {
                        String title = jsonObject.optString("title", "");
                        String cover = jsonObject.optJSONArray("covers").optString(0,"");
                        String key = jsonObject.optString("key","");
                        String author = jsonObject.optJSONArray("authors").optJSONObject(0).optJSONObject("author").optString("key","");

                        book.setTitle(title);
                        book.setAuthor(author);
                        book.setCover(cover);
                        book.setKey(key);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return book;
    }
}
    /*
    private Book parseBook(JSONObject jsonObject) throws JSONException {
        String title = jsonObject.optString("title", "");
        String cover = jsonObject.optJSONArray("covers").optString(0,"");
        String key = jsonObject.optString("key","");
        String author = jsonObject.optJSONArray("authors").optJSONObject(0).optJSONObject("author").optString("key","");

        return new Book(title, cover, key, author);
    }
}
     */
