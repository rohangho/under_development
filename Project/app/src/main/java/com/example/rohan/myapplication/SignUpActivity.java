package com.example.rohan.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rohan.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class SignUpActivity extends AppCompatActivity {

    ImageView addPic;
    int pickPhoto=0;
    Button signUp;
    EditText name;
    EditText email;
    EditText password;
    EditText phoneNumber;
    Button home;
    Uri uri;
    FirebaseAuth mAuth;
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

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance()!=null)
                {
                    Intent homeIntent=new Intent(getApplicationContext(),Home.class);
                    startActivity(homeIntent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Please SignIn",Toast.LENGTH_SHORT).show();
            }
        });



        addPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                startActivityForResult(intent,pickPhoto);
            }
        });

        signUp=(Button)findViewById(R.id.sign_Up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")||email.getText().toString().equals("")||phoneNumber.getText().toString().equals("")||password.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),"Please fill properly",Toast.LENGTH_LONG).show();

                else
                {
                    showpRrogress.setVisibility(View.VISIBLE);
                    mAuth=FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        if(uri!=null) {
                                            UploadPicture();
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_SHORT).show();
                                            showpRrogress.setVisibility(View.GONE);
                                            home.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);

                                        }

                                    }
                                    else {
                                        showpRrogress.setVisibility(View.GONE);
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                                    }

                                    // ...
                                }
                            });

                }
            }
        });


    }

    private void UploadPicture() {

        FirebaseStorage storage;
        StorageReference storageReference;
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();
        StorageReference ref=storageReference.child("images/"+mAuth.getCurrentUser().getEmail());
        ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"addition successful",Toast.LENGTH_SHORT).show();
                //  returnHome.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark);
                home.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);
                showpRrogress.setVisibility(View.GONE);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showpRrogress.setVisibility(View.GONE);
                        Log.w(TAG,"image upload faliure",e);
                        Toast.makeText(getApplicationContext(),"Image addition unsuccessful",Toast.LENGTH_SHORT).show();
                    }

                });
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


    }


