package com.example.rohan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rohan.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    TextView pressedJAVA;
    TextView pressedC;
    TextView pressedCSharpe;
    TextView pressedJavascript;
    TextView pressedScala;
    TextView pressedPython;
    Button pressedLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pressedJAVA=(TextView)findViewById(R.id.java);
        pressedC=(TextView)findViewById(R.id.c);
        pressedCSharpe=(TextView)findViewById(R.id.CSharpe);
        pressedJavascript=(TextView)findViewById(R.id.javascript);
        pressedScala=(TextView)findViewById(R.id.scala);
        pressedPython=(TextView)findViewById(R.id.python);
        pressedLogOut=(Button)findViewById(R.id.logout);
        pressedLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent i1=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i1);
               // FirebaseAuth.getInstance().signOut();
            }
        });

    }

    public void clickedJava(View view) {
        Intent messageIntent=new Intent(getApplicationContext(),Message.class);
        messageIntent.putExtra("Type_Selected",pressedJAVA.getText().toString());
        startActivity(messageIntent);
    }

    public void clickedScala(View view) {
        Intent messageIntent=new Intent(getApplicationContext(),Message.class);
        messageIntent.putExtra("Type_Selected",pressedScala.getText().toString());
        startActivity(messageIntent);
    }

    public void clickedCSharpe(View view) {
        Intent messageIntent=new Intent(getApplicationContext(),Message.class);
        messageIntent.putExtra("Type_Selected",pressedCSharpe.getText().toString());
        startActivity(messageIntent);
    }

    public void clickedC(View view) {
        Intent messageIntent=new Intent(getApplicationContext(),Message.class);
        messageIntent.putExtra("Type_Selected",pressedC.getText().toString());
        startActivity(messageIntent);
    }

    public void clickedPython(View view) {
        Intent messageIntent=new Intent(getApplicationContext(),Message.class);
        messageIntent.putExtra("Type_Selected",pressedPython.getText().toString());
        startActivity(messageIntent);
    }

    public void clickedJavascript(View view) {
        Intent messageIntent=new Intent(getApplicationContext(),Message.class);
        messageIntent.putExtra("Type_Selected",pressedJavascript.getText().toString());
        startActivity(messageIntent);
    }
}
