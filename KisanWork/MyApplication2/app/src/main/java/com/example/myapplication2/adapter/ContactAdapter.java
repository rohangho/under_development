package com.example.myapplication2.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.R;
import com.example.myapplication2.activity.SendText;
import com.example.myapplication2.pojo.Result;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    List<Result> allResult;
    Context context;

    public ContactAdapter(List<Result> allResult, Context context) {
        this.allResult = allResult;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inside_contact, parent, false);
        return new ContactAdapter.ViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(allResult.get(position).getName());
        final int a=position;
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SendText.class);
                intent.putExtra("Name",allResult.get(a).getName());
                intent.putExtra("Phone Number",allResult.get(a).getNumber());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return allResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        LinearLayout container;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.nameOfPerson);
            container=(LinearLayout)itemView.findViewById(R.id.container);
        }
    }
}