package com.example.android.doc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class PaitientDetail extends AppCompatActivity {

    int REQUEST_IMAGE_CAPTURE=1;
    Button submitto;
    ImageView imageSelector;
    ImageView mimg;
    EditText nameofpat;
    Spinner gender;
    Spinner subpart;
    EditText ageofpat;
    Bitmap imageBitmap;
    ArrayList<customDatatype> basicdetail=new ArrayList<customDatatype>();
    ArrayList<Bitmap> img=new ArrayList<Bitmap>();
    customDatatype cs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paitient_detail);
        nameofpat=(EditText)findViewById(R.id.nameof);
        ageofpat=(EditText)findViewById(R.id.ageof);
        gender=(Spinner)findViewById(R.id.spin_gender);
        subpart=(Spinner)findViewById(R.id.spin_problem);
        mimg=(ImageView)findViewById(R.id.setpic);
        imageSelector=(ImageView)findViewById(R.id.media);
        //imageSelector.setImageResource(R.drawable.add);
        imageSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        submitto = (Button) findViewById(R.id.submit);
        submitto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mimg.getResources()==null) {
                    cs = new customDatatype(nameofpat.getText().toString(), ageofpat.getText().toString(), gender.getSelectedItem().toString(), subpart.getSelectedItem().toString());
                    basicdetail.add(cs);
                }
                else
                {
                    cs = new customDatatype(nameofpat.getText().toString(), ageofpat.getText().toString(), gender.getSelectedItem().toString(), subpart.getSelectedItem().toString());
                    basicdetail.add(cs);
                    img.add(imageBitmap);
                }
               Intent myintent = new Intent(getApplicationContext(), mainChatUI.class);
               startActivity(myintent);
            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            mimg.setImageBitmap(imageBitmap);
        }
    }
}
