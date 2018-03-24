package com.example.android.doc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PaitientDetail extends AppCompatActivity {

    Button submitto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paitient_detail);

        submitto=(Button)findViewById(R.id.submit);
        submitto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(getApplicationContext(),checktheBox.class);
                startActivity(myintent);
            }
        });

    }
}
