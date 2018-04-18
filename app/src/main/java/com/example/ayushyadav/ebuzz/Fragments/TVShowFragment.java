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

import com.example.ayushyadav.ebuzz.Adapters.TVShowsCardView1Adapter;
import com.example.ayushyadav.ebuzz.Adapters.TVShowsCardView2Adapter;
import com.example.ayushyadav.ebuzz.ApiClient;
import com.example.ayushyadav.ebuzz.NetworkClasses.MovieResults;
import com.example.ayushyadav.ebuzz.NetworkClasses.TVShowList;
import com.example.ayushyadav.ebuzz.NetworkClasses.TVShowList;
import com.example.ayushyadav.ebuzz.NetworkClasses.TVShowResults;
import com.example.ayushyadav.ebuzz.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowFragment extends Fragment {

    RecyclerView showsAiringTodayRecyclerView, popularTVShowsRecyclerView, showsOnAirRecyclerView, topRatedRecyclerView;
    TVShowsCardView1Adapter showsAiringTodayAdapter, showsOnAirAdapter;
    TVShowsCardView2Adapter popularTVShowsAdapter, topRatedAdapter;

    ArrayList<TVShowResults> showsAiringTodayArrayList = new ArrayList<>();
    ArrayList<TVShowResults> popularTVShowsArrayList = new ArrayList<>();
    ArrayList<TVShowResults> showsOnAirArrayList = new ArrayList<>();
    ArrayList<TVShowResults> topRatedArrayList = new ArrayList<>();

    FrameLayout showsAiringTodayFrameLayout, popularTVShowsFrameLayout, showsOnAirFrameLayout, topRatedFrameLayout;
    TextView showsAiringTodayTextViewAll, popularTVShowsTextViewAll, showsOnAirTextViewAll, topRatedTextViewAll;

    ProgressBar TVShowFragmentProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tvshow, container, false);

        TVShowFragmentProgressBar = view.findViewById(R.id.movieFragmentProgressBar);

        showsAiringTodayFrameLayout = view.findViewById(R.id.layout_now_showing);
        popularTVShowsFrameLayout = view.findViewById(R.id.layout_popular);
        topRatedFrameLayout = view.findViewById(R.id.layout_top_rated);
        showsOnAirFrameLayout = view.findViewById(R.id.layout_upcoming);

        showsAiringTodayTextViewAll = view.findViewById(R.id.text_view_view_all_now_showing);
        popularTVShowsTextViewAll = view.findViewById(R.id.text_view_view_all_popular);
        showsOnAirTextViewAll = view.findViewById(R.id.text_view_view_all_upcoming);
        topRatedTextViewAll = view.findViewById(R.id.text_view_view_all_top_rated);

        showsAiringTodayRecyclerView = view.findViewById(R.id.nowShowingRecyclerView);
        popularTVShowsRecyclerView = view.findViewById(R.id.popularRecyclerView);
        showsOnAirRecyclerView = view.findViewById(R.id.upComingRecyclerView);
        topRatedRecyclerView = view.findViewById(R.id.topRatedRecyclerView);

        showsAiringTodayAdapter = new TVShowsCardView1Adapter(showsAiringTodayArrayList, getContext());
        showsOnAirAdapter = new TVShowsCardView1Adapter(showsOnAirArrayList, getContext());
        popularTVShowsAdapter = new TVShowsCardView2Adapter(popularTVShowsArrayList, getContext());
        topRatedAdapter = new TVShowsCardView2Adapter(topRatedArrayList, getContext());

        showsAiringTodayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        showsAiringTodayRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(showsAiringTodayRecyclerView);
        showsAiringTodayRecyclerView.setItemAnimator(new DefaultItemAnimator());
        showsAiringTodayRecyclerView.setAdapter(showsAiringTodayAdapter);

        popularTVShowsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        popularTVShowsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        popularTVShowsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        popularTVShowsRecyclerView.setAdapter(popularTVShowsAdapter);

        showsOnAirRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelper1 = new LinearSnapHelper();
        snapHelper1.attachToRecyclerView(showsOnAirRecyclerView);
        showsOnAirRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        showsOnAirRecyclerView.setItemAnimator(new DefaultItemAnimator());
        showsOnAirRecyclerView.setAdapter(showsOnAirAdapter);

        topRatedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        topRatedRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        topRatedRecyclerView.setItemAnimator(new DefaultItemAnimator());
        topRatedRecyclerView.setAdapter(topRatedAdapter);

        fetchNowShowingTVShowList();
        fetchPopularTVShowList();
        fetchUpcomingTVShowList();
        fetchTopRatedTVShowList();

        return view;
    }

    private void fetchNowShowingTVShowList() {
        showsAiringTodayRecyclerView.setVisibility(View.GONE);
        TVShowFragmentProgressBar.setVisibility(View.VISIBLE);

        Call<TVShowList> showsAiringTodayTVShowListCall = ApiClient.getInstance().getApiCall().showsAiringTodayData();
        showsAiringTodayTVShowListCall.enqueue(new Callback<TVShowList>() {
            @Override
            public void onResponse(Call<TVShowList> call, Response<TVShowList> response) {
                TVShowList tvShowList = response.body();
                if (tvShowList != null) {
                    showsAiringTodayArrayList.clear();
                    showsAiringTodayArrayList.addAll(tvShowList.results);
                    showsAiringTodayAdapter.notifyDataSetChanged();
                }
                showsAiringTodayRecyclerView.setVisibility(View.VISIBLE);
                showsAiringTodayFrameLayout.setVisibility(View.VISIBLE);
                TVShowFragmentProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<TVShowList> call, Throwable t) {

            }
        });

    }

    private void fetchPopularTVShowList() {
        popularTVShowsRecyclerView.setVisibility(View.GONE);
        TVShowFragmentProgressBar.setVisibility(View.VISIBLE);

        Call<TVShowList> popularTVShowsTVShowListCall = ApiClient.getInstance().getApiCall().popularTVShowsData();
        popularTVShowsTVShowListCall.enqueue(new Callback<TVShowList>() {
            @Override
            public void onResponse(Call<TVShowList> call, Response<TVShowList> response) {
                TVShowList tvShowList = response.body();
                if (tvShowList != null) {
                    popularTVShowsArrayList.clear();
                    popularTVShowsArrayList.addAll(tvShowList.results);
                    popularTVShowsAdapter.notifyDataSetChanged();
                }
                popularTVShowsRecyclerView.setVisibility(View.VISIBLE);
                popularTVShowsFrameLayout.setVisibility(View.VISIBLE);
                TVShowFragmentProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<TVShowList> call, Throwable t) {

            }
        });

    }

    private void fetchUpcomingTVShowList() {
        showsOnAirRecyclerView.setVisibility(View.GONE);
        TVShowFragmentProgressBar.setVisibility(View.VISIBLE);

        Call<TVShowList> showsOnAirURLTVShowListCall = ApiClient.getInstance().getApiCall().showsOnAirData();
        showsOnAirURLTVShowListCall.enqueue(new Callback<TVShowList>() {
            @Override
            public void onResponse(Call<TVShowList> call, Response<TVShowList> response) {
                TVShowList tvShowList = response.body();
                if (tvShowList != null) {
                    showsOnAirArrayList.clear();
                    showsOnAirArrayList.addAll(tvShowList.results);
                    showsOnAirAdapter.notifyDataSetChanged();
                }
                showsOnAirRecyclerView.setVisibility(View.VISIBLE);
                showsOnAirFrameLayout.setVisibility(View.VISIBLE);
                TVShowFragmentProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<TVShowList> call, Throwable t) {

            }
        });

    }

    private void fetchTopRatedTVShowList() {
        topRatedRecyclerView.setVisibility(View.GONE);
        TVShowFragmentProgressBar.setVisibility(View.VISIBLE);

        Call<TVShowList> topRatedTVShowListCall = ApiClient.getInstance().getApiCall().topRatedTVShowsData();
        topRatedTVShowListCall.enqueue(new Callback<TVShowList>() {
            @Override
            public void onResponse(Call<TVShowList> call, Response<TVShowList> response) {
                TVShowList tvShowList = response.body();
                if (tvShowList != null) {
                    topRatedArrayList.clear();
                    topRatedArrayList.addAll(tvShowList.results);
                    topRatedAdapter.notifyDataSetChanged();
                }
                topRatedRecyclerView.setVisibility(View.VISIBLE);
                topRatedFrameLayout.setVisibility(View.VISIBLE);
                TVShowFragmentProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<TVShowList> call, Throwable t) {

            }
        });

    }

}
