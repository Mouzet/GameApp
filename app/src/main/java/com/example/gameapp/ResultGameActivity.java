package com.example.gameapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.TextView;

class ResultGameActivity extends AppCompatActivity
{
    private TextView mResultTitlePage;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_games);

        mResultTitlePage = (TextView) findViewById(R.id.result_game_page);

        Intent intent = getIntent();


    }

}
