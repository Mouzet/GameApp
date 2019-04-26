package com.example.gameapp.firebase;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.gameapp.entity.Comment;
import com.example.gameapp.entity.Game;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GameListLiveData extends LiveData<List<Game>> {

    private static final String TAG = "GameListLiveData";

    private final DatabaseReference reference;
    private final MyValueEventListener listener = new MyValueEventListener();

    public GameListLiveData(DatabaseReference ref) {
        reference = ref;
    }

    @Override
    protected void onActive() {
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        Log.d(TAG, "onInactive");
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(toGames(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<Game> toGames(DataSnapshot snapshot) {
        List<Game> games = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            Game entity = childSnapshot.getValue(Game.class);
            entity.setIdGame(childSnapshot.getKey());
            games.add(entity);
        }
        return games;
    }

}

