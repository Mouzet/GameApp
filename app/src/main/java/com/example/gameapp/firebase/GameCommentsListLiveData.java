package com.example.gameapp.firebase;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.gameapp.entity.Comment;
import com.example.gameapp.entity.Game;
import com.example.gameapp.pojo.GameWithComments;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GameCommentsListLiveData extends LiveData<List<GameWithComments>> {

    private static final String TAG = "GameCommentsLiveData";


    private final DatabaseReference reference;
    private final String mIdGame;
    private final GameCommentsListLiveData.MyValueEventListener listener =
            new GameCommentsListLiveData.MyValueEventListener();

    public GameCommentsListLiveData(DatabaseReference ref, String mIdGame) {
        reference = ref;
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
            setValue(toGameWithCommentsList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<GameWithComments> toGameWithCommentsList(DataSnapshot snapshot) {
        List<GameWithComments> gameWithCommentsList = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            if (!childSnapshot.getKey().equals(mIdGame)) {
                GameWithComments gameWithComments = new GameWithComments();
                gameWithComments.game = childSnapshot.getValue(Game.class);
                gameWithComments.game.setIdGame(childSnapshot.getKey());
                gameWithComments.comments = toComments(childSnapshot.child("comments"),
                        childSnapshot.getKey());
                gameWithCommentsList.add(gameWithComments);
            }
        }
        return gameWithCommentsList;
    }

    private List<Comment> toComments(DataSnapshot snapshot, String mIdGame) {
        List<Comment> comments = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            Comment entity = childSnapshot.getValue(Comment.class);
            entity.setIdComment(childSnapshot.getKey());
            entity.setIdGame(mIdGame);
            comments.add(entity);
        }
        return comments;
    }
}
