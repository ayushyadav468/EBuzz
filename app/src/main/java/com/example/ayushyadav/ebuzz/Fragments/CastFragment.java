package com.example.ayushyadav.ebuzz.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.ayushyadav.ebuzz.Adapters.CastAdapter;
import com.example.ayushyadav.ebuzz.ApiClient;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cast, container, false);

        castFrameLayout = view.findViewById(R.id.cast_frame_layout);
        castFragmentProgressBar = view.findViewById(R.id.castFragmentProgressBar);
        castRecyclerView = view.findViewById(R.id.cast_recycler_view);
        castAdapter = new CastAdapter(castResultsArrayList, getContext());

        castRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        castRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        castRecyclerView.setItemAnimator(new DefaultItemAnimator());
        castRecyclerView.setAdapter(castAdapter);

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
