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

import com.example.gameapp.Adapter.RecyclerAdapter;
import com.example.gameapp.R;
import com.example.gameapp.ViewModel.CommentViewModel;
import com.example.gameapp.ViewModel.GameListViewModel;
import com.example.gameapp.ViewModel.GameViewModel;
import com.example.gameapp.entity.Game;
import com.example.gameapp.util.OnAsyncEventListener;
import com.example.gameapp.util.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity
{
    private static final String TAF = "GameActivity";

    public static final int ADD_GAME =1;
    public static final int UPDATE_GAME =1;
    public static final int DELETE_GAME =1;

    private String idGame;
    private String nameGame;
    private String nameButton;
    private String nameSearch;
    private String gender;


    private List<Game> games;
    private RecyclerAdapter<Game> gameAdapter;
    private GameListViewModel gameListViewModel;
    private GameViewModel gameViewModel;
    private CommentViewModel commentViewModel;

    private Game game;

   // private static String imagePath;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_games);

        Intent intent = getIntent();



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton buttonAdd = findViewById(R.id.add_game);

        games = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recycler_view_game);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final Intent intent_details = new Intent(this, DetailsActivity.class);

        gameAdapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener()
        {
            @Override
            public void onItemClick(View v, int position)
            {
                intent_details.putExtra(DetailsActivity.EXTRA_IDGAME, games.get(position).getIdGame());
                intent_details.putExtra(DetailsActivity.EXTRA_NAMEGAME, games.get(position).getNameGame());
                intent_details.putExtra(DetailsActivity.EXTRA_DESCRIPTIONGAME, games.get(position).getDescriptionGame());
                intent_details.putExtra(DetailsActivity.EXTRA_STARSGAME, games.get(position).getNumberStars());
                intent_details.putExtra(DetailsActivity.EXTRA_GENDERGAME, games.get(position).getGenderGame());
                intent_details.putExtra(DetailsActivity.EXTRA_PATHIMAGEGAME, games.get(position).getPathImage());
                intent_details.putExtra(DetailsActivity.EXTRA_DATEGAME, games.get(position).getDate());
            }
        });

        GameListViewModel.Factory factory = new GameListViewModel.Factory(
                getApplication(),idGame,nameGame,gender
        );
        gameListViewModel = ViewModelProviders.of(this, factory).get(GameListViewModel.class);
        gameListViewModel.getGame().observe(this, gameEntities -> {
            if (gameEntities != null) {
                game =  gameEntities;
                gameAdapter.setData(games);
            }
        });

        recyclerView.setAdapter(gameAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(GameActivity.this,Add_ModifyActivity.class);
                String nameButton = "Add";
                intent.putExtra("nameButton",nameButton);
                startActivityForResult(intent, ADD_GAME);
            }
        });

        nameButton = intent.getStringExtra("nameButton");

        //Si l'activité est demarré pour une recherche (par le bouton validate)
        //On effectue donc une recherche
        if(nameButton.equals("validate"))
        {
            //permet d'afficher tant qu'on a la place a l'écran
            //recyclerView.setAdapter(adapter);

            //Si on a entré un nom dans la recherche
            if(intent.hasExtra("nameSearch"))
            {
                nameSearch = intent.getStringExtra("nameSearch");

                //Si on a entré un NOM + un GENRE
                if(intent.hasExtra("gender"))
                {
                    gender = intent.getStringExtra("gender");

                    //Si on recherche par NOM + GENRE
                    gameListViewModel = ViewModelProviders.of(this, factory).get(GameListViewModel.class);
                    gameListViewModel.getGamesByNameAndGender().observe(this, new Observer<List<Game>>() {
                        @Override
                        public void onChanged(@Nullable List<Game> games) {
                            gameAdapter.setData(games);
                        }
                    });
                }

                //Si on a pas de genre, donc qu'un nom
                else
                {
                    nameSearch = intent.getStringExtra("nameSearch");

                    gameListViewModel = ViewModelProviders.of(this).get(GameListViewModel.class);
                    gameListViewModel.getGamesByName().observe(this, new Observer<List<Game>>() {
                        @Override
                        public void onChanged(@Nullable List<Game> games) {
                            gameAdapter.setData(games);
                        }
                    });
                }
            }

            //Si on a entré seulement un genre
            else if(intent.hasExtra("gender"))
            {
                gender = intent.getStringExtra("gender");

                gameListViewModel = ViewModelProviders.of(this).get(GameListViewModel.class);
                gameListViewModel.getGamesByGender().observe(this, new Observer<List<Game>>() {
                    @Override
                    public void onChanged(@Nullable List<Game> games) {

                        gameAdapter.setData(games);
                    }
                });
            }
        }

        //Si on veut afficher tous les jeux de la base de données
        if(nameButton.equals("navBar"))
        {
           // final GameAdapter adapter = new GameAdapter();
            recyclerView.setAdapter(gameAdapter);

            gameListViewModel = ViewModelProviders.of(this).get(GameListViewModel.class);
            gameListViewModel.getAllGames().observe(this, new Observer<List<Game>>() {
                @Override
                public void onChanged(@Nullable List<Game> games) {
                    gameAdapter.setData(games);
                }
            });

            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i)
                {
                    gameViewModel.deleteGame(game, new OnAsyncEventListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(GameActivity.this, "Game deleted", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(GameActivity.this, "ERROR : Game not deleted", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Toast.makeText(GameActivity.this, "Game deleted", Toast.LENGTH_SHORT).show();
                }
            }).attachToRecyclerView(recyclerView);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_GAME && resultCode == RESULT_OK) {
            String name = data.getStringExtra(Add_ModifyActivity.EXTRA_NAME);
            String gender = data.getStringExtra((Add_ModifyActivity.EXTRA_GENDER));
            String image = data.getStringExtra(Add_ModifyActivity.EXTRA_IMAGE);
            int date = data.getIntExtra(Add_ModifyActivity.EXTRA_DATE, 20100101);
            String description = data.getStringExtra(Add_ModifyActivity.EXTRA_DESCRIPTION);
            int stars = data.getIntExtra(Add_ModifyActivity.EXTRA_STARS, 1);

            Game game = new Game(name,description,stars,gender,date);

            gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
            gameViewModel.insertGame(game, new OnAsyncEventListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(GameActivity.this, "Game inserted", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(GameActivity.this, "Error Game not inserted", Toast.LENGTH_SHORT).show();
                }
            });
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

        switch(item.getItemId())
        {
            case R.id.delete_games :
                gameViewModel.deleteAllGames(new OnAsyncEventListener() {
                    @Override
                    public void onSuccess() {}

                    @Override
                    public void onFailure(Exception e) {}
                });

                commentViewModel.deleteAllComments(new OnAsyncEventListener() {
                    @Override
                    public void onSuccess() {}

                    @Override
                    public void onFailure(Exception e) {}
                });

                Toast.makeText(this, "All games deleted", Toast.LENGTH_SHORT).show();
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }
}


