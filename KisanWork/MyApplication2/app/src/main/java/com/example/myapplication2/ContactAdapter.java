package com.example.myapplication2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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


    }

    @Override
    public int getItemCount() {
        return allResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.nameOfPerson);
        }
    }
}