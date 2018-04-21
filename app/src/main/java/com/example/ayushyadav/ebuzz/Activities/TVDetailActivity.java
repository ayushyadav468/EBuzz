package com.example.ayushyadav.ebuzz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ayushyadav.ebuzz.ApiClient;
import com.example.ayushyadav.ebuzz.Constants;
import com.example.ayushyadav.ebuzz.NetworkClasses.Genres;
import com.example.ayushyadav.ebuzz.NetworkClasses.TVDetails;
import com.example.ayushyadav.ebuzz.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVDetailActivity extends AppCompatActivity {

    private long tvId;

    private CollapsingToolbarLayout mCollapsingToolBarLayout;
    private AppBarLayout mAppBarLayout;
    private Toolbar mtoolbar;
    private ConstraintLayout ToolbarConstraintLayout;
    private LinearLayout TVRatingLinearLayout;

    private ProgressBar progressBar;

    private ImageView tvPoster;
    private TextView ReadMoretext;
    private TextView OverViewtext;
    private TextView firstOnAirandSeasonstext;
    private TextView TVNametext;
    private TextView TVgenretext;
    private TextView firstOnAirtext;
    private TextView tvRating;
    private TextView episodesAndRuntimeText;

    private LinearLayout mDetailsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("");

        Intent intent = getIntent();
        tvId = intent.getLongExtra(Constants.TV_SHOW_ID, -1);
        if (tvId == -1) finish();

        ReadMoretext = findViewById(R.id.text_view_read_more_tv_detail);

        tvPoster = findViewById(R.id.tvPoster);
        OverViewtext = findViewById(R.id.text_view_overview_tv_detail);
        firstOnAirandSeasonstext = findViewById(R.id.text_view_details_tv_detail);
        TVNametext = findViewById(R.id.tv_name);
        TVgenretext = findViewById(R.id.tv_genre_textView);
        firstOnAirtext = findViewById(R.id.yearOfRelease);
        episodesAndRuntimeText = findViewById(R.id.noOfEpisodes_runtime);
        progressBar = findViewById(R.id.tv_detail_progress_bar);
        tvRating = findViewById(R.id.text_view_rating_tv_detail);

        mDetailsLayout = findViewById(R.id.layout_details_tv_detail);
        mAppBarLayout = findViewById(R.id.appbar);
        mCollapsingToolBarLayout = findViewById(R.id.collapsing_toolbar);
        mtoolbar = findViewById(R.id.toolbarTVDetail);
        ToolbarConstraintLayout = findViewById(R.id.toolbarConstraintLayout);
        TVRatingLinearLayout = findViewById(R.id.layout_rating_tv_detail);

        loadDetail();

    }

    private void loadDetail() {
        Call<TVDetails> call = ApiClient.getInstance().getApiCall().getTVShowDetails(tvId, Constants.apiKey);
        call.enqueue(new Callback<TVDetails>() {
            @Override
            public void onResponse(Call<TVDetails> call, Response<TVDetails> response) {
                final TVDetails tvDetails = response.body();
                if (tvDetails != null) {
                    progressBar.setVisibility(View.GONE);
                    Picasso.get().load(Constants.bigSizeImageURL + tvDetails.getPoster()).into(tvPoster);
                    TVNametext.setText(tvDetails.getName());
                    setGenres(tvDetails.getGenres());
                    setYear(tvDetails.getFirstAirDate());
                    ToolbarConstraintLayout.setVisibility(View.VISIBLE);

                    tvRating.setText(String.format("%s", tvDetails.getVote_average()));
                    TVRatingLinearLayout.setVisibility(View.VISIBLE);

                    if (tvDetails.getOverview() != null && !tvDetails.getOverview().trim().isEmpty()) {
                        ReadMoretext.setVisibility(View.VISIBLE);

                        OverViewtext.setVisibility(View.VISIBLE);
                        OverViewtext.setText(tvDetails.getOverview());
                        ReadMoretext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                OverViewtext.setMaxLines(Integer.MAX_VALUE);
                                mDetailsLayout.setVisibility(View.VISIBLE);
                                ReadMoretext.setVisibility(View.GONE);
                            }
                        });
                    } else
                        OverViewtext.setText("");

                    setDetails(tvDetails.getFirstAirDate(), tvDetails.getNoOfSeasons());
                    episodesAndRuntimeText.setText(tvDetails.getNoOfEpisodes() + "$\n" + tvDetails.getEpisodeRunTime() + "$");
                }
            }

            @Override
            public void onFailure(Call<TVDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"onFailed Called", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setYear(String firstAirDate) {
        if (firstAirDate != null && !firstAirDate.trim().isEmpty()) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
            try {
                Date releaseDate = sdf1.parse(firstAirDate);
                firstOnAirtext.setText(sdf2.format(releaseDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            firstOnAirtext.setText("");
        }
    }

    private void setGenres(List<Genres> genresList) {
        String genres = "";
        if (genresList != null) {
            for (int i = 0; i < genresList.size(); i++) {
                if (genresList.get(i) == null) continue;
                if (i == genresList.size() - 1) {
                    genres = genres.concat(genresList.get(i).getGenreName());
                } else {
                    genres = genres.concat(genresList.get(i).getGenreName() + ", ");
                }
            }
        }
        TVgenretext.setText(genres);
    }

    private void setDetails(String firstOnAirDate, Integer noOfSeasons) {
        String detailsString = "";

        if (firstOnAirDate != null && !firstOnAirDate.trim().isEmpty()) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("MMM d, yyyy");
            try {
                Date releaseDate = sdf1.parse(firstOnAirDate);
                detailsString += sdf2.format(releaseDate) + "\n";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            detailsString = "-\n";
        }

        if (noOfSeasons != null && noOfSeasons != 0) {
            detailsString += noOfSeasons + "";

        } else {
            detailsString += "-";
        }
        firstOnAirandSeasonstext.setText(detailsString);
    }

}
