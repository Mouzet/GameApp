package com.example.gameapp.Controller;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.gameapp.Adapter.GameAdapter;
import com.example.gameapp.Model.Game;
import com.example.gameapp.R;
import com.example.gameapp.ViewModel.GameViewModel;

import java.util.List;

;

public class GameActivity extends AppCompatActivity
{
    public static final int ADD_GAME =1;
    //Déclaration des viewmodels
    private GameViewModel gameViewModel;

    private String nameGame;
    private String nameButton;
    private String nameSearch;
    private String gender;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_games);

        FloatingActionButton buttonAdd = findViewById(R.id.add_game);
        buttonAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this,Add_ModifyActivity.class);
                startActivityForResult(intent, ADD_GAME);
            }
        });
        Intent intent = getIntent();

        //Si l'intent contient des variables
        if (intent != null)
        {
            //Si elle contient un nameGame
            if(intent.hasExtra("nameGame"))
            {
                //Prend la valeur
                nameGame = intent.getStringExtra("nameGame");
            }

            //Si elle contient un nameButton
            if(intent.hasExtra("nameButton"))
            {
                nameButton = intent.getStringExtra("nameButton");
            }
        }

        //Si l'activité est demarré pour une recherche (par le bouton validate)
        //On effectue donc une recherche
        if(nameButton.equals("validate"))
        {
            nameSearch = intent.getStringExtra("nameSearch");
            gender = intent.getStringExtra("gender");

            //Toast.makeText(this, nameSearch, Toast.LENGTH_SHORT).show();
            RecyclerView recyclerView = findViewById(R.id.recycler_view_game);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

            //permet d'afficher tant qu'on a la place a l'écran
            final GameAdapter adapter = new GameAdapter();
            recyclerView.setAdapter(adapter);

            gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
            gameViewModel.getResearchGames().observe(this, new Observer<List<Game>>() {
                @Override
                public void onChanged(@Nullable List<Game> games) {
                    adapter.setGames(games);
                }
            });
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
        }
    }

    public void modifyGame(View view)
    {
        Intent intent = new Intent(this, Add_ModifyActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    if(requestCode == ADD_GAME && resultCode == RESULT_OK){
        String name = data.getStringExtra(Add_ModifyActivity.EXTRA_NAME);
        String gender = data.getStringExtra((Add_ModifyActivity.EXTRA_GENDER));
        String image = data.getStringExtra(Add_ModifyActivity.EXTRA_IMAGE);
        int date = data.getIntExtra(Add_ModifyActivity.EXTRA_DATE,20100101);
        String description = data.getStringExtra(Add_ModifyActivity.EXTRA_DESCRIPTION);
        int stars = data.getIntExtra(Add_ModifyActivity.EXTRA_STARS,1);

        Game game = new Game(name,description,stars,gender,image,date);
        //GameViewModel.insert(game);

        Toast.makeText(this, "Game added", Toast.LENGTH_SHORT).show();

    }
    else{
        Toast.makeText(this, "Error,Game not added", Toast.LENGTH_SHORT).show();
    }
    }
}
