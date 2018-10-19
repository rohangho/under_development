package com.example.rohan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rohan.myapplication.presenter.MainActivityPresenter;
import com.example.rohan.myapplication.views.MainActivityMethods;
import com.example.rohan.myapplication.views.SignUpActivity;

public class MainActivity extends AppCompatActivity implements MainActivityMethods {

    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUp=(Button)findViewById(R.id.sign_Up);
        final MainActivityPresenter presenter=new MainActivityPresenter(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.callMain();
            }
        });
    }

    @Override
    public void onClickSignUp() {
        Intent signupIntent=new Intent(this,SignUpActivity.class);
        startActivity(signupIntent);
    }
}
