package com.example.rohan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rohan.myapplication.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void clickedJava(View view) {
        Intent messageIntent=new Intent(getApplicationContext(),Message.class);
        startActivity(messageIntent);
    }
}
