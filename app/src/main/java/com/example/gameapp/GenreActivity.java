package com.example.gameapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

public class GenreActivity extends AppCompatActivity
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


        //Methode lorsqu'on clique sur le bouton search
        mSelectGender.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                //Démarre la nouvelle activité
                Intent resultGameActivity = new Intent(GenreActivity.this, ResultGameActivity.class);
                startActivity(resultGameActivity);

            }
        });

    }

}
