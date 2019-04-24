package com.example.gameapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.gameapp.database.database.Entity.Comment;
import com.example.gameapp.Repositories.CommentRepository;

import java.util.List;

public class CommentViewModel extends AndroidViewModel
{
    private CommentRepository repository;
    private LiveData<List<Comment>> allComments;

    public CommentViewModel(@NonNull Application application)
    {
        super(application);
        repository = new CommentRepository(application);
        allComments = repository.getAllComments();
    }

    public void insert(Comment comment)
    {
        repository.insert(comment);
    }

    public void update(Comment comment)
    {
        repository.update(comment);
    }

    public void delete(Comment comment)
    {
        repository.delete(comment);
    }

    public void deleteAllComments()
    {
        repository.deleteAllComments();
    }

    public LiveData<List<Comment>> getCommentById(int idGame)
    {
        return repository.getCommentById(idGame);
    }

    public LiveData<List<Comment>> getAllComments()
    {
        return allComments;
    }
}
