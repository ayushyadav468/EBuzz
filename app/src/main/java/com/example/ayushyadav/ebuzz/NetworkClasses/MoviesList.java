package com.example.ayushyadav.ebuzz.NetworkClasses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesList {

    @SerializedName("results")
    public ArrayList<MovieResults> results;

    public MoviesList(ArrayList<MovieResults> results) {
        this.results = results;
    }

    public ArrayList<MovieResults> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieResults> results) {
        this.results = results;
    }

}
