package com.example.android.chatui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ROHAN on 26-03-2018.
 */

public class MessageAdapter extends ArrayAdapter<ChatMessage> {

    private Activity activity;
    private List<ChatMessage> messages;

    public MessageAdapter(Activity context, int resource, List<ChatMessage> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.messages = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        int layoutResource = 0; // determined by view type

        ChatMessage chatMessage = getItem(position);
        int viewType = getItemViewType(position);
        if (position!=1&& position!=2)
        {
            if (chatMessage.isMine()) {

                layoutResource = R.layout.chat_left;


            } else {
                layoutResource = R.layout.item_chat_right;
            }
        }
        else if ( position==1)
        { layoutResource=R.layout.checkbox; }
        else if( position==2)
        {layoutResource=R.layout.detail_of_prob;
        }
        else
        {
            if (chatMessage.isMine()) {

                layoutResource = R.layout.chat_left;


            } else {
                layoutResource = R.layout.item_chat_right;
            }
        }


        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(layoutResource, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        //set message content

            if(viewType==0)
            {holder.msg.setText(chatMessage.getContent());

            }
            else if(position==1)
            {
                holder.butt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //obj.update();
                    }
                });
            }

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        // return the total number of view types. this value should never change
        // at runtime
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        return position % 3;
    }

    private class ViewHolder {
        private TextView msg;
        private Button butt;
         public ViewHolder(View v) {
            msg = (TextView) v.findViewById(R.id.txt_msg);
            butt=(Button)v.findViewById(R.id.submit);
        }
    }
}

