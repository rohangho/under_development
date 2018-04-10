package com.example.android.awsomeui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Bundle bundle=new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentone fragone = new fragmentone();
        fragone.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
         fragmentManager.beginTransaction().add(R.id.fragment_one,fragone).commit();


    }
}
