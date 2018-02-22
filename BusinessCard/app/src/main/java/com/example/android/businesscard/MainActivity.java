package com.example.android.businesscard;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {

    EditText mail;
    EditText passme;
    Button logmeIn;
    private FirebaseAuth fbAuth;
    Button createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fbAuth = FirebaseAuth.getInstance();

        mail=(EditText)findViewById(R.id.nameid);
        passme=(EditText)findViewById(R.id.passid);
        logmeIn=(Button)findViewById(R.id.inside);
        logmeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbAuth.signInWithEmailAndPassword(mail.getText().toString(),passme.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                            Toast.makeText(getApplicationContext(),"Get in",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(),"Not Possible",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        createAccount=(Button)findViewById(R.id.create);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(getApplicationContext(),createAccount.class);
                startActivity(myintent);
            }
        });

        computePakageHash();
    }

    private void computePakageHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.android.businesscard",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            Log.e("TAG",e.getMessage());
        }
    }

}
