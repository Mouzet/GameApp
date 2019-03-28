package com.example.gameapp.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.gameapp.R;

public class CommentsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    }
