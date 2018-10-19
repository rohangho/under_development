package com.example.rohan.myapplication.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v13.view.inputmethod.InputConnectionCompat;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rohan.myapplication.views.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

import static android.content.ContentValues.TAG;

public class UploadDetail {
    ImageView profilePicture;
    EditText nameToUpload;
    EditText phoneNumberToUpload;
    EditText passwordToUpload;
    EditText emailToUpload;
    Context signUpContext;
    SignUpActivity signUp;


    public UploadDetail(ImageView profilePicture,EditText nameToUpload,EditText emailToUpload,EditText phoneNumberToUpload,EditText passwordToUpload ,Context signUpContext,SignUpActivity signUp)
    {
        this.profilePicture=profilePicture;
        this.nameToUpload=nameToUpload;
        this.emailToUpload=emailToUpload;
        this.phoneNumberToUpload=phoneNumberToUpload;
        this.passwordToUpload=passwordToUpload;
        this.signUpContext=signUpContext;
        this.signUp=signUp;
    }

    public void uploadStuffs()
    {
      //  FirebaseApp.initializeApp(signUpContext);
        FirebaseAuth mAuth;
        mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(emailToUpload.getText().toString(), passwordToUpload.getText().toString())
                .addOnCompleteListener(signUp, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(signUpContext,"Successful",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(signUpContext,"Failed",Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });


    }
}
