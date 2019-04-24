package com.example.gameapp.entity;

import java.util.HashMap;
import java.util.Map;

public class Comment
{

    private String idComment;
    private String mTextComment;
    private String mUserComment;
    private String idGame;


    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public Comment()
    {
    }

    //Getters and Setters
    public String getIdComment() {
        return idComment;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    public String getTextComment() {
        return mTextComment;
    }

    public void setTextComment(String textComment) {
        this.mTextComment = textComment;
    }

    public String getUserComment() {
        return mUserComment;
    }

    public void setUserComment(String userComment) {
        this.mUserComment = userComment;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Comment)) return false;
        Comment o = (Comment) obj;
        return o.getIdComment().equals(this.getIdComment());
    }



    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("user", mUserComment);
        result.put("comment", mTextComment);

        return result;
    }
}
