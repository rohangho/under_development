package com.example.android.springlr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class Detail extends AppCompatActivity {

    private FirebaseAuth mAuth;
    DatabaseReference mDatabaseRef;
    EditText nameofuser;
    EditText ageofuser;
    TextView displaydetail;
    Button submitPressed;
    FirebaseUser currentUser;
    DataSnapshot data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameofuser=(EditText)findViewById(R.id.name);
        ageofuser=(EditText)findViewById(R.id.age);
        submitPressed=(Button)findViewById(R.id.submit);
        displaydetail=(TextView)findViewById(R.id.detail);
        mAuth=FirebaseAuth.getInstance();
         currentUser=mAuth.getCurrentUser();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        mDatabaseRef.child("Details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> detais=dataSnapshot.getChildren().iterator();
                while (detais.hasNext())
                {
                    DataSnapshot detai=detais.next();
                    String emailid=detai.child("email").getValue().toString();
                    if(emailid.equals(currentUser.getEmail()))
                        displaydetail.setText(detai.child("name").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        submitPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(nameofuser.getText().toString())&&!TextUtils.isEmpty(ageofuser.getText().toString()))
                {
                    DatabaseReference mChildDatabase = mDatabaseRef.child("Details").push();
                    mChildDatabase.child("email").setValue(currentUser.getEmail().toString());
                    mChildDatabase.child("name").setValue(nameofuser.getText().toString());
                    mChildDatabase.child("age").setValue(ageofuser.getText().toString());
                    Toast.makeText(getApplicationContext(),"submitted Successfully",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
            }
        });



    }

}
