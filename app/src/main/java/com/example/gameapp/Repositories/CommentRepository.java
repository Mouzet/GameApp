package com.example.gameapp.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.gameapp.DAO.CommentDAO;
import com.example.gameapp.Database.GameDatabase;
import com.example.gameapp.Model.Comment;

import java.util.List;

public class CommentRepository {
    private CommentDAO commentDao;
    private LiveData<List<Comment>> allComments;


    public CommentRepository(Application application){
        GameDatabase database = GameDatabase.getInstance(application);

        commentDao = database.commentDao();
        allComments = commentDao.getAllComments();
    }

    public void insert(Comment comment)
    {
        new InsertCommentAsyncTask(commentDao).execute(comment);
    }

    public void update(Comment comment)
    {
        new UpdateCommentAsyncTask(commentDao).execute(comment);
    }

    public void delete(Comment comment)
    {
        new DeleteCommentAsyncTask(commentDao).execute(comment);
    }

    public void deleteAllComments()
    {
        new DeleteAllCommentAsyncTask(commentDao).execute();
    }

    public LiveData<List<Comment>> getAllComments(){
        return allComments;
    }

    private static class InsertCommentAsyncTask extends AsyncTask<Comment, Void, Void>
    {
        private CommentDAO commentDao;

        private InsertCommentAsyncTask(CommentDAO commentDao){
            this.commentDao = commentDao;
        }

        @Override
        protected Void doInBackground(Comment... comments) {
            commentDao.insert(comments[0]);
            return null;
        }
    }

    private static class UpdateCommentAsyncTask extends AsyncTask<Comment, Void, Void>
    {
        private CommentDAO commentDao;

        private UpdateCommentAsyncTask(CommentDAO commentDao){
            this.commentDao = commentDao;
        }

        @Override
        protected Void doInBackground(Comment... comments) {
            commentDao.update(comments[0]);
            return null;
        }
    }

    private static class DeleteCommentAsyncTask extends AsyncTask<Comment, Void, Void>
    {
        private CommentDAO commentDao;

        private DeleteCommentAsyncTask(CommentDAO commentDao){
            this.commentDao = commentDao;
        }

        @Override
        protected Void doInBackground(Comment... comments) {
            commentDao.delete(comments[0]);
            return null;
        }
    }

    private static class DeleteAllCommentAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private CommentDAO commentDao;

        private DeleteAllCommentAsyncTask(CommentDAO commentDao)
        {
            this.commentDao = commentDao;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            commentDao.deleteAllComments();
            return null;
        }
    }
}
