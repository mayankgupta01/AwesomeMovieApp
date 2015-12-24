package com.flipkart.awesomemovieapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by mayank.gupta on 19/12/15.
 */
public class MovieDownloaderTask extends AsyncTask<String, Void, ArrayList<MovieInfo>> {
    MovieHelper helper;

    public MovieDownloaderTask() {
        this.helper = new MovieHelper();
    }

    @Override
    protected ArrayList<MovieInfo> doInBackground(String... params) {
        String url = helper.getPopularMoviesUrl();
        try {
            URL link = new URL(url);
            URLConnection connection = link.openConnection();
            InputStream inStr = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inStr);
            BufferedReader bufferedReader =new BufferedReader(reader);
            StringBuffer buffer = new StringBuffer();
            String line = null;

            while((line = bufferedReader.readLine()) !=null) {
               buffer.append(line);
            }
            return  helper.parseMovies(buffer.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
