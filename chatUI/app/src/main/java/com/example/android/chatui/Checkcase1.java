package com.example.android.chatui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Checkcase1 extends AppCompatActivity {
    Button pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkcase1);
        pressed=(Button)findViewById(R.id.submit);
        pressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"I am being pressed",Toast.LENGTH_LONG).show();
            }
        });
    }

}
