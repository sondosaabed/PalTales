package com.paltales.model;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class RequestData {
    /*
            In this class I create teh logic behind using the volly
            To request the data from a uri
            for both books & Movies
     */
    public static void requestData(String uri){
        StringRequest request = new StringRequest(
                uri,
                response -> {

                },
                error -> {

                });
    }
}
