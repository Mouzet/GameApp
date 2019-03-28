package com.example.gameapp.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.gameapp.R;

public class GenreActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private TextView mGenderTitle;
    private TextView mGenderSelect;
    private Button mSelectGender;
    private Spinner mGenderSpinner;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mGenderTitle = (TextView) findViewById(R.id.gender_title_txt);
        mGenderSelect = (TextView) findViewById(R.id.gender_select_txt);
        mSelectGender = (Button) findViewById(R.id.gender_btn);
        mGenderSpinner = (Spinner) findViewById(R.id.gender_select_spinner);

        //Spinner for the gender
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenderSpinner.setAdapter(mAdapter);
        mGenderSpinner.setOnItemSelectedListener(this);

        Intent intent = getIntent();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //Quand on sélectionne l'item
    }

    // creating the intent for the gender button
    public void genderSearch(View view)
    {
        Intent intent = new Intent(this, GameActivity.class);
        String gender = mGenderSpinner.getSelectedItem().toString();
        String nameButton = "validate";

        //Passe dans l'intent les 2 variables nameButton + nameGame
        intent.putExtra("nameButton", nameButton);

        //Il faut sélectionner un genre
        if(gender.equals("All genders"))
        {
            Toast.makeText(this, "Please enter a research", Toast.LENGTH_SHORT).show();
        }

        else
        {
            //Si on choisit un genre en particulier, et pas "All genders"
            if(!"All genders".equals(gender))
            {
                //On transmet l'information du genre
                intent.putExtra("gender",  gender);
            }

            startActivity(intent);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
