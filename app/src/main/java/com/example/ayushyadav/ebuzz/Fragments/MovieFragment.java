package com.example.ayushyadav.ebuzz.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ayushyadav.ebuzz.Adapters.MoviesCardView1Adapter;
import com.example.ayushyadav.ebuzz.Adapters.MoviesCardView2Adapter;
import com.example.ayushyadav.ebuzz.ApiClient;
import com.example.ayushyadav.ebuzz.NetworkClasses.MovieResults;
import com.example.ayushyadav.ebuzz.NetworkClasses.MoviesList;
import com.example.ayushyadav.ebuzz.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {

    RecyclerView nowShowingRecyclerView, popularRecyclerView, upComingRecyclerView, topRatedRecyclerView;
    MoviesCardView1Adapter nowShowingAdapter, upComingAdapter;
    MoviesCardView2Adapter popularAdapter, topRatedAdapter;

    ArrayList<MovieResults> nowShowingArrayList = new ArrayList<>();
    ArrayList<MovieResults> popularArrayList = new ArrayList<>();
    ArrayList<MovieResults> upComingArrayList = new ArrayList<>();
    ArrayList<MovieResults> topRatedArrayList = new ArrayList<>();

    FrameLayout nowShowingFrameLayout, popularFrameLayout, upComingFrameLayout, topRatedFrameLayout;
    TextView nowShowingTextViewAll, popularTextViewAll, upComingTextViewAll, topRatedTextViewAll;

    ProgressBar movieFragmentProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        movieFragmentProgressBar = view.findViewById(R.id.movieFragmentProgressBar);

        nowShowingFrameLayout = view.findViewById(R.id.layout_now_showing);
        popularFrameLayout = view.findViewById(R.id.layout_popular);
        topRatedFrameLayout = view.findViewById(R.id.layout_top_rated);
        upComingFrameLayout = view.findViewById(R.id.layout_upcoming);

        nowShowingTextViewAll = view.findViewById(R.id.text_view_view_all_now_showing);
        popularTextViewAll = view.findViewById(R.id.text_view_view_all_popular);
        upComingTextViewAll = view.findViewById(R.id.text_view_view_all_upcoming);
        topRatedTextViewAll = view.findViewById(R.id.text_view_view_all_top_rated);

        nowShowingRecyclerView = view.findViewById(R.id.nowShowingRecyclerView);
        popularRecyclerView = view.findViewById(R.id.popularRecyclerView);
        upComingRecyclerView = view.findViewById(R.id.upComingRecyclerView);
        topRatedRecyclerView = view.findViewById(R.id.topRatedRecyclerView);

        nowShowingAdapter = new MoviesCardView1Adapter(nowShowingArrayList, getContext());
        upComingAdapter = new MoviesCardView1Adapter(upComingArrayList, getContext());
        popularAdapter = new MoviesCardView2Adapter(popularArrayList, getContext());
        topRatedAdapter = new MoviesCardView2Adapter(topRatedArrayList, getContext());

        nowShowingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        nowShowingRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(nowShowingRecyclerView);
        nowShowingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        nowShowingRecyclerView.setAdapter(nowShowingAdapter);

        popularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        popularRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        popularRecyclerView.setItemAnimator(new DefaultItemAnimator());
        popularRecyclerView.setAdapter(popularAdapter);

        upComingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelper1 = new LinearSnapHelper();
        snapHelper1.attachToRecyclerView(upComingRecyclerView);
        upComingRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        upComingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        upComingRecyclerView.setAdapter(upComingAdapter);

        topRatedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        topRatedRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        topRatedRecyclerView.setItemAnimator(new DefaultItemAnimator());
        topRatedRecyclerView.setAdapter(topRatedAdapter);

        fetchNowShowingMoviesList();
        fetchPopularMoviesList();
        fetchUpcomingMoviesList();
        fetchTopRatedMoviesList();

        return view;
    }

    private void fetchNowShowingMoviesList() {
        nowShowingRecyclerView.setVisibility(View.GONE);
        movieFragmentProgressBar.setVisibility(View.VISIBLE);

        Call<MoviesList> nowShowingMoviesListCall = ApiClient.getInstance().getApiCall().nowPlayingMoviesData();
        nowShowingMoviesListCall.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                MoviesList moviesList = response.body();
                if (moviesList != null) {
                    nowShowingArrayList.clear();
                    nowShowingArrayList.addAll(moviesList.results);
                    nowShowingAdapter.notifyDataSetChanged();
                }
                nowShowingRecyclerView.setVisibility(View.VISIBLE);
                nowShowingFrameLayout.setVisibility(View.VISIBLE);
                movieFragmentProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {

            }
        });

    }

    private void fetchPopularMoviesList() {
        popularRecyclerView.setVisibility(View.GONE);
        movieFragmentProgressBar.setVisibility(View.VISIBLE);

        Call<MoviesList> popularMoviesListCall = ApiClient.getInstance().getApiCall().popularMoviesData();
        popularMoviesListCall.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                MoviesList moviesList = response.body();
                if (moviesList != null) {
                    popularArrayList.clear();
                    popularArrayList.addAll(moviesList.results);
                    popularAdapter.notifyDataSetChanged();
                }
                popularRecyclerView.setVisibility(View.VISIBLE);
                popularFrameLayout.setVisibility(View.VISIBLE);
                movieFragmentProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {

            }
        });

    }

    private void fetchUpcomingMoviesList() {
        upComingRecyclerView.setVisibility(View.GONE);
        movieFragmentProgressBar.setVisibility(View.VISIBLE);

        Call<MoviesList> upComingMoviesListCall = ApiClient.getInstance().getApiCall().upComingMoviesData();
        upComingMoviesListCall.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                MoviesList moviesList = response.body();
                if (moviesList != null) {
                    upComingArrayList.clear();
                    upComingArrayList.addAll(moviesList.results);
                    upComingAdapter.notifyDataSetChanged();
                }
                upComingRecyclerView.setVisibility(View.VISIBLE);
                upComingFrameLayout.setVisibility(View.VISIBLE);
                movieFragmentProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {

            }
        });

    }

    private void fetchTopRatedMoviesList() {
        topRatedRecyclerView.setVisibility(View.GONE);
        movieFragmentProgressBar.setVisibility(View.VISIBLE);

        Call<MoviesList> topRatedMoviesListCall = ApiClient.getInstance().getApiCall().topRatedMoviesData();
        topRatedMoviesListCall.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                MoviesList moviesList = response.body();
                if (moviesList != null) {
                    topRatedArrayList.clear();
                    topRatedArrayList.addAll(moviesList.results);
                    topRatedAdapter.notifyDataSetChanged();
                }
                topRatedRecyclerView.setVisibility(View.VISIBLE);
                topRatedFrameLayout.setVisibility(View.VISIBLE);
                movieFragmentProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {

            }
        });

    }

}
