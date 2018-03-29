package com.example.android.doc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread myThread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(4597);
                    Intent myintent = new Intent(getApplication(), PaitientDetail.class);
                    startActivity(myintent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        };
        myThread.start();


    }
}
