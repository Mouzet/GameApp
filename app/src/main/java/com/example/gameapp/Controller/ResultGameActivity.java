package com.example.gameapp.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gameapp.Model.Game;
import com.example.gameapp.Model.GameBank;
import com.example.gameapp.R;

import java.util.Arrays;

class ResultGameActivity extends AppCompatActivity
{
    private TextView mResultTitlePage;
    private ImageView mImage;
    private TextView mNameGame;
    private TextView mDescriptionGame;
    private ImageView mNumberStars;
    private TextView mDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_games);

        mResultTitlePage = (TextView) findViewById(R.id.result_game_page);
       /* mImage = (ImageView) findViewById(R.id.image);
        mNameGame = (TextView) findViewById(R.id.name);
        mDescriptionGame = (TextView) findViewById(R.id.description);
        mNumberStars = (ImageView) findViewById(R.id.stars);
        mDate = (TextView) findViewById(R.id.date_1); */

        Intent intent = getIntent();

/*
        //Quand on clique sur l'image
        mImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Démarre la nouvelle activité
                Intent specialGameActivity = new Intent(ResultGameActivity.this, SpecialGameActivity.class);
                startActivity(specialGameActivity);
            }
        });
    }

    private GameBank generateGames()
    {
        Game game1 = new Game("PacMan", "Description", 3, 120318 );
        Game game2 = new Game("Pokemon", "Description",4, 280416 );
        Game game3 = new Game("Word Of Warcraft", "Description", 2, 161195);

        displayGame(game1);
        displayGame(game2);
        displayGame(game3);

        return null;
    }

    private void displayGame(final Game game)
    {
        mImage.getDrawable();
        mNameGame.setText(game.getNameGame());
        mDescriptionGame.setText(game.getDescriptionGame());
        mNumberStars.getDrawable();
        mDate.setText(game.getDate());
    }

    @Override
    public void onClick(View v)
    {

    }

    */
    }
}
