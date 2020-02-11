package com.example.myapplication2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.myapplication2.R;
import com.example.myapplication2.entities.AppDatabase;
import com.example.myapplication2.entities.History;
import com.example.myapplication2.entities.HistoryDao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Random;

public class SendMessage extends AppCompatActivity {

    private AppCompatButton send;
    private AppCompatEditText message;
    int number=0;
    AppDatabase appDatabase;
    HistoryDao historyDao;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        name=getIntent().getStringExtra("Name");
        appDatabase=AppDatabase.gettDatabase(this);
        historyDao = appDatabase.historyDao();
        send=findViewById(R.id.sendNeximo);
        message=findViewById(R.id.messageToSend);
        number = new Random().nextInt(900000) + 100000;;
        message.setText("Your OTP is "+Integer.toString(number));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(message.getText().toString().length()!=0)
                new ABC().execute(message.getText().toString());
            }
        });
    }

    public class ABC extends AsyncTask<String, Void, Void>
    {

        @Override
        protected Void doInBackground(String... strings) {

            try {
                // Construct data
                String apiKey = "apikey=" + "2TI9LMzWoJw-Ai8P5XqZJC6SNhXSQiLZLNWn3cDVuC";
                String message = "&message=" + strings[0];
                String sender = "&sender=" + "TXTLCL";
                String numbers = "&numbers=" + "919810153260";

                // Send data
                HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                String data = apiKey + numbers + message + sender;
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                conn.getOutputStream().write(data.getBytes("UTF-8"));
                final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                final StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    stringBuffer.append(line);

                }
                if(stringBuffer.toString().contains("success")) {
                    History history=new History();
                    history.setName(name);
                    history.setOtp(number);
                    history.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
                    historyDao.insertAppointment(history);
                }
                rd.close();

            } catch (Exception e) {
                System.out.println("Error SMS " + e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }

}
