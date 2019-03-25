package com.example.gameapp.Model;

import java.util.List;

public class GameBank
{
    private List<Game> mResultList;
    private int mNextGameIndex;

    public GameBank(List<Game> resultList)
    {
        mResultList = resultList;
        mNextGameIndex = 0;
    }

    public Game getGame()
    {
        if(mNextGameIndex == mResultList.size())
        {
            mNextGameIndex = 0;
        }

        return mResultList.get(mNextGameIndex++);
    }
}
