package com.example.ayushyadav.ebuzz.NetworkClasses;

import com.google.gson.annotations.SerializedName;

public class DetailActivityCastResults {

    @SerializedName("id")
    private long id;
    @SerializedName("character")
    private String charater;
    @SerializedName("name")
    private String name;
    @SerializedName("profile_path")
    private String image;

    public DetailActivityCastResults(long id, String charater, String name, String image) {
        this.id = id;
        this.charater = charater;
        this.name = name;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCharater() {
        return charater;
    }

    public void setCharater(String charater) {
        this.charater = charater;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
