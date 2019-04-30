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
    private  String nameOrId;
    private  String genre;
    private final GameLiveData.MyValueEventListener listener = new GameLiveData.MyValueEventListener();

    public GameLiveData(DatabaseReference ref, String idOrName) {
        reference = ref;
        nameOrId = idOrName;

    }


    public GameLiveData(DatabaseReference ref, String nom, String gender){
        reference=ref;
        nameOrId=nom;
        genre = gender;
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
            //entity.setIdGame(dataSnapshot.getKey());
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

            if(game.getIdGame().equals(nameOrId))
                return game;
        }

        return null;
    }

    private Game getGamesByName(DataSnapshot snapshot)
    {
        for (DataSnapshot childSnapshot : snapshot.getChildren())
        {
            Game game = childSnapshot.getValue(Game.class);
            game.setIdGame(childSnapshot.getKey());
            Log.i("* GAME ID *", game.getIdGame());

            if(game.getNameGame().equals(nameOrId))
                return game;
        }

        return null;
    }

    private Game getGamesByNameAndGender(DataSnapshot snapshot)
    {
        for (DataSnapshot childSnapshot : snapshot.getChildren())
        {
            Game game = childSnapshot.getValue(Game.class);
            game.setIdGame(childSnapshot.getKey());

            if (game.getNameGame().equals(nameOrId) && game.getGenderGame().equals(genre))
                return game;
        }
        return null;
    }

}
