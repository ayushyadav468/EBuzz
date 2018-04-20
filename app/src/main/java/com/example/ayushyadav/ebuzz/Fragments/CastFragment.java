package com.example.ayushyadav.ebuzz.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.ayushyadav.ebuzz.Activities.CastDetailActivity;
import com.example.ayushyadav.ebuzz.Adapters.CastAdapter;
import com.example.ayushyadav.ebuzz.ApiClient;
import com.example.ayushyadav.ebuzz.Constants;
import com.example.ayushyadav.ebuzz.NetworkClasses.CastList;
import com.example.ayushyadav.ebuzz.NetworkClasses.CastResults;
import com.example.ayushyadav.ebuzz.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastFragment extends Fragment {

    FrameLayout castFrameLayout;
    RecyclerView castRecyclerView;
    CastAdapter castAdapter;
    ArrayList<CastResults> castResultsArrayList = new ArrayList<>();
    ProgressBar castFragmentProgressBar;

    private boolean loading = true;
    private int previousTotal = 0;
    private int visibleThreshold = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cast, container, false);

        castFrameLayout = view.findViewById(R.id.cast_frame_layout);
        castFragmentProgressBar = view.findViewById(R.id.castFragmentProgressBar);
        castRecyclerView = view.findViewById(R.id.cast_recycler_view);
        castAdapter = new CastAdapter(castResultsArrayList, getContext(), new CastAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                long id = castResultsArrayList.get(position).getId();
                Intent intent = new Intent(getContext(), CastDetailActivity.class);
                intent.putExtra(Constants.CAST_ID, id);
                startActivity(intent);
            }
        });

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        castRecyclerView.setLayoutManager(linearLayoutManager);
        castRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        castRecyclerView.setItemAnimator(new DefaultItemAnimator());
        castRecyclerView.setAdapter(castAdapter);

        castRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    fetchCastDetail();
                    loading = true;
                }
            }
        });
        fetchCastDetail();
        return view;
    }

    private void fetchCastDetail() {
        castRecyclerView.setVisibility(View.GONE);
        castFragmentProgressBar.setVisibility(View.VISIBLE);

        Call<CastList> castResultsCall = ApiClient.getInstance().getApiCall().personData();
        castResultsCall.enqueue(new Callback<CastList>() {
            @Override
            public void onResponse(Call<CastList> call, Response<CastList> response) {
                CastList castList =  response.body();
                if(castList != null){
                    castResultsArrayList.clear();
                    castResultsArrayList.addAll(castList.results);
                    castAdapter.notifyDataSetChanged();
                }
                castRecyclerView.setVisibility(View.VISIBLE);
                castFragmentProgressBar.setVisibility(View.GONE);
                castFrameLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<CastList> call, Throwable t) {

            }
        });

    }

}
