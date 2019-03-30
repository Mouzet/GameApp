package com.example.gameapp.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.gameapp.Model.Comment;

import java.util.List;

@Dao
public interface CommentDAO {

  @Insert
    void insert(Comment comment);

  @Update
    void update(Comment comment);

  @Delete
    void delete(Comment comment);

  @Query("DELETE FROM comment_table")
    void deleteAllComments();

  @Query("SELECT * FROM comment_table")
    LiveData<List<Comment>> getAllComments();

  // WHERE idComment =:idComment
  //Requête pour la recherche avec le NOM + le GENRE
  @Query("SELECT * FROM comment_table WHERE idComment = :idComment")
  LiveData<List<Comment>> getCommentById(int idComment);
}
