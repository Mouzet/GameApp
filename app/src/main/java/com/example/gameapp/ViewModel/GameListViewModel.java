package com.example.gameapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.example.gameapp.Repositories.CommentRepository;
import com.example.gameapp.Repositories.GameRepository;
import com.example.gameapp.entity.Comment;
import com.example.gameapp.entity.Game;
import com.example.gameapp.pojo.GameWithComments;

import java.util.List;

public class GameListViewModel extends AndroidViewModel {

    private static final String TAG = "GameListViewModel";

    private CommentRepository mRepository;

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
  //  private final MediatorLiveData<List<GameWithComments>> mObservableGameComments;
    private final MediatorLiveData<List<Game>> mObservableOwnGames;


    public GameListViewModel(@NonNull Application application,
                                final String mIdGame, GameRepository gameRepository, CommentRepository commentRepository) {
        super(application);

        mRepository = commentRepository;

      //  mObservableGameComments = new MediatorLiveData<>();
        mObservableOwnGames = new MediatorLiveData<>();

        // set by default null, until we get data from the database.
        //mObservableGameComments.setValue(null);
        mObservableOwnGames.setValue(null);

        //LiveData<List<GameWithComments>> gameComments = gameRepository.getOtherGamesWithComments(mIdGame);
        LiveData<List<Game>> ownGames = mRepository.getByIdGame(mIdGame);

        // observe the changes of the entities from the database and forward them
       // mObservableGameComments.addSource(gameComments, mObservableGameComments::setValue);
        mObservableOwnGames.addSource(ownGames, mObservableOwnGames::setValue);
    }
}
