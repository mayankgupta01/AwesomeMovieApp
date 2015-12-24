package com.flipkart.awesomemovieapp;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieInfoFragment extends Fragment {

    private TextView textView;
    private int position;

//    Day 6 : Pass 2 : Moving to Volley
    VolleyHelper volleyHelper;
    MovieHelper movieHelper;
    ArrayList<MovieInfo> movieInfoItems;


    public MovieInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleyHelper = VolleyHelper.getInstance(getActivity());
        movieHelper = new MovieHelper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_movie_info, container, false);
        textView = (TextView) mainView.findViewById(R.id.fragmentTextView);

        position = getArguments().getInt("POSITION");
        textView.setText(Integer.toString(position));

//        Pass 2 : Code for using Volley
        StringRequest request = new StringRequest(movieHelper.getPopularMoviesUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                movieInfoItems = movieHelper.parseMovies(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        volleyHelper.requestQueue.add(request);
        // Inflate the layout for this fragment
        return mainView;
    }
}
