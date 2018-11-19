package com.example.rohan.redox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    EditText name;
    Spinner age;
    String a="0";
    String b="Male";
    Button submit;
    String ageInString="10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.name_given);
        age=(Spinner)findViewById(R.id.spinner);
        submit=(Button)findViewById(R.id.submit_check);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Plese fill all things",Toast.LENGTH_SHORT).show();
                else
                {
                    if(a.equals("0"))
                        ageInString="9";
                    else if(a.equals("1"))
                        ageInString="10-15";
                    else if(a.equals("2"))
                        ageInString="16-40";
                    else
                        ageInString="Above40";

                    Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                    intent.putExtra("slab",a);
                    intent.putExtra("urGender",b.);
                    intent.putExtra("STRING_I_NEED",(name.getText().toString()+ageInString+b).toString());
                    startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                }
            }
        });
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.age,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age.setAdapter(adapter);
        age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        a="0";
                        break;
                    case 1:
                        a="1";
                        break;
                    case 2:
                        a="2";
                        break;
                    case 3:
                        a="3";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onRadioButtonClicked(View view) {
        boolean checked=((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.male_gender:
                if (checked)
                    b="Male";
                break;
            case R.id.female_gender:
                if(checked)
                    b="Female";
                break;
            case R.id.other_gender:
                if(checked)
                    b="Other";
                break;
        }
    }


}
