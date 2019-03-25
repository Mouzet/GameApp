package com.example.gameapp.Controller;
;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gameapp.Controller.DetailsActivity;
import com.example.gameapp.R;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity{

    private String nameGame;
    private String nameButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games);

        TextView mPageTitle = (TextView) findViewById(R.id.game_page);
        ImageView mImage = (ImageView) findViewById(R.id.image);
        TextView mNameGame = (TextView) findViewById(R.id.name_game);
        TextView mDescription = (TextView) findViewById(R.id.description);
        ImageView mStars = (ImageView) findViewById(R.id.stars);
        TextView mDate = (TextView) findViewById(R.id.date);

        Intent intent = getIntent();

        if (intent != null)
        {
            if(intent.hasExtra("nameGame"))
            {
                nameGame = intent.getStringExtra("nameGame");
            }

            if(intent.hasExtra("nameButton"))
            {
                nameButton = intent.getStringExtra("nameButton");
            }
        }

        //Si l'activité est demarré pour une recherche (par le bouton validate)
        if(nameButton.equals("validate")) {
            String[] arrayList = getResources().getStringArray(R.array.gamesList);
            boolean test = true;

            for (int i = 0; i < arrayList.length; i++)
            {
                if (nameGame.equals(arrayList[i]))
                {
                    test = false;
                    mNameGame.setText(nameGame);
                }
            }

            //Si le jeu n'est pas dans la tableau, on affiche null
            //Et on désactive le reste
            if (test) {
                mPageTitle.setText("No result found");
                mNameGame.setText("Sorry, but we don't found anything with the research : " + nameGame);
                mImage.setEnabled(false);
                mDescription.setEnabled(false);
                mStars.setEnabled(false);
                mDate.setEnabled(false);
            }
        }

        //Si on clique sur "Game" dans la navBar
        if(nameButton.equals("navBar"))
        {
            String[] arrayList = getResources().getStringArray(R.array.gamesList);

            for (int i = 0; i < arrayList.length; i++)
            {
                if (nameGame.equals(arrayList[i]))
                {
                    mNameGame.setText(nameGame);
                }
            }
        }
    }

    public void modifyGame(View view)
    {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }

}
