package com.example.gameapp.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.gameapp.DAO.GameDAO;
import com.example.gameapp.Database.GameDatabase;
import com.example.gameapp.Model.Game;

import java.util.List;

public class GameRepository {
    private GameDAO gameDao;
    private LiveData<List<Game>> allGames;
    private LiveData<List<Game>> researchGames;


    public GameRepository(Application application){
        GameDatabase database = GameDatabase.getInstance(application);

        gameDao = database.gameDao();
        allGames = gameDao.getAllGames();
    }

    //Insert d'un nouveau jeu
    public void insert(Game game)
    {
        new InsertGameAsyncTask(gameDao).execute(game);
    }

    //Modifier le jeu
    public void update(Game game)
    {
        new UpdateGameAsyncTask(gameDao).execute(game);
    }

    //Supprime le jeu
    public void delete(Game game)
    {
        new DeleteGameAsyncTask(gameDao).execute(game);
    }

    //Supprime tous les jeux
    public void deleteAllGames()  {new DeleteAllGamesAsyncTask(gameDao).execute();}

    //Obtiens tous les jeux de la BDD
    public LiveData<List<Game>> getAllGames(){
        return allGames;
    }

    //Obtiens les jeux qui correspondent a la recherche
    public LiveData<List<Game>> getGamesByName(String nameSearch) {return gameDao.getGamesByName(nameSearch);}

    public LiveData<List<Game>> getGamesByGender(String gender){return gameDao.getGamesByGender(gender);}

    public LiveData<List<Game>> getGamesByNameAndGender(String nameSearch, String gender)
    {
        return gameDao.getGamesByNameAndGender(nameSearch, gender);
    }

    private static class InsertGameAsyncTask extends AsyncTask<Game, Void, Void>
    {
        private GameDAO gameDao;

        private InsertGameAsyncTask(GameDAO gameDao){
            this.gameDao = gameDao;
        }

        @Override
        protected Void doInBackground(Game... games)
        {
            gameDao.insert(games[0]);
            return null;
        }
    }

    private static class UpdateGameAsyncTask extends AsyncTask<Game, Void, Void>
    {
        private GameDAO gameDao;

        private UpdateGameAsyncTask(GameDAO gameDao){
            this.gameDao = gameDao;
        }

        @Override
        protected Void doInBackground(Game... games) {
            gameDao.update(games[0]);
            return null;
        }
    }

    private static class DeleteGameAsyncTask extends AsyncTask<Game, Void, Void>
    {
        private GameDAO gameDao;

        private DeleteGameAsyncTask(GameDAO gameDao){
            this.gameDao = gameDao;
        }

        @Override
        protected Void doInBackground(Game... games) {
            gameDao.delete(games[0]);
            return null;
        }
    }

    private static class DeleteAllGamesAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private GameDAO gameDao;

        private DeleteAllGamesAsyncTask(GameDAO gameDao){
            this.gameDao = gameDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            gameDao.deleteAllGames();
            return null;
        }
    }
}
