package com.example.android.doc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;

public class checktheBox extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    ListView lv;
    ArrayList<customdatatype> symptonlist;
    listAdapter adapt;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkthe_box);
        lv=(ListView)findViewById(R.id.list);
        displayList();
    }

    private void displayList() {
        symptonlist=new ArrayList<customdatatype>();
        symptonlist.add(new customdatatype("option1"));
        symptonlist.add(new customdatatype("option2"));
        symptonlist.add(new customdatatype("option3"));
        symptonlist.add(new customdatatype("option4"));
        symptonlist.add(new customdatatype("option5"));
        symptonlist.add(new customdatatype("option6"));
        symptonlist.add(new customdatatype("option7"));
        symptonlist.add(new customdatatype("option8"));
        symptonlist.add(new customdatatype("option9"));

        adapt = new listAdapter(symptonlist,this);
        ListView listView=findViewById(R.id.list);
        listView.setAdapter(adapt);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

         pos=lv.getPositionForView(buttonView);
        if(pos!=ListView.INVALID_POSITION)
        {
            customdatatype c=symptonlist.get(pos);
            c.setSelect(isChecked);
        }

    }
}
