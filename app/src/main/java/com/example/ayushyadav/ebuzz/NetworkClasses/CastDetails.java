package com.example.ayushyadav.ebuzz.NetworkClasses;

import com.google.gson.annotations.SerializedName;

public class CastDetails {

    @SerializedName("name")
    private String name;
    @SerializedName("birthday")
    private String birthDay;
    @SerializedName("deathday")
    private String deathDay;
    @SerializedName("gender")
    private int gender;
    @SerializedName("place_of_birth")
    private String placeOfBirth;
    @SerializedName("popularity")
    private long popularity;

    public CastDetails(String name, String birthDay, String deathDay, int gender, String placeOfBirth, long popularity) {
        this.name = name;
        this.birthDay = birthDay;
        this.deathDay = deathDay;
        this.gender = gender;
        this.placeOfBirth = placeOfBirth;
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getDeathDay() {
        return deathDay;
    }

    public void setDeathDay(String deathDay) {
        this.deathDay = deathDay;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public long getPopularity() {
        return popularity;
    }

    public void setPopularity(long popularity) {
        this.popularity = popularity;
    }
}
