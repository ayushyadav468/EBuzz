package com.example.ayushyadav.ebuzz.NetworkClasses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CastList {

    @SerializedName("results")
    public ArrayList<CastResults> results;

    public CastList(ArrayList<CastResults> results) {
        this.results = results;
    }

    public ArrayList<CastResults> getResults() {
        return results;
    }

    public void setResults(ArrayList<CastResults> results) {
        this.results = results;
    }
}
