package com.example.gameapp.Controller;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gameapp.Adapter.GameAdapter;
import com.example.gameapp.Model.Game;
import com.example.gameapp.R;
import com.example.gameapp.ViewModel.GameViewModel;

import java.util.List;

public class GameActivity extends AppCompatActivity
{
    public static final int ADD_GAME =1;
    //Déclaration des viewmodels
    private GameViewModel gameViewModel;

    private String nameGame;
    private String nameButton;
    private String nameSearch;
    private String gender;
   // private static String imagePath;
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
                String nameButton = "Add";
                intent.putExtra("nameButton",nameButton);
                startActivityForResult(intent, ADD_GAME);
            }
        });

        Intent intent = getIntent();

        nameButton = intent.getStringExtra("nameButton");

        //Si l'activité est demarré pour une recherche (par le bouton validate)
        //On effectue donc une recherche
        if(nameButton.equals("validate"))
        {
            RecyclerView recyclerView = findViewById(R.id.recycler_view_game);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);

            //permet d'afficher tant qu'on a la place a l'écran
            final GameAdapter adapter = new GameAdapter();
            recyclerView.setAdapter(adapter);

            //Si on a entré un nom dans la recherche
            if(intent.hasExtra("nameSearch"))
            {
                nameSearch = intent.getStringExtra("nameSearch");

                //Si on a entré un NOM + un GENRE
                if(intent.hasExtra("gender"))
                {
                    gender = intent.getStringExtra("gender");

                    //Si on recherche par NOM + GENRE
                    gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
                    gameViewModel.getGamesByNameAndGender(nameSearch, gender).observe(this, new Observer<List<Game>>() {
                        @Override
                        public void onChanged(@Nullable List<Game> games) {
                            adapter.setGames(games);
                        }
                    });
                }

                //Si on a pas de genre, donc qu'un nom
                else
                {
                    nameSearch = intent.getStringExtra("nameSearch");

                    gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
                    gameViewModel.getGamesByName(nameSearch).observe(this, new Observer<List<Game>>() {
                        @Override
                        public void onChanged(@Nullable List<Game> games) {
                            adapter.setGames(games);
                        }
                    });
                }
            }

            //Si on a entré seulement un genre
            else if(intent.hasExtra("gender"))
            {
                gender = intent.getStringExtra("gender");

                gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
                gameViewModel.getGamesByGender(gender).observe(this, new Observer<List<Game>>() {
                    @Override
                    public void onChanged(@Nullable List<Game> games) {

                        adapter.setGames(games);
                    }
                });
            }
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


    public void showGame(View view){

        Intent intent = new Intent(GameActivity.this, DetailsActivity.class);

        TextView tname = (TextView)findViewById(R.id.name_game);
        TextView tdate = (TextView)findViewById(R.id.date);
        TextView tdescription = (TextView) findViewById(R.id.description);
        TextView tgenre = (TextView) findViewById(R.id.genre);
        ImageView bimage =  findViewById(R.id.buttonimage);
        TextView tstars = (TextView) findViewById(R.id.number);
        TextView path = findViewById(R.id.pathimage);
        //TextView tidgame = findViewById(R.id.id_game);


        String name = tname.getText().toString();
        String date = tdate.getText().toString();
        String description = tdescription.getText().toString();
        String genre = tgenre.getText().toString();
        String stars = tstars.getText().toString();
        String imagePath = path.getText().toString();
        //String idgame = tidgame.getText().toString();

        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        intent.putExtra("name", name);
        intent.putExtra("date",date);
        intent.putExtra("description",description);
        intent.putExtra("imagepath",imagePath);
        intent.putExtra("genre",genre);
        intent.putExtra("stars",stars);
        //intent.putExtra("idgame", idgame);

        Log.i("****** name *******", name);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*int buttonmodify = data.getIntExtra("test",-1);
        int a=0;
        if( a!=-1){*/

    if(requestCode == ADD_GAME  && resultCode == RESULT_OK) {
        String name = data.getStringExtra(Add_ModifyActivity.EXTRA_NAME);
        String gender = data.getStringExtra((Add_ModifyActivity.EXTRA_GENDER));
        String image = data.getStringExtra(Add_ModifyActivity.EXTRA_IMAGE);
        int date = data.getIntExtra(Add_ModifyActivity.EXTRA_DATE, 20100101);
        String description = data.getStringExtra(Add_ModifyActivity.EXTRA_DESCRIPTION);
        int stars = data.getIntExtra(Add_ModifyActivity.EXTRA_STARS, 1);

        Game game = new Game(name, description, stars, gender, image, date);

        gameViewModel.insert(game);

        Log.i("***** PATHIMAGE *****", image);

        Toast.makeText(this, "Game added", Toast.LENGTH_SHORT).show();
    /*}
    else if (a==-1){
        String name = data.getStringExtra(Add_ModifyActivity.EXTRA_NAME);
        String gender = data.getStringExtra((Add_ModifyActivity.EXTRA_GENDER));
        String image = data.getStringExtra(Add_ModifyActivity.EXTRA_IMAGE);
        int date = data.getIntExtra(Add_ModifyActivity.EXTRA_DATE, 20100101);
        String description = data.getStringExtra(Add_ModifyActivity.EXTRA_DESCRIPTION);
        int stars = data.getIntExtra(Add_ModifyActivity.EXTRA_STARS, 1);
        int id = Integer.parseInt(data.getStringExtra("idgame"));

        Game game = new Game(name,description,stars,gender,image,date);
        game.setIdGame(id);

        gameViewModel.update(game);

    }*/
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
    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
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


