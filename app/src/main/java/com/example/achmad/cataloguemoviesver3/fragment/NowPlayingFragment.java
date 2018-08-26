package com.example.achmad.cataloguemoviesver3.fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.achmad.cataloguemoviesver3.R;
import com.example.achmad.cataloguemoviesver3.adapter.MovieAdapter;
import com.example.achmad.cataloguemoviesver3.entity.Movie;
import com.example.achmad.cataloguemoviesver3.entity.MovieResult;
import com.example.achmad.cataloguemoviesver3.rest.MovieClient;
import com.example.achmad.cataloguemoviesver3.rest.MovieInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.achmad.cataloguemoviesver3.utils.Utils.API_KEY;

/**
 * Created by Achmad on 23-08-2018
 **/

public class NowPlayingFragment extends Fragment {


    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.recycler_movie_now)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar_now)
    ProgressBar progressBar;

    List<MovieResult> movieList;
    MovieAdapter movieAdapter;


    MovieInterface movieService;
    Call<Movie> movieCall;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_now_playing, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        getMovies();

        return rootView;
    }

    void initView() {
        movieAdapter = new MovieAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void getMovies() {

        showProgressBar();
        movieService = MovieClient.getClient().create(MovieInterface.class);
        movieCall = movieService.getNowPlayingMovie(API_KEY);

        movieList = new ArrayList<>();

        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.body() != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        movieList = Objects.requireNonNull(response.body()).getResults();
                    }
                }
                movieAdapter.setMovieResult(movieList);
                recyclerView.setAdapter(movieAdapter);
                hideProgressBar();
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong"
                        , Toast.LENGTH_SHORT).show();
                hideProgressBar();
            }
        });
    }

    void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
