package com.example.gameapp;

import android.app.Application;

import com.example.gameapp.Repositories.CommentRepository;
import com.example.gameapp.Repositories.GameRepository;

public class BaseApp extends Application {

    public CommentRepository getCommentRepository() {
        return CommentRepository.getInstance();
    }

    public GameRepository getGameRepository() {
        return GameRepository.getInstance();
    }
}