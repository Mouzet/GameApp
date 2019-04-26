package com.example.gameapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.gameapp.BaseApp;
import com.example.gameapp.Repositories.CommentRepository;
import com.example.gameapp.Repositories.GameRepository;
import com.example.gameapp.entity.Comment;
import com.example.gameapp.entity.Game;
import com.example.gameapp.pojo.GameWithComments;

import java.util.List;

public class GameListViewModel extends AndroidViewModel {

    private static final String TAG = "GameListViewModel";

    private GameRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<Game>> mObservableGames;
    private final MediatorLiveData<Game> mObservableGame;
    private final MediatorLiveData<List<Game>> mObservableGameByName;
    private final MediatorLiveData<List<Game>> mObservableGameByGender;
    private final MediatorLiveData<List<Game>> mObservableGameByNameAndGender;



    public GameListViewModel(@NonNull Application application,
                                final String mIdGame, final String nameGame, final String genderGame, GameRepository gameRepository, CommentRepository commentRepository) {
        super(application);

        mRepository = gameRepository;

        mObservableGames = new MediatorLiveData<>();
        mObservableGame = new MediatorLiveData<>();
        mObservableGameByName = new MediatorLiveData<>();
        mObservableGameByGender = new MediatorLiveData<>();
        mObservableGameByNameAndGender = new MediatorLiveData<>();

        // set by default null, until we get data from the database.
        mObservableGames.setValue(null);
        mObservableGame.setValue(null);
        mObservableGameByName.setValue(null);
        mObservableGameByGender.setValue(null);
        mObservableGameByNameAndGender.setValue(null);

        LiveData<List<Game>> games = mRepository.getAllGames();
        LiveData<Game> game = mRepository.getGame(mIdGame);
        LiveData<List<Game>> gameByName = mRepository.getGamesByName(nameGame);
        LiveData<List<Game>> gameByGender = mRepository.getGamesByGender(genderGame);
        LiveData<List<Game>> gameByNameAndGender = mRepository.getGamesByNameAndGender(nameGame, genderGame);

        // observe the changes of the entities from the database and forward them
        mObservableGames.addSource(games, mObservableGames::setValue);
        mObservableGame.addSource(game, mObservableGame::setValue);
        mObservableGameByName.addSource(gameByName, mObservableGameByName::setValue);
        mObservableGameByGender.addSource(gameByGender, mObservableGameByGender::setValue);
        mObservableGameByNameAndGender.addSource(gameByNameAndGender, mObservableGameByNameAndGender::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final String mIdGame;
        private final String mNameGame;
        private final String mGenderGame;

        private final GameRepository mGameRepository;
        private final CommentRepository mCommentRepository;

        public Factory(@NonNull Application application, String idGame, String nameGame, String genderGame) {
            mApplication = application;
            mIdGame= idGame;
            mNameGame = nameGame;
            mGenderGame = genderGame;
            mGameRepository = ((BaseApp) application).getGameRepository();
            mCommentRepository = ((BaseApp) application).getCommentRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass)
        {
            return (T) new GameListViewModel(mApplication, mIdGame, mNameGame, mGenderGame, mGameRepository, mCommentRepository);
        }
    }

    //Different method that we used
    public LiveData<List<Game>> getAllGames() {
        return mObservableGames;
    }
    public LiveData<Game> getGame() {
        return mObservableGame;
    }
    public LiveData<List<Game>> getGamesByName() {
        return mObservableGameByName;
    }
    public LiveData<List<Game>> getGamesByGender() {
        return mObservableGameByGender;
    }
    public LiveData<List<Game>> getGamesByNameAndGender() {
        return mObservableGameByNameAndGender;
    }


}
