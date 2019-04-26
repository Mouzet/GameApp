package com.example.gameapp.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gameapp.R;

public class Add_CommentActivity extends AppCompatActivity {

    public static final String EXTRA_USER =
            "com.example.gameapp.Controller.EXTRA_USER";
    public static final String EXTRA_COMMENT =
            "com.example.gameapp.Controller.EXTRA_COMMENT";


    private EditText editTextUser;
    private EditText editTextComment;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comments);

        editTextComment = findViewById(R.id.comment);
        editTextUser = findViewById(R.id.user);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        String nameButton = intent.getStringExtra("button");

        if (nameButton.equals("modify")){
            setTitle("Modify comment");
            editTextUser.setText(intent.getStringExtra("User"));
            editTextComment.setText(intent.getStringExtra("Texte"));
        }
        setTitle("Add comment");
    }

    private void Save(){
        String user = editTextUser.getText().toString();
        String comment = editTextComment.getText().toString();

        if( user.trim().isEmpty() || comment.trim().isEmpty()){
            Toast.makeText(this, "Please insert a username and a comment", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_USER,user);
        data.putExtra(EXTRA_COMMENT,comment);

        setResult(RESULT_OK,data);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuinfalter = getMenuInflater();
        menuinfalter.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                Save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
