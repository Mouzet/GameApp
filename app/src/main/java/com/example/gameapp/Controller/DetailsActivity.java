package com.example.gameapp.Controller;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gameapp.R;

public class DetailsActivity extends AppCompatActivity
{

    String nameButton;

    TextView tname;
    TextView tgenre;
    TextView tdate;
    TextView tdescription;
    ImageView timage;
    ImageView tstars;

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

        tname.setText(getIntent().getStringExtra("name"));
        //tgenre.setText(getIntent().getStringExtra("genre"));
        tdate.setText(getIntent().getStringExtra("date"));
        tdescription.setText(getIntent().getStringExtra("description"));
        //timage.
        //tstars.
    }

    public void seeComments(View view){
        Intent intent = new Intent(this, CommentsActivity.class);
        startActivity(intent);
        TextView idgame = (TextView) findViewById(R.id.name_game);
    }

    public void modifyGame(View view)
    {
        Intent intent = new Intent(this, Add_ModifyActivity.class);
        TextView tname = (TextView)  findViewById(R.id.name_game);
        TextView tgender = (TextView) findViewById(R.id.genre);
        TextView tdate = (TextView) findViewById(R.id.date);
        TextView tdescription = (TextView) findViewById(R.id.description);
        ImageView timage = (ImageView) findViewById(R.id.image);
        ImageView tstars = (ImageView) findViewById(R.id.stars);

        nameButton="modify";

        String name = tname.getText().toString();
        String gender = tgender.getText().toString();
        String date = tdate.getText().toString();
        String description = tdescription.getText().toString();

        intent.putExtra("nameButton",nameButton);
        intent.putExtra("name",name);
        intent.putExtra("genre",gender);
        intent.putExtra("date",date);
        intent.putExtra("description",description);
        //intent.putExtra(EXTRA_IMAGE2,image);
        //intent.putExtra(EXTRA_NAME2,name);

        startActivity(intent);



    }

}
