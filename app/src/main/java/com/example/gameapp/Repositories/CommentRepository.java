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


    public LiveData<Comment> getAccount(final String midComment) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("games")
                .child("comments")
                .child(midComment);
        return new CommentLiveData(reference);
    }

    public LiveData<List<Comment>> getByIdGame(final String mIdGame) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("games")
                .child(mIdGame)
                .child("comments");
        return new CommentListLiveData(reference, mIdGame);
    }

    public void insert(final Comment comment, final OnAsyncEventListener callback) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("games")
                .child(comment.getIdGame())
                .child("comments");
        String key = reference.push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("games")
                .child(comment.getIdGame())
                .child("comments")
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
                .child("comments")
                .child(comment.getIdComment())
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
                .child(comment.getIdGame())
                .child("comments")
                .child(comment.getIdComment())
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }
}
    /*
    public void transaction(final AccountEntity sender, final AccountEntity recipient,
                            OnAsyncEventListener callback) {
        final DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
        rootReference.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                rootReference
                        .child("clients")
                        .child(sender.getOwner())
                        .child("accounts")
                        .child(sender.getId())
                        .updateChildren(sender.toMap());

                rootReference
                        .child("clients")
                        .child(recipient.getOwner())
                        .child("accounts")
                        .child(recipient.getId())
                        .updateChildren(recipient.toMap());

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                if (databaseError != null) {
                    callback.onFailure(databaseError.toException());
                } else {
                    callback.onSuccess();
                }
            }
        });
    }
*/








