package com.example.rohan.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<MessageModel> {
    Context context;
    public MessageAdapter(@NonNull Context context, int resource, List<MessageModel> messages) {
        super(context, resource,messages);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.inside_messege, parent, false);
        }
        TextView massage_to_be_displayed=(TextView)convertView.findViewById(R.id.displaymessage);
        TextView author_to_display=(TextView)convertView.findViewById(R.id.displayauthor);

        MessageModel messege=getItem(position);
        massage_to_be_displayed.setText(messege.message);
        author_to_display.setText(messege.authur);
        return convertView;
    }
}
