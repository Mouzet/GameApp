package com.example.gameapp.Controller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gameapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Add_ModifyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final int SELECT_PHOTO = 1;
    public static final String EXTRA_NAME =
            "com.example.gameapp.Controller.EXTRA_NAME";
    public static final String EXTRA_IMAGE =
            "com.example.gameapp.Controller.EXTRA_IMAGE";
    public static final String EXTRA_DATE =
            "com.example.gameapp.Controller.EXTRA_DATE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.gameapp.Controller.EXTRA_DESCRIPTION";
    public static final String EXTRA_STARS =
            "com.example.gameapp.Controller.EXTRA_STARS";
    public static final String EXTRA_GENDER =
            "com.example.gameapp.Controller.EXTRA_GENDER";

    private EditText editname;
    private ImageView editimage;
    private EditText editdate;
    private EditText editdescription;
    private NumberPicker editstars;
    private Spinner editGender;
    //private TextView tidgame;
    private static String imagePath;

    String nameButton;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modify);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editname = findViewById(R.id.name_game);
        editimage = findViewById(R.id.image);
        editdate = findViewById(R.id.date);
        editdescription = findViewById(R.id.description);
        editstars = findViewById(R.id.stars);
        editGender = findViewById(R.id.gender);
        //tidgame = findViewById(R.id.id_game);


        editstars.setMinValue(1);
        editstars.setMaxValue(5);

        //Spinner for the gender
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editGender.setAdapter(mAdapter);
          editGender.setOnItemSelectedListener(this);

        editimage.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                if (ContextCompat.checkSelfPermission(Add_ModifyActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED)
                {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                }

            }
        });


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Game");

        //Intent intent = getIntent();

        nameButton = getIntent().getStringExtra("nameButton");


        if(nameButton.equals("Modify")){
            editname.setText(getIntent().getStringExtra("name"));
            editdate.setText(getIntent().getStringExtra("date"));
            editdescription.setText(getIntent().getStringExtra("description"));
            editimage.setImageURI(Uri.parse(getIntent().getStringExtra("pathimage")));
            //tidgame.setText(getIntent().getStringExtra("idgame"));
            /*String gender = editGender.getSelectedItem().toString();
            mAdapter = (ArrayAdapter) editGender.getAdapter(); //cast to an ArrayAdapter

            int spinnerPosition = mAdapter.getPosition(gender);
            editGender.setSelection(spinnerPosition);*/
            editstars.setValue(Integer.parseInt(getIntent().getStringExtra("stars")));

             setTitle("Modify Game");
        }

    }

    private void Save(){
        String name = editname.getText().toString();
       // String image = textviewimage.getText().toString();
        String date = editdate.getText().toString();
        String description = editdescription.getText().toString();
        int stars = editstars.getValue();
        String gender = editGender.getSelectedItem().toString();
        //String idgame = tidgame.getText().toString();

        if(name.trim().isEmpty() || date.trim().isEmpty() || description.trim().isEmpty()){

            Toast.makeText(this, "Please fill all the gaps", Toast.LENGTH_SHORT).show();
        return;
        }


        Intent data = new Intent();

        if (nameButton.equals("Modify")){
            int test = -1;
            data.putExtra("test",test);
        }

        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_IMAGE, imagePath);
        data.putExtra(EXTRA_DATE, date);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_STARS, stars);
        data.putExtra(EXTRA_GENDER, gender);
        //data.putExtra("idgame", idgame);


        setResult(RESULT_OK,data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);



        switch(requestCode) {
            case SELECT_PHOTO:

                if (resultCode == RESULT_OK) try {
                    final Uri imageUri = imageReturnedIntent.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    editimage.setImageBitmap(selectedImage);

                    Uri tempUri = imageUri;
                    // CALL THIS METHOD TO GET THE ACTUAL PATH
                    File finalFile = new File(getRealPathFromURI(tempUri));
                    imagePath = finalFile.getAbsoluteFile().toString();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


        }

    }
    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

}
