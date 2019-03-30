package com.example.gameapp.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "game_table")
public class Game
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name = "mIdGame")
    private int mIdGame;

    @ColumnInfo (name = "mNameGame")
    private String mNameGame;

    @ColumnInfo (name="mDescriptionGame")
    private String mDescriptionGame;

    @ColumnInfo (name = "mNumberStars")
    private int mNumberStars;

    @ColumnInfo (name = "mGenderGame")
    private String mGenderGame;

    @ColumnInfo (name = "mPathImage")
    private String mPathImage;

    @ColumnInfo (name = "mDate")
    private int mDate;  //Format : 20190325 -> 25 mars 2019

    @ColumnInfo (name = "idComment")
    private int idComment;

    //Constructor
    public Game(String nameGame, String descriptionGame, int numberStars, String genderGame, String pathImage, int date)
    {
        mNameGame = nameGame;
        mDescriptionGame = descriptionGame;
        mNumberStars = numberStars;
        mGenderGame = genderGame;
        mPathImage = pathImage;
        mDate = date;
    }

    //Getters and Setters
    public int getIdGame() {
        return mIdGame;
    }

    public void setIdGame(int idGame) {
        mIdGame = idGame;
    }

    public String getNameGame() {
        return mNameGame;
    }

    public void setNameGame(String nameGame) {
        mNameGame = nameGame;
    }

    public String getDescriptionGame() {
        return mDescriptionGame;
    }

    public void setDescriptionGame(String descriptionGame) {
        mDescriptionGame = descriptionGame;
    }

    public int getNumberStars() {
        return mNumberStars;
    }

    public void setNumberStars(int numberStars) {
        mNumberStars = numberStars;
    }

    public String getGenderGame() {
        return mGenderGame;
    }

    public void setGenderGame(String genderGame) {
        mGenderGame = genderGame;
    }

    public String getPathImage() { return mPathImage; }

    public void setPathImage(String pathImage) {
        mPathImage = pathImage;
    }

    public int getDate() {
        return mDate;
    }

    public void setDate(int date) {
        mDate = date;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        idComment = idComment;
    }
}
