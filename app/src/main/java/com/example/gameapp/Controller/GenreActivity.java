package com.example.gameapp.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        setContentView(R.layout.genre);

        mGenderTitle = (TextView) findViewById(R.id.gender_title_txt);
        mGenderSelect = (TextView) findViewById(R.id.gender_select_txt);
        mSelectGender = (Button) findViewById(R.id.select_gender_btn);
        mGenderSpinner = (Spinner) findViewById(R.id.gender_select_spinner);

        //Spinner for the gender
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this,
                R.array.genre, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mGenderSpinner.setAdapter(mAdapter);

        mGenderSpinner.setOnItemSelectedListener(this);

        Intent intent = getIntent();


        //Methode lorsqu'on clique sur le bouton search
        mSelectGender.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Démarre la nouvelle activité
                Intent resultGameActivity = new Intent(GenreActivity.this, ResultGameActivity.class);
                startActivity(resultGameActivity);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        //On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        //Showing selected spinner item in toast message for a short time
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
