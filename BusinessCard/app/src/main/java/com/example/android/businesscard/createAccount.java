package com.example.android.businesscard;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;

public class createAccount extends AppCompatActivity {

    Uri imageuri;
    ImageView profile_pic;
    private FirebaseAuth fbAuth;
    DatabaseReference mDatabaseRef;
    EditText nameofuser;
    EditText passwordofuser;
    EditText phonenumber;
    EditText mailId;
    Button open;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        profile_pic = (ImageView) findViewById(R.id.profilepic);
        nameofuser = (EditText) findViewById(R.id.name);
        passwordofuser = (EditText) findViewById(R.id.password);
        phonenumber = (EditText) findViewById(R.id.phoneNumber);
        mailId = (EditText) findViewById(R.id.mailId);
        fbAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        final Bitmap bitmap = ((BitmapDrawable)profile_pic.getDrawable()).getBitmap();
        open=(Button)findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(mailId.getText().toString()) && !TextUtils.isEmpty(passwordofuser.getText().toString())) {

                    fbAuth.createUserWithEmailAndPassword(mailId.getText().toString(), passwordofuser.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                DatabaseReference mChildDatabase = mDatabaseRef.child("Users Data").push();
                                mChildDatabase.child("primary number").setValue(phonenumber.getText().toString());
                                mChildDatabase.child("email").setValue(mailId.getText().toString());
                                mChildDatabase.child("password").setValue(passwordofuser.getText().toString());
                                mChildDatabase.child("name").setValue(nameofuser.getText().toString());
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                mChildDatabase.child("profile pic").setValue(bitmap.toString());
                            } else
                                Toast.makeText(getApplicationContext(),"SOMETHING WRONG HAPPENED",Toast.LENGTH_LONG).show();
                        }


                    });
                }

            }
        });


    }
    public void changePhoto(View v)
    {
        Intent gallery=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,22);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        super.onActivityResult(requestCode,resultCode,resultData);
        if(resultCode==RESULT_OK && requestCode==22)
        {
            imageuri=resultData.getData();
            String[] projection={MediaStore.Images.Media.DATA};
            Cursor cursor=getContentResolver().query(imageuri,projection,null,null,null);
            cursor.moveToFirst();
            int column=cursor.getColumnIndex(projection[0]);
            String filepath=cursor.getString(column);
            cursor.close();
            Bitmap selectedImage= BitmapFactory.decodeFile(filepath);
            profile_pic.setImageBitmap(selectedImage);
        }

    }

}
