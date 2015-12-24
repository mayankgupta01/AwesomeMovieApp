package com.flipkart.awesomemovieapp;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Movie;
import android.net.Uri;
import android.util.Log;

//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amitgulati on 19/12/15.
 */
public class MovieHelper {

    private static String BASE_URL_REQUEST="https://api.themoviedb.org/3/";
    private static String METHOD_DISCOVER_MOVIE="discover/movie";
    private static String API_KEY="api_key";
    private static String API_KEY_VAL="12bc9597abe64b759644431e3b1f3652";
    private static String SORT_BY="sort_by";
    private static String POPULAR="popularity.desc";

    public String getPopularMoviesUrl() {

        ArrayList<MovieInfo> movies = new ArrayList<MovieInfo>();
        String url = BASE_URL_REQUEST + METHOD_DISCOVER_MOVIE;
        String finalUri = Uri.parse(url).buildUpon().appendQueryParameter(API_KEY, API_KEY_VAL)
                             .appendQueryParameter(SORT_BY, POPULAR).build().toString();

        Log.i("MovieHelper", finalUri);

        return finalUri;
    }

    public ArrayList<MovieInfo> parseMovies(String jsonString) {
        ArrayList<MovieInfo> movies = new ArrayList<MovieInfo>();
        try {
            JSONObject mainObj = new JSONObject(jsonString);

            JSONArray array = mainObj.getJSONArray("results");
            for(int i = 0; i < array.length(); ++i) {
                JSONObject movieObj = (JSONObject)array.get(i);
                MovieInfo info = new MovieInfo();
                info.id = Integer.toString(movieObj.getInt("id"));
                info.isAdult = movieObj.getBoolean("adult");
                info.overView = movieObj.getString("overview");
                String name = movieObj.getString("poster_path");
                String basepath = "https://image.tmdb.org/t/p/original/";
                Uri uri = Uri.withAppendedPath(Uri.parse(basepath), name);
                info.posterImageUrl  = uri.toString();
                info.title = movieObj.getString("title");
                movies.add(info);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;

    }

}
