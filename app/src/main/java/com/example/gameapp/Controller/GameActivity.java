package com.example.gameapp.Controller;
;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gameapp.Adapter.GameAdapter;
import com.example.gameapp.Controller.DetailsActivity;
import com.example.gameapp.Model.Comment;
import com.example.gameapp.Model.Game;
import com.example.gameapp.R;
import com.example.gameapp.ViewModel.CommentViewModel;
import com.example.gameapp.ViewModel.GameViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity
{
    //Déclaration des viewmodels
    private GameViewModel gameViewModel;
    private CommentViewModel commentViewModel;

    private String nameGame;
    private String nameButton;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_games);

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
        //On effectue donc une recherche
        if(nameButton.equals("validate"))
        {
            //A remplir
        }

        //Si on veut afficher tous les jeux de la base de données
        if(nameButton.equals("navBar"))
        {
            RecyclerView recyclerView = findViewById(R.id.recycler_view_game);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

            final GameAdapter adapter = new GameAdapter();
            recyclerView.setAdapter(adapter);

            gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
            gameViewModel.getAllGames().observe(this, new Observer<List<Game>>() {
                @Override
                public void onChanged(@Nullable List<Game> games) {
                    adapter.setGames(games);
                }
            });

        /*
        commentViewModel = ViewModelProviders.of(this).get(CommentViewModel.class);
        commentViewModel.getAllComments().observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(@Nullable List<Comment> comments) {

            }
        });     */
        }
    }

    public void modifyGame(View view)
    {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }

}
