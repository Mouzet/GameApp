package com.example.gameapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.gameapp.BaseApp;
import com.example.gameapp.Repositories.GameRepository;
import com.example.gameapp.entity.Game;
import com.example.gameapp.util.OnAsyncEventListener;

public class GameViewModel extends AndroidViewModel
{
        private static final String TAG = "AccountViewModel";

        private GameRepository repository;

        // MediatorLiveData can observe other LiveData objects and react on their emissions.
        private final MediatorLiveData<Game> mObservableGame;

        public GameViewModel(@NonNull Application application,
                               final String gameId, GameRepository gameRepository) {
            super(application);

            repository = gameRepository;

            mObservableGame = new MediatorLiveData<>();
            // set by default null, until we get data from the database.
            mObservableGame.setValue(null);

            LiveData<Game> game = repository.getGame(gameId);

            // observe the changes of the client entity from the database and forward them
            mObservableGame.addSource(game, mObservableGame::setValue);
        }

public static class Factory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    private final Application application;

    private final String gameId;

    private final GameRepository repository;

    public Factory(@NonNull Application application, String gameId) {
        this.application = application;
        this.gameId = gameId;
        repository = ((BaseApp) application).getGameRepository();
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new GameViewModel(application, gameId, repository);
    }
}

    //Different method that we used
    public LiveData<Game> getGame() {
        return mObservableGame;
    }

    public void getAllGames() {
        ((BaseApp) getApplication()).getGameRepository()
                .getAllGames();
    }

    public void getGamesByNameAndGender(String name, String gender) {
        ((BaseApp) getApplication()).getGameRepository()
                .getGamesByNameAndGender(name, gender);
    }
    public void getGamesByName(String name) {
        ((BaseApp) getApplication()).getGameRepository()
                .getGamesByName(name);
    }
    public void getGamesByGender(String gender) {
        ((BaseApp) getApplication()).getGameRepository()
                .getGamesByGender(gender);
    }

    public void insertGame(Game game, OnAsyncEventListener callback) {
        ((BaseApp) getApplication()).getGameRepository()
                .insert(game, callback);
    }

    public void updateGame(Game game, OnAsyncEventListener callback) {
        ((BaseApp) getApplication()).getGameRepository()
                .update(game, callback);
    }

    public void deleteGame(Game game, OnAsyncEventListener callback) {
        ((BaseApp) getApplication()).getGameRepository()
                .delete(game, callback);
    }

    //Delete all the games of the bdd
    public void deleteAllGames(OnAsyncEventListener callback) {
        ((BaseApp) getApplication()).getGameRepository()
                .deleteAllGames(callback);
    }

}
