package com.example.gameapp.Repositories;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.gameapp.entity.Game;
import com.example.gameapp.firebase.GameCommentsListLiveData;
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

    public LiveData<Game> getIdGame(final String idGame) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("games")
                .child(idGame);
        return new GameLiveData(reference);
    }

    public LiveData<List<GameWithComments>> getOtherClientsWithAccounts(final String owner) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("games");
        return new GameCommentsListLiveData(reference, mIdGame);
    }

    private void insert(final Game game, final OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("clients")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(game, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                        FirebaseAuth.getInstance().getCurrentUser().delete()
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        callback.onFailure(null);
                                        Log.d(TAG, "Rollback successful: User account deleted");
                                    } else {
                                        callback.onFailure(task.getException());
                                        Log.d(TAG, "Rollback failed: signInWithEmail:failure",
                                                task.getException());
                                    }
                                });
                    } else {
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
                    } else {
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
                    } else {
                        callback.onSuccess();
                    }
                });
    }
}
