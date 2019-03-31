package com.example.mat;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ServiceBroadcastReceiver extends BroadcastReceiver {
    LocationManager locationManager;


    @SuppressLint("MissingPermission")
    @Override
    public void onReceive(Context context, Intent intent) {
//        Intent intent1=new Intent(context, BackgroundService.class);
//        context.startForegroundService(intent1);

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000000, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.e("Location", Double.toString(location.getLatitude()));
                Calendar mycalender = Calendar.getInstance();
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                DatabaseReference mChildReference = mDatabase.child("Location").push();
                mChildReference.child("Lattitude").setValue(location.getLatitude());
                mChildReference.child("Longitude").setValue(location.getLongitude());
                mChildReference.child("Time").setValue(mycalender.getTime());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });

    }


}
