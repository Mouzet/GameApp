package com.example.gameapp.Model;

import java.util.List;

public class Game
{
    private String mNameGame;
    private String mDescriptionGame;
    private int mNumberStars;
    private int mDate;


    public Game(String name, String description, int stars, int date)
    {
        this.setNameGame(mNameGame);
        this.setDescriptionGame(mDescriptionGame);
        this.setNumberStars(mNumberStars);
        this.setDate(mDate);
    }

    public int getDate()
    {
        return mDate;
    }

    public void setDate(int date)
    {
        mDate = date;
    }

    public int getNumberStars()
    {
        return mNumberStars;
    }

    public void setNumberStars(int numberStars)
    {
        mNumberStars = numberStars;
    }

    public String getDescriptionGame()
    {
        return mDescriptionGame;
    }

    public void setDescriptionGame(String descriptionGame)
    {
        mDescriptionGame = descriptionGame;
    }

    public String getNameGame()
    {
        return mNameGame;
    }

    public void setNameGame(String nameGame)
    {
        mNameGame = nameGame;
    }


}
