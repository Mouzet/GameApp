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


    public GameRepository(Application application){
        GameDatabase database = GameDatabase.getInstance(application);

        gameDao = database.gameDao();
        allGames = gameDao.getAllGames();
    }

    public void insert(Game game)
    {
        new InsertGameAsyncTask(gameDao).execute(game);
    }

    public void update(Game game)
    {
        new UpdateGameAsyncTask(gameDao).execute(game);
    }

    public void delete(Game game)
    {
        new DeleteGameAsyncTask(gameDao).execute(game);
    }

    public void deleteAllGames()
    {
        new DeleteAllGameAsyncTask(gameDao).execute();
    }

    public LiveData<List<Game>> getAllGames(){
        return allGames;
    }

    private static class InsertGameAsyncTask extends AsyncTask<Game, Void, Void>
    {
        private GameDAO gameDao;

        private InsertGameAsyncTask(GameDAO gameDao){
            this.gameDao = gameDao;
        }

        @Override
        protected Void doInBackground(Game... games) {
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

    private static class DeleteAllGameAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private GameDAO gameDao;

        private DeleteAllGameAsyncTask(GameDAO gameDao){
            this.gameDao = gameDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            gameDao.deleteAllGames();
            return null;
        }
    }
}
