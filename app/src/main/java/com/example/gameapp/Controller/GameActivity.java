package com.example.gameapp.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gameapp.Controller.DetailsActivity;
import com.example.gameapp.R;

public class GameActivity extends AppCompatActivity{

    private String nameGame;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games);

        Intent intent = getIntent();

        if (intent != null)
        {
            if(intent.hasExtra("nameGame"))
            {
                nameGame = intent.getStringExtra("nameGame");
            }
        }

        TextView textView = (TextView) findViewById(R.id.name_game1);

        textView.setText(nameGame);

    }

    public void modifyGame(View view){
        Intent intent = new Intent(this, DetailsActivity.class);

        startActivity(intent);
    }

}
