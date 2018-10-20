package com.example.rohan.myapplication.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rohan.myapplication.R;
import com.example.rohan.myapplication.model.UploadDetail;
import com.example.rohan.myapplication.presenter.SignUpPresenter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity implements SignUpMethods {

    ImageView addPic;
    int pickPhoto=0;
    Button signUp;
    EditText name;
    EditText email;
    EditText password;
    EditText phoneNumber;
    Button home;
    Uri uri;
    ProgressBar showpRrogress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        addPic=(ImageView)findViewById(R.id.addNew);
        name=(EditText)findViewById(R.id.signUp_name);
        email=(EditText)findViewById(R.id.singnUpEmail);
        phoneNumber=(EditText)findViewById(R.id.signUpPhone);
        password=(EditText)findViewById(R.id.signUpPassword);
        showpRrogress=(ProgressBar)findViewById(R.id.progress);
        home=(Button)findViewById(R.id.return_home);
        final SignUpPresenter signPresenter=new SignUpPresenter(this);
        addPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signPresenter.CallPlus();
            }
        });

        signUp=(Button)findViewById(R.id.sign_Up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signPresenter.CallSignUp();
            }
        });


    }

    @Override
    public void onClickPlus() {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent,pickPhoto);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == pickPhoto && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                addPic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClickSingUp() {



        if(name.getText().toString().equals("")||email.getText().toString().equals("")||phoneNumber.getText().toString().equals("")||password.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(),"Please fill properly",Toast.LENGTH_LONG).show();
        else
        {
            UploadDetail upload=new UploadDetail(addPic,name,email,phoneNumber,password,getApplicationContext(),this,uri ,home,showpRrogress);
            upload.uploadStuffs();
        }

    }


}
