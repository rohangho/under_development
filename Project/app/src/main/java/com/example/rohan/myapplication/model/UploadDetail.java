package com.example.rohan.myapplication.model;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v13.view.inputmethod.InputConnectionCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rohan.myapplication.R;
import com.example.rohan.myapplication.views.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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
    Uri uri;
    FirebaseAuth mAuth;
    Button returnHome;
    ProgressBar progressBar;


    public UploadDetail(ImageView profilePicture,EditText nameToUpload,EditText emailToUpload,EditText phoneNumberToUpload,EditText passwordToUpload ,Context signUpContext,SignUpActivity signUp,Uri uri,Button returnHome,ProgressBar progressBar)
    {
        this.profilePicture=profilePicture;
        this.nameToUpload=nameToUpload;
        this.emailToUpload=emailToUpload;
        this.phoneNumberToUpload=phoneNumberToUpload;
        this.passwordToUpload=passwordToUpload;
        this.signUpContext=signUpContext;
        this.signUp=signUp;
        this.uri=uri;
        this.returnHome=returnHome;
        this.progressBar=progressBar;
    }

    public void uploadStuffs()
    {
      //  FirebaseApp.initializeApp(signUpContext);

        progressBar.setVisibility(View.VISIBLE);
        mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(emailToUpload.getText().toString(), passwordToUpload.getText().toString())
                .addOnCompleteListener(signUp, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if(uri!=null) {
                                UploadPicture();
                            }
                            else
                                Toast.makeText(signUpContext,"Add an image",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(signUpContext,"Failed",Toast.LENGTH_LONG).show();
                        }

                        // ...
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
                Toast.makeText(signUpContext,"addition successful",Toast.LENGTH_SHORT).show();
              //  returnHome.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark);
                returnHome.getBackground().setColorFilter(ContextCompat.getColor(signUpContext, R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);
                progressBar.setVisibility(View.GONE);
            }
        })
           .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);
                Log.w(TAG,"image upload faliure",e);
                Toast.makeText(signUpContext,"Image addition unsuccessful",Toast.LENGTH_SHORT).show();
            }

        });
    }
}
