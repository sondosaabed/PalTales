//package com.paltales.model;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//public class GETRequestData<T> {
//    /*
//            In this class I Wrapp
//            the logic behind using the volly
//            To request the data from a uri
//            for both books & Movies
//            Since I use diiffrent object its decide to use Generic Objects
//            It's Get since I only make a GET request
//    */
//    // Attributes of a GetRequest
//    private String url;
//    private RequestQueue queue;
//    private ArrayList<T> data;
//
//    public GETRequestData(String url, Context context){
//        setUrl(url);
//        setQueue(Volley.newRequestQueue(context));
//        setData(new ArrayList<>());
//    }
//
//    public ArrayList<T> requestData(){
//        StringRequest request = new StringRequest(
//                getUrl(),
//
//                response -> {
//                    Log.d("RawJSONResponse", response);
//                    try{
//                        JSONArray jsonArray = new JSONArray(response);
//                        setData(parseJSONArray(jsonArray));
//                    } catch (JSONException error) {
//                        Log.d("JSONException", error.toString());
//                        throw new RuntimeException(error);
//                    }
//                },
//
//                error -> {
//                    Log.d("VollyError", error.toString());
//                    throw new RuntimeException(error);
//                });
//
//        getQueue().add(request);
//
//        return getData();
//    }
//
//    private ArrayList<T> parseJSONArray(JSONArray jsonArray){
//        /*
//            Created this method to parse the JSON Array
//         */
//        ArrayList<T> parsedData = new ArrayList<>();
//        for (int i = 0; i < jsonArray.length(); i++) {
//            try {
//                JSONArray jsonObject = jsonArray.getJSONArray(i);
//                T item = parseJsonObject(jsonObject);
//                parsedData.add(item);
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Log.d("JSONException", "Error parsing JSON object: " + e.getMessage());
//            }
//        }
//        return parsedData;
//    }
//
//    private T parseJsonObject(JSONArray jsonObject) {
//        /*
//        I feel this method is against Generic Programming,
//         */
//        if(jsonObject.has("authors")){
//            return (T) parseBooks(jsonObject);
//        }else if(jsonObject.has("n")){
//            return (T) parseBook(jsonObject);
//        }else{
//            return null;
//        }
//    }
//
//    private ArrayList<Book> parseBooks(JSONArray  worksArray){
//        ArrayList<Book> books = new ArrayList<>();
//        for (int i = 0; i < worksArray.length(); i++) {
//            try {
//                JSONObject bookObject = worksArray.getJSONObject(i);
//                Book book = parseBook(bookObject);
//                books.add(book);
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Log.d("JSONException", "Error parsing Book object: " + e.getMessage());
//            }
//        }
//        return books;
//    }
//
//    private Book parseBook(JSONObject jsonObject) {
//        String title = jsonObject.optString("title", "");
//        String cover = jsonObject.optJSONArray("covers").optString(0,"");
//        String key = jsonObject.optString("key","");
//        String author = jsonObject.optJSONArray("authors").optJSONObject(0).optJSONObject("author").optString("key","");
//
//        return new Book(title, cover, key, author);
//    }
//
//    /*
//    Getters & Setters
//     */
//    public String getUrl() {
//        return url;
//    }
//    public void setUrl(String url){
//        this.url = url;
//    }
//    public RequestQueue getQueue() {
//        return queue;
//    }
//    public void setQueue(RequestQueue queue) {
//        this.queue = queue;
//    }
//    public ArrayList<T> getData() {
//        return data;
//    }
//    public void setData(ArrayList<T> data) {
//        this.data = data;
//    }
//}