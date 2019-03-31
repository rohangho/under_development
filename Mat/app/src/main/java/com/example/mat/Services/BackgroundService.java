package com.example.mat.Services;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.mat.ServiceBroadcastReceiver;

public class BackgroundService extends Service {


    int d = 0;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        new BackCall().execute();

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("i am being Destroyed", "Save Me");
//        BroadcastReceiver mt=new ServiceBroadcastReceiver();
//       IntentFilter intentFilter=new IntentFilter();
//       intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
//       getApplicationContext().registerReceiver(mt,intentFilter);


    }

    public class BackCall extends AsyncTask<Void, Void, Void> {


        @SuppressLint("MissingPermission")
        @Override
        protected Void doInBackground(Void... voids) {
            while (true) {

                PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
                boolean isScreenOn = pm.isInteractive();
                Log.i("Hii", Boolean.toString(isScreenOn));
                if (isScreenOn == false && d == 0) {
                    d = 1;
                    Intent intent = new Intent(getApplicationContext(), ServiceBroadcastReceiver.class);
                    sendBroadcast(intent);


                }
                if (isScreenOn == true && d == 1) {
                    d = 0;

                    Intent intent = new Intent(getApplicationContext(), ServiceBroadcastReceiver.class);
                    sendBroadcast(intent);


                }
            }

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }


    }


}
