package com.example.gameapp.Controller;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gameapp.R;

public class DetailsActivity extends AppCompatActivity
{


    String nameButton;

    private TextView tname;
    private TextView tgenre;
    private TextView tdate;
    private TextView tdescription;
    private Button bimage;
    private Button bstars;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tname = (TextView) findViewById(R.id.name_game);
        tdate = (TextView) findViewById(R.id.date);
        tdescription = (TextView) findViewById(R.id.description);
        tgenre = (TextView) findViewById(R.id.genre);
        bimage = (Button) findViewById(R.id.image);
        bstars = (Button) findViewById(R.id.stars);

        tname.setText(getIntent().getStringExtra("name"));
        tdate.setText(getIntent().getStringExtra("date"));
        tdescription.setText(getIntent().getStringExtra("description"));
        tgenre.setText(getIntent().getStringExtra("genre"));
        bimage.setText(getIntent().getStringExtra("imagepath"));
        bstars.setText(getIntent().getStringExtra("stars"));




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

    public void modifyGame(View view)
    {
        Intent intent = new Intent(this, Add_ModifyActivity.class);
        TextView tename = (TextView)  findViewById(R.id.name_game);
        TextView tegender = (TextView) findViewById(R.id.genre);
        TextView tedate = (TextView) findViewById(R.id.date);
        TextView tedescription = (TextView) findViewById(R.id.description);
        Button buimage = (Button) findViewById(R.id.image);
        Button bustars = (Button) findViewById(R.id.stars);


        nameButton="Modify";

        String name = tename.getText().toString();
        String gender = tegender.getText().toString();
        String date = tedate.getText().toString();
        String description = tedescription.getText().toString();
        String button = buimage.getText().toString();
        String stars = bustars.getText().toString();


        intent.putExtra("nameButton",nameButton);
        intent.putExtra("name",name);
        intent.putExtra("genre",gender);
        intent.putExtra("date",date);
        intent.putExtra("description",description);
        intent.putExtra("pathimage",button);
        intent.putExtra("stars",stars);


        startActivity(intent);



    }

}
