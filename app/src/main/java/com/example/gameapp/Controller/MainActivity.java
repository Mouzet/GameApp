package com.example.gameapp.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gameapp.R;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Spinner pour choisir le genre
        spinner_gender = (Spinner) findViewById(R.id.gender);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_gender.setAdapter(adapter1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    //Adding the intent for the button on the navbar
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent = getIntent();

        switch(item.getItemId()){
            case R.id.action_games:
                intent = new Intent(MainActivity.this, GameActivity.class);
                String nameButton = "navBar";
                intent.putExtra("nameButton", nameButton);
                MainActivity.this.startActivity(intent);
                break;

            case R.id.action_genre:
                intent = new Intent(MainActivity.this, GenreActivity.class);
                MainActivity.this.startActivity(intent);
                break;

            case R.id.action_about:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                MainActivity.this.startActivity(intent);
                break;
        }

        return true;
    }

    // creating the intent for the validate button
    public void validateSearch(View view)
    {
        Intent intent = new Intent(this, GameActivity.class);
        EditText name = (EditText)findViewById(R.id.name_typed);
        String nameSearch = name.getText().toString();
        String gender = spinner_gender.getSelectedItem().toString();
        String nameButton = "validate";

        //Passe dans l'intent les 2 variables nameButton + nameGame
        intent.putExtra("nameButton", nameButton);

        //Si le nameSearch contient quelque chose
        if(!"null".equals(nameSearch))
        {
            intent.putExtra("nameSearch",  nameSearch);
        }

        //Si on choisit un genre en particulier, et pas "All genders"
        if(!"All genders".equals(gender))
        {
            //On transmet l'information du genre
            intent.putExtra("gender",  gender);
        }

        startActivity(intent);
    }


    public void modifyGame(View view)
    {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }

}

