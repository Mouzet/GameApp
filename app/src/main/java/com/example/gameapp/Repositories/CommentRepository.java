package com.example.gameapp.Repositories;

import android.arch.lifecycle.LiveData;

import com.example.gameapp.entity.Comment;
import com.example.gameapp.firebase.CommentListLiveData;
import com.example.gameapp.firebase.CommentLiveData;
import com.example.gameapp.util.OnAsyncEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class CommentRepository {


    private static CommentRepository instance;

    public static CommentRepository getInstance() {
        if (instance == null) {
            synchronized (CommentRepository.class) {
                if (instance == null) {
                    instance = new CommentRepository();
                }
            }
        }
        return instance;
    }


    public LiveData<Comment> getComment(String mIdComment) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("comments");
        return new CommentLiveData(reference,mIdComment);
    }

    public LiveData<List<Comment>> getCommentsByGameId(final String mIdGame) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("comments")
                .child(mIdGame);
        return new CommentListLiveData(reference, mIdGame);
    }

    public void insert(final Comment comment, final OnAsyncEventListener callback) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("comments")
                .child(comment.getIdGame());
        String key = reference.push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("comments")
                .child(comment.getIdGame())
                .child(key)
                .setValue(comment, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }

    public void update(final Comment comment, OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("games")
                .child(comment.getIdGame())
                .updateChildren(comment.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }

    public void delete(final Comment comment, OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("games")
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }

    public void deleteAllComments(OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("comments")
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









