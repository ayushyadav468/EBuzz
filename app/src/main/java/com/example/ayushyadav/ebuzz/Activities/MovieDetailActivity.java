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

import com.example.ayushyadav.ebuzz.ApiClient;
import com.example.ayushyadav.ebuzz.Constants;
import com.example.ayushyadav.ebuzz.NetworkClasses.Genres;
import com.example.ayushyadav.ebuzz.NetworkClasses.MovieDetails;
import com.example.ayushyadav.ebuzz.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailActivity extends AppCompatActivity{

    private long movieid;

    private CollapsingToolbarLayout mCollapsingToolBarLayout;
    private AppBarLayout mAppBarLayout;
    private Toolbar mtoolbar;
    private ConstraintLayout ToolbarConstraintLayout;
    private LinearLayout MovieRatingLinearLayout;

    private ProgressBar progressBar;

    private ImageView movieposter;
    private TextView ReadMoretext;
    private TextView OverViewtext;
    private TextView ReleaseAndRunTimeDatetext;
    private TextView MovieNametext;
    private TextView Moviegenretext;
    private TextView yearOfReleasetext;
    private TextView movieRating;
    private TextView revenueandbudget;

    private LinearLayout mDetailsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("");

        Intent intent = getIntent();
        movieid = intent.getLongExtra(Constants.MOVIE_ID, -1);
        if (movieid == -1) finish();

        movieposter = findViewById(R.id.moviePoster);
        ReadMoretext = findViewById(R.id.text_view_read_more_movie_detail);
        OverViewtext = findViewById(R.id.text_view_overview_movie_detail);
        ReleaseAndRunTimeDatetext = findViewById(R.id.text_view_details_movie_detail);
        MovieNametext = findViewById(R.id.movie_name);
        Moviegenretext = findViewById(R.id.movie_genre_textView);
        yearOfReleasetext = findViewById(R.id.yearOfRelease);
        revenueandbudget = findViewById(R.id.revenueandbudget);
        progressBar = findViewById(R.id.movie_detail_progress_bar);
        movieRating = findViewById(R.id.text_view_rating_movie_detail);
        mDetailsLayout = findViewById(R.id.layout_details_movie_detail);
        mAppBarLayout = findViewById(R.id.appbar);
        mCollapsingToolBarLayout = findViewById(R.id.collapsing_toolbar);
        mtoolbar = findViewById(R.id.toolbarMovieDetail);
        ToolbarConstraintLayout = findViewById(R.id.toolbarConstraintLayout);
        MovieRatingLinearLayout = findViewById(R.id.layout_rating_movie_detail);

        loadDetail();

    }

    private void loadDetail() {
        Call<MovieDetails> call = ApiClient.getInstance().getApiCall().getMovieDetails(movieid, Constants.apiKey);
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                final MovieDetails movieDetails = response.body();
                if(movieDetails != null){
                    mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                        @Override
                        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                            if (appBarLayout.getTotalScrollRange() + verticalOffset == 0) {
                                if (movieDetails.getTitle() != null)
                                    mCollapsingToolBarLayout.setTitle(movieDetails.getTitle());
                                else
                                    mCollapsingToolBarLayout.setTitle("");
                                mtoolbar.setVisibility(View.VISIBLE);
                            } else {
                                mCollapsingToolBarLayout.setTitle("");
                                mtoolbar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                    progressBar.setVisibility(View.GONE);
                    Picasso.get().load(Constants.bigSizeImageURL + movieDetails.getBackdropPath()).into(movieposter);
                    if (movieDetails.getTitle() != null)
                        MovieNametext.setText(movieDetails.getTitle());
                    else
                        MovieNametext.setText("");

                    setGenres(movieDetails.getGenres());
                    setYear(movieDetails.getReleaseDate());
                    ToolbarConstraintLayout.setVisibility(View.VISIBLE);

                    if (movieDetails.getVoteAverage() != null && movieDetails.getVoteAverage() != 0)
                        movieRating.setText(String.format("%s", movieDetails.getVoteAverage()));
                    MovieRatingLinearLayout.setVisibility(View.VISIBLE);

                    if (movieDetails.getOverview() != null && !movieDetails.getOverview().trim().isEmpty()) {
                        ReadMoretext.setVisibility(View.VISIBLE);

                        OverViewtext.setVisibility(View.VISIBLE);
                        OverViewtext.setText(movieDetails.getOverview());
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

                    setDetails(movieDetails.getReleaseDate(), movieDetails.getRuntime());
                    revenueandbudget.setText(movieDetails.getBudget() + "$\n" + movieDetails.getRevenue() + "$");
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {
            }
        });
    }

    private void setDetails(String releaseString, Integer runtime) {
        String detailsString = "";

        if (releaseString != null && !releaseString.trim().isEmpty()) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("MMM d, yyyy");
            try {
                Date releaseDate = sdf1.parse(releaseString);
                detailsString += sdf2.format(releaseDate) + "\n";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            detailsString = "-\n";
        }

        if (runtime != null && runtime != 0) {
            if (runtime < 60) {
                detailsString += runtime + " min(s)";
            } else {
                detailsString += runtime / 60 + " hr " + runtime % 60 + " mins";
            }
        } else {
            detailsString += "-";
        }
        ReleaseAndRunTimeDatetext.setText(detailsString);
    }

    private void setYear(String releaseDateString) {
        if (releaseDateString != null && !releaseDateString.trim().isEmpty()) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
            try {
                Date releaseDate = sdf1.parse(releaseDateString);
                yearOfReleasetext.setText(sdf2.format(releaseDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            yearOfReleasetext.setText("");
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
        Moviegenretext.setText(genres);
    }

}
