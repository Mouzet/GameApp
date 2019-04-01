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

    public GameViewModel(@NonNull Application application)
    {
        super(application);
        repository = new GameRepository(application);
        allGames = repository.getAllGames();
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

    public int getIdGame(String nameGame)  {return repository.getIdGame(nameGame);}

    public LiveData<List<Game>> getAllGames()
    {
        return allGames;
    }

    //Retourne le jeu par NOM
    public LiveData<List<Game>> getGamesByName(String nameSearch)
    {
        return repository.getGamesByName(nameSearch);
    }

    //Retourne le jeu par GENRE
    public LiveData<List<Game>> getGamesByGender(String gender)
    {
        return repository.getGamesByGender(gender);
    }

    //Retourne le jeu par NOM + GENRE
    public LiveData<List<Game>> getGamesByNameAndGender(String nameSearch, String gender)
    {
        return repository.getGamesByNameAndGender(nameSearch, gender);
    }

}
