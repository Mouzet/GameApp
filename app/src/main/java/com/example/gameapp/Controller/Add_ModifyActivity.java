package com.example.gameapp.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gameapp.R;

public class Add_ModifyActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
    private EditText editimage;
    private EditText editdate;
    private EditText editdescription;
    private NumberPicker editstars;
    private Spinner editGender;

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

        editstars.setMinValue(1);
        editstars.setMaxValue(5);

        //Spinner for the gender
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this,
                R.array.gender, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editGender.setAdapter(mAdapter);
          editGender.setOnItemSelectedListener(this);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Game");

        //Intent intent = getIntent();

        nameButton= getIntent().getStringExtra("nameButton");
        if(nameButton.equals("modify")){
            editname.setText(getIntent().getStringExtra("name"));
            editdate.setText(getIntent().getStringExtra("date"));
            editdescription.setText(getIntent().getStringExtra("description"));
        }
    }

    private void Save(){
        String name = editname.getText().toString();
        String image = editimage.getText().toString();
        String date = editdate.getText().toString();
        String description = editdescription.getText().toString();
        int stars = editstars.getValue();
        String gender = editGender.toString();

        if(name.trim().isEmpty() || image.trim().isEmpty() || date.trim().isEmpty() || description.trim().isEmpty()){

            Toast.makeText(this, "Please fill all the gaps", Toast.LENGTH_SHORT).show();
        return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_IMAGE, image);
        data.putExtra(EXTRA_DATE, date);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_STARS, stars);
        data.putExtra(EXTRA_GENDER, gender);

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

   /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        // Permission is not granted



        switch(requestCode) {
            case SELECT_PHOTO:

                if (resultCode == RESULT_OK) try {
                    final Uri imageUri = imageReturnedIntent.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    imageView.setImageBitmap(selectedImage);

                    Uri tempUri = imageUri;

                    // CALL THIS METHOD TO GET THE ACTUAL PATH
                    File finalFile = new File(getRealPathFromURI(tempUri));
                    imagePath = finalFile.getAbsoluteFile().toString();
                    newBeverage.setImagepath(finalFile.getAbsoluteFile().toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // CALL THIS METHOD TO GET THE URI FROM THE BITMAP

        }

    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
    */
}
