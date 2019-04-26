package com.example.gameapp.firebase;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.gameapp.entity.Game;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class GameLiveData extends LiveData<Game> {


    private static final String TAG = "GameLiveData";

    private final DatabaseReference reference;
    private final String mIdGame;
    private final GameLiveData.MyValueEventListener listener = new GameLiveData.MyValueEventListener();

    public GameLiveData(DatabaseReference ref, String mIdGame) {
        this.reference = ref;
        this.mIdGame = mIdGame;
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
            Game entity = dataSnapshot.getValue(Game.class);
            entity.setIdGame(dataSnapshot.getKey());
            setValue(entity);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    //Fot get one game
    private Game getGame(DataSnapshot snapshot)
    {
        for (DataSnapshot childSnapshot : snapshot.getChildren())
        {
            Game game = childSnapshot.getValue(Game.class);
            game.setIdGame(childSnapshot.getKey());
            Log.i("* GAME ID *", game.getIdGame());

            if(game.getIdGame().equals(mIdGame))
                return game;
        }

        return null;
    }
}
