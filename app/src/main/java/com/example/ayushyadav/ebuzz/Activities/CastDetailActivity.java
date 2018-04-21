package com.example.ayushyadav.ebuzz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ayushyadav.ebuzz.ApiClient;
import com.example.ayushyadav.ebuzz.Constants;
import com.example.ayushyadav.ebuzz.NetworkClasses.CastDetails;
import com.example.ayushyadav.ebuzz.R;
import com.squareup.picasso.Picasso;

import java.util.PriorityQueue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastDetailActivity extends AppCompatActivity {

    public long castID;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ConstraintLayout constraintLayout;

    private ImageView image;
    private TextView castName;
    private ProgressBar progressBar;
    private TextView birthDate;
    private TextView deathDate;
    private TextView placeOfBirth;
    private TextView popularity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        castID = intent.getLongExtra(Constants.CAST_ID, -1);

        appBarLayout = findViewById(R.id.app_bar);
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        constraintLayout = findViewById(R.id.toolbarConstraintLayout);

        image = findViewById(R.id.castImage);
        castName = findViewById(R.id.cast_name);
        progressBar = findViewById(R.id.cast_detail_progress_bar);
        birthDate = findViewById(R.id.text_view_birthday);
        deathDate = findViewById(R.id.text_view_deathday);
        placeOfBirth = findViewById(R.id.text_view_placeOfBirth);
        popularity = findViewById(R.id.text_view_popularity);

        loadActivity();

    }

    private void loadActivity() {
        Call<CastDetails> castDetailsCall = ApiClient.getInstance().getApiCall().getCastDetails(castID, Constants.apiKey, "en-US");
        castDetailsCall.enqueue(new Callback<CastDetails>() {
            @Override
            public void onResponse(Call<CastDetails> call, Response<CastDetails> response) {
                CastDetails castDetails = response.body();
                if(castDetails != null){
                    castName.setText(castDetails.getName());
                    birthDate.setText(castDetails.getBirthDay());
                    deathDate.setText(castDetails.getDeathDay());
                    placeOfBirth.setText(castDetails.getPlaceOfBirth());
                    popularity.setText(castDetails.getPopularity()+"");
                }
            }

            @Override
            public void onFailure(Call<CastDetails> call, Throwable t) {
                Toast.makeText(CastDetailActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
