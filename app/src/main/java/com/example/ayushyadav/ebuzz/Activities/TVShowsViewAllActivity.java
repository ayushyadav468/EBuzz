package com.example.ayushyadav.ebuzz.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ayushyadav.ebuzz.Adapters.MovieViewAllAdapter;
import com.example.ayushyadav.ebuzz.Adapters.TVShowViewAllAdapter;
import com.example.ayushyadav.ebuzz.ApiClient;
import com.example.ayushyadav.ebuzz.Constants;
import com.example.ayushyadav.ebuzz.NetworkClasses.MovieResults;
import com.example.ayushyadav.ebuzz.NetworkClasses.MoviesViewAllResult;
import com.example.ayushyadav.ebuzz.NetworkClasses.TVShowResults;
import com.example.ayushyadav.ebuzz.NetworkClasses.TVShowViewAllResult;
import com.example.ayushyadav.ebuzz.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowsViewAllActivity extends AppCompatActivity implements Callback<TVShowViewAllResult>{

    private ProgressBar tvShowViewAllProgressbar;
    private RecyclerView mRecyclerView;
    private ArrayList<TVShowResults> mTVShow = new ArrayList<>();
    private TVShowViewAllAdapter tvShowAdapter;
    private int mTVType;
    private boolean pagesOver = false;
    private int presentPage = 1;
    private boolean loading = true;
    private int previousTotal = 0;
    private int visibleThreshold = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_shows_view_all);

        Intent receivedIntent = getIntent();
        mTVType = receivedIntent.getIntExtra(Constants.VIEW_ALL_TVSHOW_TYPE, -1);
        if (mTVType == -1) finish();
        switch (mTVType) {
            case Constants.SHOW_AIRING_TODAY_TYPE:
                setTitle("SHOWS AIRING TODAY");
                break;
            case Constants.POPULAR_TVSHOW_TYPE:
                setTitle("Popular TV Shows");
                break;
            case Constants.SHOW_ON_AIR_TYPE:
                setTitle("SHOW ON AIR");
                break;
            case Constants.TOP_RATED_TVSHOW_TYPE:
                setTitle("Top Rated TV SHOW");
                break;
        }

        tvShowViewAllProgressbar = findViewById(R.id.tv_show_view_all_progress_bar);
        mRecyclerView = findViewById(R.id.tv_show_view_all_recycler_view);
        tvShowAdapter = new TVShowViewAllAdapter(mTVShow, getApplicationContext(), new TVShowViewAllAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                long id = mTVShow.get(position).getId();
                Intent intent = new Intent(getApplicationContext(), TVDetailActivity.class);
                intent.putExtra(Constants.TV_SHOW_ID, id);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(tvShowAdapter);

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
                    loadMovies(mTVType);
                    loading = true;
                }
            }
        });
        loadMovies(mTVType);

    }

    private void loadMovies(int mMovieType) {
        if (pagesOver) return;
        switch (mMovieType) {
            case Constants.SHOW_AIRING_TODAY_TYPE:
                final Call<TVShowViewAllResult> mNowShowingCall = ApiClient.getInstance().getApiCall().getShowsAiringToday(Constants.apiKey, "en-US", presentPage);
                mNowShowingCall.enqueue(this);
                break;
            case Constants.POPULAR_TVSHOW_TYPE:
                Call<TVShowViewAllResult> mNowShowingCall1 = ApiClient.getInstance().getApiCall().getPopularTVshow(Constants.apiKey, "en-US", presentPage);
                mNowShowingCall1.enqueue(this);
                break;
            case Constants.SHOW_ON_AIR_TYPE:
                Call<TVShowViewAllResult> mNowShowingCall2 = ApiClient.getInstance().getApiCall().getShowOnAir(Constants.apiKey,"en-US", presentPage);
                mNowShowingCall2.enqueue(this);
                break;
            case Constants.TOP_RATED_TVSHOW_TYPE:
                Call<TVShowViewAllResult> mNowShowingCall3 = ApiClient.getInstance().getApiCall().getTopRatedTVshow(Constants.apiKey, "en-US", presentPage);
                mNowShowingCall3.enqueue(this);
                break;
        }
    }

    @Override
    public void onResponse(Call<TVShowViewAllResult> call, Response<TVShowViewAllResult> response) {
        TVShowViewAllResult tvShowViewAllResult = response.body();
        tvShowViewAllProgressbar.setVisibility(View.VISIBLE);
        if (tvShowViewAllResult.tvShowResults != null) {
            //mMovies.clear();
            mTVShow.addAll(tvShowViewAllResult.tvShowResults);
        }
        tvShowAdapter.notifyDataSetChanged();
        if (response.body().getPage() == response.body().getTotalpages())
            pagesOver = true;
        else
            presentPage++;
        //mNowShowingCall.enqueue(this);
        tvShowViewAllProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(Call<TVShowViewAllResult> call, Throwable t) {
    }

}
