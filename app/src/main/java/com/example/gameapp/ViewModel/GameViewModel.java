package com.example.gameapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.gameapp.Model.Game;
import com.example.gameapp.Repositories.GameRepository;

import java.util.List;

public class GameViewModel extends AndroidViewModel
{
    private GameRepository repository;
    private LiveData<List<Game>> allGames;
    private LiveData<List<Game>> researchGame;

    public GameViewModel(@NonNull Application application)
    {
        super(application);
        repository = new GameRepository(application);
        allGames = repository.getAllGames();
        researchGame = repository.getResearchGames();
    }

    public void insert(Game game)
    {
        repository.insert(game);
    }

    public void update(Game game)
    {
        repository.update(game);
    }

    public void delete(Game game)
    {
        repository.delete(game);
    }

    public void deleteAllGames()
    {
        repository.deleteAllGames();
    }

    public LiveData<List<Game>> getAllGames()
    {
        return allGames;
    }

    public LiveData<List<Game>> getResearchGames()
    {
        return researchGame;
    }

}
