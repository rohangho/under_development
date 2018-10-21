package com.example.rohan.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Message extends AppCompatActivity {

    EditText typedMessege;
    Button sendPressed;
    DatabaseReference mDatabaseRef;
   // ChildEventListener mChildEventListner;
    MessageAdapter adapter;
    ListView displayer;
    List<MessageModel> messages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        displayer=(ListView)findViewById(R.id.list_item);
        messages=new ArrayList<MessageModel>();

        typedMessege=(EditText)findViewById(R.id.message);
        sendPressed=(Button)findViewById(R.id.send);
        mDatabaseRef=FirebaseDatabase.getInstance().getReference();
        sendPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mChildDatabase = mDatabaseRef.child("JAVA").push();
                mChildDatabase.child("message").setValue(typedMessege.getText().toString());
                mChildDatabase.child("user").setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                typedMessege.setText("");

            }
        });


        DatabaseReference rootRef=FirebaseDatabase.getInstance().getReference("JAVA");
       rootRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot ds : dataSnapshot.getChildren()) {
                   String a=ds.child("message").getValue(String.class);
                   String b=ds.child("user").getValue(String.class);
                   MessageModel model=new MessageModel(a,b);
                   messages.add(model);
               }

               adapter=new MessageAdapter(getApplicationContext(),R.layout.inside_messege,messages);
               adapter.notifyDataSetChanged();
               displayer.setAdapter(adapter);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }
}
