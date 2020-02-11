package com.example.myapplication2.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication2.R;
import com.example.myapplication2.adapter.ListAdapter;
import com.example.myapplication2.entities.AppDatabase;
import com.example.myapplication2.entities.HistoryDao;


public class ListFragment extends Fragment {

    AppDatabase appDatabase;
    HistoryDao historyDao;
    ListAdapter listAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        appDatabase=AppDatabase.gettDatabase(getActivity());
        historyDao = appDatabase.historyDao();
        recyclerView = v.findViewById(R.id.recycler);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        return v;
    }



    @Override
    public void onResume() {
        super.onResume();
        new getAllDetail().execute();
        recyclerView.scrollToPosition(0);

    }

    class getAllDetail extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            listAdapter=new ListAdapter(historyDao.getAll(),getActivity());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            recyclerView.setAdapter(listAdapter);


        }
    }

}
