package com.example.mat;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mat.Services.BackgroundService;
import com.example.mat.model.TimeDetails;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public  ArrayList<String> allLatitude=new ArrayList<String>();
    public  ArrayList<String> allLongitude=new ArrayList<String>();
    public ArrayList<TimeDetails> details=new ArrayList<TimeDetails>();
    HashMap<String,Long> j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(getApplicationContext(),"Please Provide Permission",Toast.LENGTH_SHORT).show();
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
        else
        {
            startService();
        }


        new BackGroundFetch().execute();





    }
    public void startService()
    {
        Intent intent=new Intent(this, BackgroundService.class);
        getApplicationContext().startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0)
            startService();
        else
            Toast.makeText(getApplicationContext(),"Please Provide Permission",Toast.LENGTH_SHORT).show();
    }

    public class BackGroundFetch extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Location");
            ChildEventListener childEventListener=new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    for(DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        String a=ds.getValue().toString();
                        String b=ds.getKey();
                        if(b.equals("Lattitude"))
                            allLatitude.add(a);
                        if(b.equals("Longitude"))
                            allLongitude.add(a);
                        if(b.equals("Time"))
                        {
                            j = (HashMap<String,Long>) ds.getValue();
                        }
                        int a1 = 0,b1=0,c1=0,d1=0,e1=0;
                        for(String key:j.keySet())
                        {
                            a1=j.get("date").intValue();
                            b1=j.get("hours").intValue();
                            c1=j.get("minutes").intValue();
                            d1=j.get("month").intValue();
                            e1=j.get("seconds").intValue();

                        }
                        TimeDetails timeDetails=new TimeDetails(a1,b1,c1,d1,e1);
                        details.add(timeDetails);

                    }

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            databaseReference.addChildEventListener(childEventListener);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}
