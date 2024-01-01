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
    /*
        In this Implemetation, a volly request queue is made on JSON FIle
        that contains data about books. Below there is an example of the
        JSON OBjects that is returned and the logic of parsing
     */
    private static final String API_URL = URLs.BOOKS_URL;
    private RequestQueue requestQueue;

    public BookAPI(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public interface BookAPII {
        void onSuccess(List<Book> books);

        void onError(String errorMessage);
    }

    public void getItems(final BookAPII callback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, API_URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<Book> books = parseItems(response);
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

    private List<Book> parseItems(JSONObject response) {
        /*
            This is the parsing logic based on the Postman response way
            The resoinse is an array called "works" and the works are
            books and each book has diffrent attributes, and each attribute
            could have diffrent types like arrays etc.
            So based on my desire I only took the attribites that I need in
            the book list item like author link, book url, the title
            and the image

            this is an example of the response
            {
          "works": [
            {
              "description": {
                "type": "/type/text",
                "value": "\"In this groundbreaking book, published on the fiftieth anniversary of the Occupation, the outspoken and radical Israeli historian Ilan Pappé examines the most contested ideas concerning the origins and identity of the contemporary state of Israel. The \"ten myths\" that Pappé explores--repeated endlessly in the media, enforced by the military, accepted without question by the world's governments--reinforce the regional status quo. He explores the claim that Palestine was an empty land at the time of the Balfour Declaration, as well as the formation of Zionism and its role in the early decades of nation building. He asks whether the Palestinians voluntarily left their homeland in 1948, and whether June 1967 was a war of \"no choice.\" Turning to the myths surrounding the failures of the Camp David Accords and the official reasons for the attacks on Gaza, Pappé explains why the two-state solution is no longer viable.\"--Publisher's description."
              },
              "key": "/works/OL20054379W",
              "authors": [
                {
                  "type": {
                    "key": "/type/author_role"
                  },
                  "author": {
                    "key": "/authors/OL690129A"
                  }
                },
                {
                  "type": {
                    "key": "/type/author_role"
                  },
                  "author": {
                    "key": "/authors/OL7989898A"
                  }
                }
              ],
              "title": "Ten myths about Israel",
              "subject_places": [
                "Palestine",
                "Israel"
              ],
              "subjects": [
                "Politics and government",
                "Palestinian Arabs",
                "Arab-Israeli conflict",
                "History",
                "Palestinian arabs",
                "Arab-israeli conflict",
                "Palestine, history",
                "Israel, politics and government",
                "Israele",
                "Palestina",
                "Colonialismo",
                "Apartheid"
              ],
              "type": {
                "key": "/type/work"
              },
              "covers": [
                11174269,
                14298432,
                14298433,
                14298434,
                14298435,
                12248753
              ],
              "links": [
                {
                  "title": "La recensione di Michele Giorgio su «Pagine esteri»",
                  "url": "https://pagineesteri.it/2022/05/26/cultura/libri-ilan-pappe-10-miti-su-israele/",
                  "type": {
                    "key": "/type/link"
                  }
                }
              ],
              "latest_revision": 7,
              "revision": 7,
              "created": {
                "type": "/type/datetime",
                "value": "2019-07-19T22:52:39.773703"
              },
              "last_modified": {
                "type": "/type/datetime",
                "value": "2023-12-15T04:14:20.316601"
              }
            },
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