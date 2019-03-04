package com.example.gameapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends AppCompatActivity{

    private String name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games);

        Intent intent = getIntent();
    }

    public void modifyGame(View view){
        Intent intent = new Intent(this, DetailsActivity.class);


        startActivity(intent);
    }

}
