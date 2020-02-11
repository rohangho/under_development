package com.example.myapplication2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication2.R;

public class SendText extends AppCompatActivity {
    private String a,b=null;
    private AppCompatTextView name;
    private AppCompatTextView phone;
    private AppCompatButton sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_text);

        a=getIntent().getStringExtra("Name");
        b=getIntent().getStringExtra("Phone Number");

        name=findViewById(R.id.name);
        if(a!=null)
            name.setText(a);

        phone=findViewById(R.id.phoneNumber);
        if(b!=null)
            phone.setText(b);

        sendButton=findViewById(R.id.sendButton);
        final Intent intent=new Intent(this,SendMessage.class);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent.putExtra("Name",a);
                intent.putExtra("Phone Number",b);
                startActivity(intent);
            }
        });


    }
}
