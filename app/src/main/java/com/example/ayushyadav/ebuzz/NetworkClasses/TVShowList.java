package com.example.ayushyadav.ebuzz.NetworkClasses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TVShowList {

    @SerializedName("results")
    public ArrayList<TVShowResults> results;

    public TVShowList(ArrayList<TVShowResults> results) {
        this.results = results;
    }

    public ArrayList<TVShowResults> getResults() {
        return results;
    }

    public void setResults(ArrayList<TVShowResults> results) {
        this.results = results;
    }
}
