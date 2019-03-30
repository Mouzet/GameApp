package com.example.gameapp.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "comment_table", foreignKeys = @ForeignKey(entity =Game.class, parentColumns = "mIdGame",
        childColumns = "idComment"), indices = {@Index("idComment")})
public class Comment
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo (name="idComment")
    private int idComment;

    @ColumnInfo (name = "mTextComment")
    private String mTextComment;

    @ColumnInfo (name = "mUserComment")
    private String mUserComment;

    @ColumnInfo (name = "idGame")
    private int idGame;

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    //Constructor
    public Comment(String textComment, String userComment, int idGame)
    {
        mTextComment = textComment;
        mUserComment = userComment;
        idGame = idGame;
    }

    //Getters and Setters
    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        idComment = idComment;
    }

    public String getTextComment() {
        return mTextComment;
    }

    public void setTextComment(String textComment) {
        mTextComment = textComment;
    }

    public String getUserComment() {
        return mUserComment;
    }

    public void setUserComment(String userComment) {
        mUserComment = userComment;
    }
}
