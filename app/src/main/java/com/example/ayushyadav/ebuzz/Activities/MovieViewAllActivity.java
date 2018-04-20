package com.example.ayushyadav.ebuzz.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ayushyadav.ebuzz.Adapters.MovieViewAllAdapter;
import com.example.ayushyadav.ebuzz.ApiClient;
import com.example.ayushyadav.ebuzz.Constants;
import com.example.ayushyadav.ebuzz.NetworkClasses.MovieResults;
import com.example.ayushyadav.ebuzz.NetworkClasses.MoviesViewAllResult;
import com.example.ayushyadav.ebuzz.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieViewAllActivity extends AppCompatActivity implements Callback<MoviesViewAllResult>{

    private ProgressBar movieViewAllProgressbar;
    private RecyclerView mRecyclerView;
    private ArrayList<MovieResults> mMovies = new ArrayList<>();
    private MovieViewAllAdapter mMoviesAdapter;
    private int mMovieType;
    private boolean pagesOver = false;
    private int presentPage = 1;
    private boolean loading = true;
    private int previousTotal = 0;
    private int visibleThreshold = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_view_all);

        Intent receivedIntent = getIntent();
        mMovieType = receivedIntent.getIntExtra(Constants.VIEW_ALL_MOVIES_TYPE, -1);
        if (mMovieType == -1) finish();
        switch (mMovieType) {
            case Constants.NOW_SHOWING_MOVIES_TYPE:
                setTitle("IN THEATERS");
                break;
            case Constants.POPULAR_MOVIES_TYPE:
                setTitle("POPULAR Movies");
                break;
            case Constants.UPCOMING_MOVIES_TYPE:
                setTitle("UPCOMING MOVIES");
                break;
            case Constants.TOP_RATED_MOVIES_TYPE:
                setTitle("TOP RATED");
                break;
        }

        movieViewAllProgressbar = findViewById(R.id.movie_view_all_progress_bar);
        mRecyclerView = findViewById(R.id.movie_view_all_recycler_view);
        mMoviesAdapter = new MovieViewAllAdapter(mMovies, getApplicationContext(), new MovieViewAllAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                long id = mMovies.get(position).getId();
                Intent intent = new Intent(getApplicationContext(), MovieDetailActivity.class);
                intent.putExtra(Constants.MOVIE_ID, id);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mMoviesAdapter);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = gridLayoutManager.getChildCount();
                int totalItemCount = gridLayoutManager.getItemCount();
                int firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition();
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    loadMovies(mMovieType);
                    loading = true;
                }
            }
        });
        loadMovies(mMovieType);

    }

    private void loadMovies(int mMovieType) {
        if (pagesOver) return;
        switch (mMovieType) {
            case Constants.NOW_SHOWING_MOVIES_TYPE:
                final Call<MoviesViewAllResult> mNowShowingCall = ApiClient.getInstance().getApiCall().getlatest(Constants.apiKey, "en-US", presentPage);
                mNowShowingCall.enqueue(this);
                break;
            case Constants.POPULAR_MOVIES_TYPE:
                Call<MoviesViewAllResult> mNowShowingCall1 = ApiClient.getInstance().getApiCall().getPopular(Constants.apiKey, "en-US", presentPage);
                mNowShowingCall1.enqueue(this);
                break;
            case Constants.UPCOMING_MOVIES_TYPE:
                Call<MoviesViewAllResult> mNowShowingCall2 = ApiClient.getInstance().getApiCall().getUpcoming(Constants.apiKey,"en-US", presentPage);
                mNowShowingCall2.enqueue(this);
                break;
            case Constants.TOP_RATED_MOVIES_TYPE:
                Call<MoviesViewAllResult> mNowShowingCall3 = ApiClient.getInstance().getApiCall().getTopRated(Constants.apiKey, "en-US", presentPage);
                mNowShowingCall3.enqueue(this);
                break;
        }
    }

    @Override
    public void onResponse(Call<MoviesViewAllResult> call, Response<MoviesViewAllResult> response) {
        MoviesViewAllResult moviesViewAllResult = response.body();
        movieViewAllProgressbar.setVisibility(View.VISIBLE);
        if (moviesViewAllResult.movieResults != null) {
            //mMovies.clear();
            mMovies.addAll(moviesViewAllResult.movieResults);
        }
        mMoviesAdapter.notifyDataSetChanged();
        if (response.body().getPage() == response.body().getTotalpages())
            pagesOver = true;
        else
            presentPage++;
        //mNowShowingCall.enqueue(this);
        movieViewAllProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(Call<MoviesViewAllResult> call, Throwable t) {
    }
}
