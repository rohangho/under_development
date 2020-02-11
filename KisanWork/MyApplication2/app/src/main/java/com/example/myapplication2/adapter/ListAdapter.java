package com.example.myapplication2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.R;
import com.example.myapplication2.entities.History;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyHolder> {

    List<History> allLIst=new ArrayList<>();
    Context context;

    public ListAdapter(List<History> allLIst, Context context)
    {
        this.allLIst=allLIst;
        this.context=context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_detail, parent, false);
        return new ListAdapter.MyHolder(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.name.setText(allLIst.get(allLIst.size()-position-1).getName());
        holder.otp.setText(Integer.toString(allLIst.get(allLIst.size()-position-1).getOtp()));
        holder.time.setText(allLIst.get(allLIst.size()-position-1).getTimestamp());

    }

    @Override
    public int getItemCount() {
        return allLIst.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView name;
        private AppCompatTextView otp;
        private AppCompatTextView time;
        public MyHolder(@NonNull View itemView, Context context) {
            super(itemView);

            name=itemView.findViewById(R.id.nameDISPLAYER);
            otp=itemView.findViewById(R.id.otpDisplayer);
            time=itemView.findViewById(R.id.timeStamp);

        }
    }
}
