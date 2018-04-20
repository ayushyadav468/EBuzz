package com.example.ayushyadav.ebuzz.NetworkClasses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TVShowViewAllResult {

    @SerializedName("results")
    public ArrayList<TVShowResults> tvShowResults;
    @SerializedName("page")
    private int page;
    @SerializedName("total_pages")
    private int totalpages;
    @SerializedName("total_results")
    private int totalResults;

    public TVShowViewAllResult(ArrayList<TVShowResults> tvShowResults, int page, int totalpages, int totalResults) {
        this.tvShowResults = tvShowResults;
        this.page = page;
        this.totalpages = totalpages;
        this.totalResults = totalResults;
    }

    public TVShowViewAllResult(int page, int totalpages, int totalResults) {
        this.page = page;
        this.totalpages = totalpages;
        this.totalResults = totalResults;
    }

    public ArrayList<TVShowResults> getTvShowResults() {
        return tvShowResults;
    }

    public void setTvShowResults(ArrayList<TVShowResults> tvShowResults) {
        this.tvShowResults = tvShowResults;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
