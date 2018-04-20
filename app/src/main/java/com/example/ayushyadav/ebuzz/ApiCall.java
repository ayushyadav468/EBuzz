package com.example.ayushyadav.ebuzz;

import com.example.ayushyadav.ebuzz.NetworkClasses.CastList;
import com.example.ayushyadav.ebuzz.NetworkClasses.MovieDetails;
import com.example.ayushyadav.ebuzz.NetworkClasses.MoviesList;
import com.example.ayushyadav.ebuzz.NetworkClasses.MoviesViewAllResult;
import com.example.ayushyadav.ebuzz.NetworkClasses.TVShowList;
import com.example.ayushyadav.ebuzz.NetworkClasses.TVShowViewAllResult;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("movie/now_playing")
    Call<MoviesViewAllResult> getlatest(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);
    @GET("movie/popular")
    Call<MoviesViewAllResult> getPopular(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);
    @GET("movie/upcoming")
    Call<MoviesViewAllResult> getUpcoming(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);
    @GET("movie/top_rated")
    Call<MoviesViewAllResult> getTopRated(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("tv/airing_today")
    Call<TVShowViewAllResult> getShowsAiringToday(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);
    @GET("tv/popular")
    Call<TVShowViewAllResult> getPopularTVshow(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);
    @GET("tv/on_the_air")
    Call<TVShowViewAllResult> getShowOnAir(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);
    @GET("tv/top_rated")
    Call<TVShowViewAllResult> getTopRatedTVshow(@Query("api_key") String apiKey, @Query("language") String language, @Query("page") int page);

    @GET("movie/{id}")
    Call<MovieDetails> getMovieDetails(@Path("id") long movieId, @Query("api_key") String apiKey);


}
