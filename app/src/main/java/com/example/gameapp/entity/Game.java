package com.example.gameapp.entity;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Game implements Comparable
{
    private String mIdGame;
    private String mNameGame;
    private String mDescriptionGame;
    private int mNumberStars;
    private String mGenderGame;
    private String mPathImage;
    private int mDate;  //Format : 20190325 -> 25 mars 2019


    public Game() {
    }

    @Exclude
    public String getIdGame() {
        return mIdGame;
    }

    public void setIdGame(String idGame) {
        this.mIdGame = idGame;
    }

    public String getNameGame() {
        return mNameGame;
    }

    public void setNameGame(String nameGame) {
        this.mNameGame = nameGame;
    }

    public String getDescriptionGame() {
        return mDescriptionGame;
    }

    public void setDescriptionGame(String descriptionGame) {
        this.mDescriptionGame = descriptionGame;
    }

    public int getNumberStars() {
        return mNumberStars;
    }

    public void setNumberStars(int numberStars) {
        this.mNumberStars = numberStars;
    }

    public String getGenderGame() {
        return mGenderGame;
    }

    public void setGenderGame(String genderGame) {
        this.mGenderGame = genderGame;
    }

    public String getPathImage() { return mPathImage; }

    public void setPathImage(String pathImage) {
        this.mPathImage = pathImage;
    }

    public int getDate() {
        return mDate;
    }

    public void setDate(int date) {
        this.mDate = date;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Game)) return false;
        Game o = (Game) obj;
        return o.getIdGame().equals(this.getIdGame());
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", mNameGame);
        result.put("description", mDescriptionGame);
        result.put("stars", mNumberStars);
        result.put("genre", mGenderGame);
        result.put("path", mPathImage);
        result.put("date", mDate);

        return result;
    }

    @Override
    public String toString() {
        return mNameGame;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return toString().compareTo(o.toString());
    }
}