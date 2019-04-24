package com.example.gameapp.firebase;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.gameapp.entity.Comment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CommentLiveData extends LiveData<Comment> {

    private static final String TAG = "AccountLiveData";

    private final DatabaseReference reference;
    private final String mIdGame;
    private final CommentLiveData.MyValueEventListener listener = new CommentLiveData.MyValueEventListener();

    public CommentLiveData(DatabaseReference ref){
        reference = ref;
        mIdGame = ref.getParent().getParent().getKey();
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
            Comment comment = dataSnapshot.getValue(Comment.class);
            comment.setIdComment(dataSnapshot.getKey());
            comment.setIdGame(mIdGame);
            setValue(comment);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }
}
