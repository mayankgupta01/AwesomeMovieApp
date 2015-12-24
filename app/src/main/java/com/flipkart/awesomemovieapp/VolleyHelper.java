package com.flipkart.awesomemovieapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by mayank.gupta on 19/12/15.
 */
public class VolleyHelper {

    RequestQueue requestQueue;
    Context context;
    private  static VolleyHelper instance;

    private VolleyHelper(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public static VolleyHelper getInstance(Context context){
        if(instance == null) {
            instance = new VolleyHelper(context);
        }
        return  instance;
    }

}
