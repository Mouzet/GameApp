package com.example.gameapp.firebase;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.gameapp.entity.Comment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommentListLiveData extends LiveData<Comment> {

    private static final String TAG = "AccountListLiveData";

    private final DatabaseReference reference;
    private final String mIdGame;
    private final MyValueEventListener listener = new MyValueEventListener();

    public CommentListLiveData(DatabaseReference ref, String mIdGame) {
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
            setValue(toComments(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<Comment> toComments(DataSnapshot snapshot) {
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
