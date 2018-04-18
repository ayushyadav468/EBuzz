package com.example.ayushyadav.ebuzz.NetworkClasses;

import com.google.gson.annotations.SerializedName;

public class CastResults {

    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private long id;
    @SerializedName("profile_path")
    private String profilePath;

    public CastResults(String name, long id, String profilePath) {
        this.name = name;
        this.id = id;
        this.profilePath = profilePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }


}
