package com.example.android.doc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ROHAN on 25-03-2018.
 */

class customdatatype{
    String name;
    boolean select=false;
    public  customdatatype(String name )
    {
        this.name=name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}

public class listAdapter extends ArrayAdapter<customdatatype> {

    private List<customdatatype> symptonlist;
    private Context context;

    public listAdapter(List<customdatatype> symptonlist, Context context) {
        super(context, R.layout.each_item_of_list,symptonlist);
        this.symptonlist=symptonlist;
        this.context=context;
    }
    public static class SymptonHolder {
        public TextView sym;
        public CheckBox tick;

    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View v=convertView;
        SymptonHolder hold=new SymptonHolder();
        if(convertView==null)
        {
            LayoutInflater infiltrate=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=infiltrate.inflate(R.layout.each_item_of_list,null);
            hold.sym=(TextView) v.findViewById(R.id.sympt);
            hold.tick=(CheckBox)v.findViewById(R.id.check);

            hold.tick.setOnCheckedChangeListener((checktheBox) context);
            v.setTag(hold);

        }
        else
        {
            hold=(SymptonHolder)v.getTag();
        }
        customdatatype p=symptonlist.get(position);
        hold.sym.setText(p.getName());
        hold.tick.setChecked(p.isSelect());
        hold.tick.setTag(p);
        return v;
    }
}

