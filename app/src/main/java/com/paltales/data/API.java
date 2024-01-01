package com.paltales.data;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.List;

public interface API<T> {
    /**
     * Dear reviewer
     * I have created this Interface but didn;t have the time to use
     * I have noticed that I applied the same logic of parsing
     * the same methods I mean to both Books API and Movies API
     * So I know I can write cleaner code
     * Since I am obssissed with it,
     * Hence, I will be using this interface as a future development to the code
     */
    String API_URL = "";
    RequestQueue requestQueue = null;

    void onSuccess(List<T> items);

    void onError(String errorMessage);

    default void getItems(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, API_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<T> items = parseItems(response);
                onSuccess(items);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onError(error.getMessage());
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    List<T> parseItems(JSONObject response);
}
