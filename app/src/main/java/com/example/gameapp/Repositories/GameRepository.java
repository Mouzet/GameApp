package com.example.gameapp.Repositories;

import android.arch.lifecycle.LiveData;

import com.example.gameapp.entity.Game;
import com.example.gameapp.firebase.GameCommentsListLiveData;
import com.example.gameapp.firebase.GameListLiveData;
import com.example.gameapp.firebase.GameLiveData;
import com.example.gameapp.pojo.GameWithComments;
import com.example.gameapp.util.OnAsyncEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class GameRepository {

    private static final String TAG = "GameRepository";

    private static GameRepository instance;

    private GameRepository() {
    }

    public static GameRepository getInstance() {
        if (instance == null) {
            synchronized (CommentRepository.class) {
                if (instance == null) {
                    instance = new GameRepository();
                }
            }
        }
        return instance;
    }

    //Get game by id game
    public LiveData<Game> getGame(final String idGame) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("games")
                .child(idGame);
        return new GameLiveData(reference, idGame);
    }

    //Get all games of the bdd
    public LiveData<List<Game>> getAllGames() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("games");
        return new GameListLiveData(reference);
    }

    public LiveData<List<Game>> getGamesByNameAndGender(final String nameSearch, final String gender) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("games")
                .child(nameSearch)
                .child(gender);
        return new GameListLiveData(reference);
    }

    public LiveData<List<Game>> getGamesByName(final String nameSearch) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("games")
                .child(nameSearch);
        return new GameListLiveData(reference);
    }

    public LiveData<List<Game>> getGamesByGender(final String gender) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("games")
                .child(gender);
        return new GameListLiveData(reference);
    }

    public void insert(final Game game, final OnAsyncEventListener callback) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("games");
        String key = reference.push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("games")
                .child(key)
                .setValue(game, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    }

                    else {
                        callback.onSuccess();
                    }
                });
    }

    public void update(final Game game, final OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("games")
                .child(game.getIdGame())
                .updateChildren(game.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    }

                    else {

                        callback.onSuccess();
                    }
                });

    }

    public void delete(final Game game, OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("games")
                .child(game.getIdGame())
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    }

                    else {
                        callback.onSuccess();
                    }
                });
    }
}
