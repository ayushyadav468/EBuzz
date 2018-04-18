package com.example.ayushyadav.ebuzz;

import com.example.ayushyadav.ebuzz.NetworkClasses.CastList;
import com.example.ayushyadav.ebuzz.NetworkClasses.MoviesList;
import com.example.ayushyadav.ebuzz.NetworkClasses.TVShowList;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.http.GET;

public interface ApiCall {

    @GET(Constants.nowPlayingMoviesURL) Call<MoviesList> nowPlayingMoviesData();
    @GET(Constants.topRatedMoviesURL) Call<MoviesList> topRatedMoviesData();
    @GET(Constants.upComingMoviesURL) Call<MoviesList> upComingMoviesData();
    @GET(Constants.popularMoviesURL) Call<MoviesList> popularMoviesData();

    @GET(Constants.showsAiringTodayURL) Call<TVShowList> showsAiringTodayData();
    @GET(Constants.showsOnAirURL) Call<TVShowList> showsOnAirData();
    @GET(Constants.topRatedTVShowsURL) Call<TVShowList> topRatedTVShowsData();
    @GET(Constants.popularTVShowsURL) Call<TVShowList> popularTVShowsData();

    @GET(Constants.castURL) Call<CastList> personData();

}
