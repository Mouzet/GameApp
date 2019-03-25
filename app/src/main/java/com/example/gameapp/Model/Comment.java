package com.example.gameapp.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "comment_table")
public class Comment
{
    @PrimaryKey(autoGenerate = true)
    private int mIdComment;

    private String mTextComment;
    private String mNameAuthorComment;

    //Constructor
    public Comment(String textComment, String nameAuthorComment)
    {
        mTextComment = textComment;
        mNameAuthorComment = nameAuthorComment;
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

    public String getNameAuthorComment() {
        return mNameAuthorComment;
    }

    public void setNameAuthorComment(String nameAuthorComment) {
        mNameAuthorComment = nameAuthorComment;
    }
}
