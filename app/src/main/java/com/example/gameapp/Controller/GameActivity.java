package com.example.gameapp.Controller;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            gameViewModel.getGamesByName(nameSearch).observe(this, new Observer<List<Game>>() {
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

            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                gameViewModel.delete(adapter.getGameAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(GameActivity.this, "Game deleted", Toast.LENGTH_SHORT).show();
                }
            }).attachToRecyclerView(recyclerView);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuinflater = getMenuInflater();
        menuinflater.inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.delete_games :
                gameViewModel.deleteAllGames();
                Toast.makeText(this, "All games deleted", Toast.LENGTH_SHORT).show();
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }

    }
}


