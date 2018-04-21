package com.example.ayushyadav.ebuzz.NetworkClasses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailActivityCastList {

    @SerializedName("cast")
    public ArrayList<DetailActivityCastResults> cast;

    public DetailActivityCastList(ArrayList<DetailActivityCastResults> cast) {
        this.cast = cast;
    }

    public ArrayList<DetailActivityCastResults> getCast() {
        return cast;
    }

    public void setCast(ArrayList<DetailActivityCastResults> cast) {
        this.cast = cast;
    }
}
