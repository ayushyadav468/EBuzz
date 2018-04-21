package com.example.ayushyadav.ebuzz.NetworkClasses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVDetails {

    @SerializedName("number_of_episodes")
    private int noOfEpisodes;
    @SerializedName("number_of_seasons")
    private int noOfSeasons;
    @SerializedName("episode_run_time")
    private int episodeRunTime;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("genres")
    List<Genres> genres;
    @SerializedName("original_name")
    private String name;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("vote_average")
    private Double vote_average;
    @SerializedName("backdrop_path")
    private String poster;

    public TVDetails(int noOfEpisodes, int noOfSeasons, int episodeRunTime, String firstAirDate, List<Genres> genres, String name, String overview, Double popularity, Double vote_average, String poster) {
        this.noOfEpisodes = noOfEpisodes;
        this.noOfSeasons = noOfSeasons;
        this.episodeRunTime = episodeRunTime;
        this.firstAirDate = firstAirDate;
        this.genres = genres;
        this.name = name;
        this.overview = overview;
        this.popularity = popularity;
        this.vote_average = vote_average;
        this.poster = poster;
    }

    public int getNoOfEpisodes() {
        return noOfEpisodes;
    }

    public void setNoOfEpisodes(int noOfEpisodes) {
        this.noOfEpisodes = noOfEpisodes;
    }

    public int getNoOfSeasons() {
        return noOfSeasons;
    }

    public void setNoOfSeasons(int noOfSeasons) {
        this.noOfSeasons = noOfSeasons;
    }

    public int getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(int episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}