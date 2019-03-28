package com.example.gameapp.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "comment_table")
public class Comment
{
    @PrimaryKey(autoGenerate = true)
    private int mIdComment;
    private String mTextComment;
    private String mUserComment;

    //Constructor
    public Comment(String textComment, String userComment)
    {
        mTextComment = textComment;
        mUserComment = userComment;
    }

    //Getters and Setters
    public int getIdComment() {
        return mIdComment;
    }

    public void setIdComment(int idComment) {
        mIdComment = idComment;
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
