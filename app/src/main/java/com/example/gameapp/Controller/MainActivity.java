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
import android.widget.Button;

import com.example.gameapp.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Coding the first spinner (genre)
        Spinner spinner1 = (Spinner) findViewById(R.id.genre);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.genre, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);

        //Coding the second spinner (nb_etoile)
        Spinner spinner2 = (Spinner) findViewById(R.id.nb_etoile);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.nb_etoile, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner2.setAdapter(adapter2);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        String nameGame = name.getText().toString();
        String nameButton = ((Button)view).getText().toString();
        //Passe dans la méthode GameName
        intent.putExtra("nameButton", nameButton);
        intent.putExtra("nameGame",  nameGame);
        startActivity(intent);
    }


    public void modifyGame(View view){
        Intent intent = new Intent(this, DetailsActivity.class);

        startActivity(intent);
    }

}
