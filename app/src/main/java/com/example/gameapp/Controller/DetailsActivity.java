package com.example.gameapp.Controller;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.gameapp.R;

public class DetailsActivity extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        TextView textview4 = (TextView) findViewById(R.id.details_page);
        textview4.setPaintFlags(textview4.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextView link = (TextView) findViewById(R.id.facebook);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link2 = (TextView) findViewById(R.id.twitter);
        link2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link3 = (TextView) findViewById(R.id.instagram);
        link3.setMovementMethod(LinkMovementMethod.getInstance());

        Intent intent = getIntent();
    }

    public void seeComments(View view){
        Intent intent = new Intent(this, CommentsActivity.class);
        startActivity(intent);
        TextView idgame = (TextView) findViewById(R.id.name_game);
    }

}
