package com.paltales.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.Authenticator;
import java.util.ArrayList;

public class GETRequestData<T> {
    /*
            In this class I Wrapp
             the logic behind using the volly
            To request the data from a uri
            for both books & Movies
            Since I use diiffrent object its decide to use Generic Objects
            It's Get since I only make a GET request
     */
    // Attributes of a GetRequest
    private String url;
    private RequestQueue queue;
    private ArrayList<T> data;

    public GETRequestData(String url, Context context){
        setUrl(url);
        setQueue(Volley.newRequestQueue(context));
        setData(new ArrayList<>());
    }

    public ArrayList<T> requestData(){
        StringRequest request = new StringRequest(
                getUrl(),

                response -> {
                    try{
                        JSONArray jsonArray = new JSONArray(response);
                        setData(parseJSONArray(jsonArray));
                    } catch (JSONException error) {
                        Log.d("JSONException", error.toString());
                        throw new RuntimeException(error);
                    }
                },

                error -> {
                    Log.d("VollyError", error.toString());
                    throw new RuntimeException(error);
                });

        getQueue().add(request);

        return getData();
    }

    private ArrayList<T> parseJSONArray(JSONArray jsonArray){
        /*
            Created this method to parse the JSON Array
            - TODO I would like to consider Pagination as I learned with Laravel
         */
        ArrayList<T> parsedData = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                T item = parseJsonObject(jsonObject);
                parsedData.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("JSONException", "Error parsing JSON object: " + e.getMessage());
            }
        }
        return parsedData;
    }

    private T parseJsonObject(JSONObject jsonObject) {
        /*
        I feel this method is against Generic Programming,
        TODO I will try to find otehr way that suits my approach
         */
        if(jsonObject.getClass().equals("Book")){

        }else if(jsonObject.getClass().equals("Movie")){

        }else{

        }
        return null;
    }

    /*
    Getters & Setters
     */
    public String getUrl() {
        return url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public RequestQueue getQueue() {
        return queue;
    }
    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }
    public ArrayList<T> getData() {
        return data;
    }
    public void setData(ArrayList<T> data) {
        this.data = data;
    }
}