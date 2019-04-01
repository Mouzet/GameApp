package com.example.gameapp.Controller;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gameapp.R;

public class DetailsActivity extends AppCompatActivity
{

    String nameButton;

    private TextView tname;
    private TextView tgenre;
    private TextView tdate;
    private TextView tdescription;
    private TextView pathimage;
    private ImageView bimage;
    private TextView bstars;
    private String name;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tname = (TextView) findViewById(R.id.name_game);
        tdate = (TextView) findViewById(R.id.date);
        tdescription = (TextView) findViewById(R.id.description);
        tgenre = (TextView) findViewById(R.id.genre);
        bimage = (ImageView) findViewById(R.id.image);
        bstars = (TextView) findViewById(R.id.stars);
        pathimage = (TextView) findViewById(R.id.imageuri);

        tname.setText(getIntent().getStringExtra("name"));
        tdate.setText(getIntent().getStringExtra("date"));
        tdescription.setText(getIntent().getStringExtra("description"));
        tgenre.setText(getIntent().getStringExtra("genre"));
        bimage.setImageURI(Uri.parse(getIntent().getStringExtra("imagepath")));
        bstars.setText(getIntent().getStringExtra("stars"));
        pathimage.setText(getIntent().getStringExtra("imagepath"));

        name = intent.getStringExtra("name");

        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        Typeface blockFonts = Typeface.createFromAsset(getAssets(),"MAXWELL BOLD.ttf");
        TextView txtSampleTxt = (TextView) findViewById(R.id.details_page);
        txtSampleTxt.setTypeface(blockFonts);


        TextView textview4 = (TextView) findViewById(R.id.details_page);
        textview4.setPaintFlags(textview4.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TextView link = (TextView) findViewById(R.id.facebook);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link2 = (TextView) findViewById(R.id.twitter);
        link2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link3 = (TextView) findViewById(R.id.instagram);
        link3.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void seeComments(View view){
        Intent intent = new Intent(this, CommentsActivity.class);

        Toast.makeText(this, "spuTest123", Toast.LENGTH_SHORT).show();
        TextView textViewIdGame = (TextView)findViewById(R.id.id_game);
       textViewIdGame.setText(getIntent().getStringExtra("idgame"));
        int idGame =  Integer.parseInt(textViewIdGame.getText().toString());

        Toast.makeText(this, "idGame : " + idGame, Toast.LENGTH_SHORT).show();

        intent.putExtra("idGame", idGame);
        startActivity(intent);
    }

    public void modifyGame(View view)
    {
        Intent intent = new Intent(this, Add_ModifyActivity.class);
        TextView tename = (TextView)  findViewById(R.id.name_game);
        TextView tegender = (TextView) findViewById(R.id.genre);
        TextView tedate = (TextView) findViewById(R.id.date);
        TextView tedescription = (TextView) findViewById(R.id.description);
        ImageView buimage = (ImageView) findViewById(R.id.image);
        TextView imagepath = (TextView) findViewById(R.id.imageuri);
        TextView bustars = (TextView) findViewById(R.id.stars);


        nameButton="Modify";

        String name = tename.getText().toString();
        String gender = tegender.getText().toString();
        String date = tedate.getText().toString();
        String description = tedescription.getText().toString();
        String button = imagepath.getText().toString();
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
