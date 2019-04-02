package com.example.gameapp.Controller;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gameapp.R;

public class DetailsActivity extends AppCompatActivity
{
    public static final int MODIFY_GAME =-1;
    public static final String EXTRA_IDGAME = "com.example.gameapp.EXTRA_IDGAME";
    public static final String EXTRA_NAMEGAME = "com.example.gameapp.EXTRA_NAMEGAME";
    public static final String EXTRA_DESCRIPTIONGAME = "com.example.gameapp.DESCRIPTIONGAME";
    public static final String EXTRA_STARSGAME = "com.example.gameapp.EXTRA_STARSGAME";
    public static final String EXTRA_GENDERGAME = "com.example.gameapp.EXTRA_GENDERGAME";
    public static final String EXTRA_PATHIMAGEGAME = "com.example.gameapp.EXTRA_PATHIMAGEGAME";
    public static final String EXTRA_DATEGAME = "com.example.gameapp.EXTRA_DATEGAME";

    String nameButton;

    private TextView tidgame;
    private TextView tname;
    private TextView tgenre;
    private TextView tdate;
    private TextView tdescription;
    private TextView pathimage;
    private ImageView bimage;
    private TextView bstars;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tidgame = (TextView) findViewById(R.id.id_game_details);
        tname = (TextView) findViewById(R.id.name_game);
        tdate = (TextView) findViewById(R.id.date);
        tdescription = (TextView) findViewById(R.id.description);
        tgenre = (TextView) findViewById(R.id.genre);
        bimage = (ImageView) findViewById(R.id.image);
        bstars = (TextView) findViewById(R.id.stars);
        pathimage = (TextView) findViewById(R.id.imageuri);

        int id = intent.getIntExtra(EXTRA_IDGAME, -1);
        tidgame.setText(Integer.toString(id));
        tname.setText(intent.getStringExtra(EXTRA_NAMEGAME));
        tdescription.setText(intent.getStringExtra(EXTRA_DESCRIPTIONGAME));
        int stars = intent.getIntExtra(EXTRA_STARSGAME, -1);
        bstars.setText(Integer.toString(stars));
        tgenre.setText(intent.getStringExtra(EXTRA_GENDERGAME));
        tdate.setText(intent.getStringExtra(EXTRA_DATEGAME));
        bimage.setImageURI(Uri.parse(intent.getStringExtra(EXTRA_PATHIMAGEGAME)));
        pathimage.setText(intent.getStringExtra(EXTRA_PATHIMAGEGAME));

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

    public void seeComments(View view)
    {
        Intent intent = getIntent();
        Intent intent_comment = new Intent(this, CommentsActivity.class);

        TextView textViewIdGame = findViewById(R.id.id_game_details);

        String idGame =  textViewIdGame.getText().toString();

        intent_comment.putExtra("idGame", idGame);
        startActivity(intent_comment);
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
        //TextView tidgame = (TextView) findViewById(R.id.id_game);

        nameButton="Modify";

        String name = tename.getText().toString();
        String gender = tegender.getText().toString();
        String date = tedate.getText().toString();
        String description = tedescription.getText().toString();
        String button = imagepath.getText().toString();
        String stars = bustars.getText().toString();
        //String idgame = tidgame.getText().toString();

        intent.putExtra("nameButton",nameButton);
        intent.putExtra("name",name);
        intent.putExtra("genre",gender);
        intent.putExtra("date",date);
        intent.putExtra("description",description);
        intent.putExtra("pathimage",button);
        intent.putExtra("stars",stars);
       // intent.putExtra("idgame",idgame);

        startActivity(intent);
        //startActivityForResult(intent, MODIFY_GAME);
    }



}
