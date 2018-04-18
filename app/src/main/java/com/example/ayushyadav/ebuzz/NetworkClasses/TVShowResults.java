package com.example.ayushyadav.ebuzz.NetworkClasses;

import com.google.gson.annotations.SerializedName;

public class TVShowResults {

    @SerializedName("original_name")
    private String title;
    @SerializedName("id")
    private long id;
    @SerializedName("vote_count")
    private int votes;
    @SerializedName("vote_average")
    private double votesAvg;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("first_air_date")
    private String firstAirDate;

    public TVShowResults(String title, long id, int votes, double votesAvg, String backdropPath, String posterPath, String overview, String firstAirDate) {
        this.title = title;
        this.id = id;
        this.votes = votes;
        this.votesAvg = votesAvg;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
        this.overview = overview;
        this.firstAirDate = firstAirDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public double getVotesAvg() {
        return votesAvg;
    }

    public void setVotesAvg(double votesAvg) {
        this.votesAvg = votesAvg;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }
}
