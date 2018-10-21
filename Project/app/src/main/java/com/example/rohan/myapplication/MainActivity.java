package com.example.rohan.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity  {

    Button signUp;
    EditText mailEntered;
    Button logIn;
    EditText passwordEntered;
    FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fbAuth = FirebaseAuth.getInstance();
        signUp=(Button)findViewById(R.id.sign_Up_in_mainactivity);
        mailEntered=(EditText)findViewById(R.id.emailId);
        passwordEntered=(EditText)findViewById(R.id.password);
        logIn=(Button)findViewById(R.id.getIn);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mailEntered.getText().toString().isEmpty() || passwordEntered.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Fill the Details", Toast.LENGTH_SHORT).show();
                else {
                    fbAuth.signInWithEmailAndPassword((mailEntered.getText().toString()), (passwordEntered.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent myintent = new Intent(getApplicationContext(), Home.class);
                                startActivity(myintent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Something is wrong", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });




        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent=new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(signupIntent);
            }
        });
    }


}
