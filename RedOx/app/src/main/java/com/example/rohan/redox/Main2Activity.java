package com.example.rohan.redox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    String c = "zero";
    String d = "zero";
    String e1 = "V0";
    String e2 = "W0";
    String e3 = "X0";
    String e4 = "Y0";
    String e5 = "Z0";
    String sum = "zero";
    Button submit;
    String uniqueCode;
    String gender;
    String ageSlab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // Intent i = getIntent();
        Bundle extras=getIntent().getExtras();

            uniqueCode = (String) extras.get("STRING_I_NEED");
            // uniqueCode = k;
            gender = (String) extras.get("urGender");
            // gender = l;
            ageSlab = (String) extras.get("slab");
            // ageSlab = m;



        setContentView(R.layout.activity_main2);
        submit = (Button) findViewById(R.id.check);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c.equals("zero") || d.equals("zero") || sum.equals("zero"))
                    Toast.makeText(getApplicationContext(), "Fill all properly", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(getApplicationContext(), ShowQR.class);
                    intent.putExtra("GenerateQR", (uniqueCode + c + d + sum));
                    startActivity(intent);
                }
            }
        });
    }

    public void onRadioButtonClicked_Training(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.maui_thai:
                if (checked) {
                    if ((ageSlab.equals("2") || ageSlab.equals("3")) && gender.equals("Male"))
                        c = "A2B0C0D0";
                    else
                        c = "A1B0C0D0";
                }
                break;

            case R.id.jujutsu:
                if (checked) {
                    if ((ageSlab.equals("2") || ageSlab.equals("3")) && gender.equals("Male"))
                        c = "A0B2C0D0";
                    else
                        c = "A0B1C0D0";
                }
                break;

            case R.id.karate:
                if (checked) {
                    if ((ageSlab.equals("2") || ageSlab.equals("3")) && gender.equals("Male"))
                        c = "A0B0C2D0";
                    else
                        c = "A0B0C1D0";
                }
                break;

            case R.id.judo:
                if (checked) {
                    if ((ageSlab.equals("2") || ageSlab.equals("3")) && gender.equals("Male"))
                        c = "A0B0C0D2";
                    else
                        c = "A0B0C0D1";
                }
                break;
        }
    }

    public void onRadioButtonClicked_Exercise(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.cardio:
                if (checked)
                    d = "P1Q0R0S0";
                break;
            case R.id.strength:
                if (checked)
                    d = "P0Q1R0S0";
                break;
            case R.id.stamina:
                if (checked)
                    d = "P0Q0R1S0";
                break;

            case R.id.muscle:
                if (checked)
                    d = "P0Q0R0S1";
                break;
        }
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();


        switch (view.getId()) {
            case R.id.checkbox_swim:
                if (checked)
                    e1 = "V1";
                else
                    e1 = "V0";
                break;
            case R.id.checkbox_run:
                if (checked)
                    e2 = "W1";
                else
                    e2 = "W0";
                break;

            case R.id.checkbox_football:
                if (checked)
                    e3 = "X1";
                else
                    e3 = "X0";
                break;

            case R.id.checkbox_rugby:
                if (checked)
                    e4 = "Y1";
                else
                    e4 = "Y0";
                break;
            case R.id.checkbox_dance:
                if (checked)
                    e5 = "Z1";
                else
                    e5 = "Z0";
                break;
        }
        if (e1.equals("V0") && e2.equals("W0") && e3.equals("X0") && e4.equals("Y0") && e5.equals("Z0"))
            sum = "zero";
        else
            sum = e1 + e2 + e3 + e4 + e5;
        ;
    }
}
