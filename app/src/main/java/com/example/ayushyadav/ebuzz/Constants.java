package com.example.ayushyadav.ebuzz;

public class Constants {

    final public static String apiKey = "3c31e95c56cb0c3ab16e391b160ea636";
    final public static String baseURL = "https://api.themoviedb.org/3/";

    final public static String nowPlayingMoviesURL = "movie/now_playing?api_key=" + apiKey + "&language=en-US&page=1";
    final public static String topRatedMoviesURL = "movie/top_rated?api_key=" + apiKey + "&language=en-US&page=1";
    final public static String upComingMoviesURL = "movie/upcoming?api_key=" + apiKey + "&language=en-US&page=1";
    final public static String popularMoviesURL = "movie/popular?api_key=" + apiKey + "&language=en-US&page=1";

    final public static String showsAiringTodayURL = "tv/airing_today?api_key=" + apiKey + "&language=en-US&page=1";
    final public static String showsOnAirURL = "tv/on_the_air?api_key=" + apiKey + "&language=en-US&page=1";
    final public static String popularTVShowsURL = "tv/popular?api_key=" + apiKey + "&language=en-US&page=1";
    final public static String topRatedTVShowsURL = "tv/top_rated?api_key="+apiKey+"&language=en-US&page=1";

    final public static String castURL = "person/popular?api_key="+ apiKey +"&language=en-US&page=1";

    final public static String bigSizeImageURL = "http://image.tmdb.org/t/p/w780/";
    final public static String mediumSizeImageURL = "http://image.tmdb.org/t/p/w342/";
    final public static String smallSizeImageURL = "http://image.tmdb.org/t/p/w185/";

    final public static String genreURL = "genre/movie/list?api_key=" + apiKey + "&language=en-US";

    public static final String MOVIE_ID = "movie_id";
    public static final String TV_SHOW_ID = "tv_show_id";
    public static final String CAST_ID = "cast_id";

    public static final String VIEW_ALL_MOVIES_TYPE = "type_view_all_movies";
    public static final int NOW_SHOWING_MOVIES_TYPE = 1;
    public static final int POPULAR_MOVIES_TYPE = 2;
    public static final int UPCOMING_MOVIES_TYPE = 3;
    public static final int TOP_RATED_MOVIES_TYPE = 4;


}
