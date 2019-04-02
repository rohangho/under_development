package com.example.myapplication2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;


public class ContactFragment extends Fragment {

    RecyclerView recycler;
    LinearLayoutManager linearLayoutManager;
    ContactAdapter contactAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_contact, container, false);

        InputStream raw =  getResources().openRawResource(R.raw.samplejson);

        Reader rd = new BufferedReader(new InputStreamReader(raw));

        Gson gson = new Gson();
//        JsonReader reader = new JsonReader(new StringReader(raw));
//        reader.setLenient(true);

        ContactModel obj=gson.fromJson(rd,ContactModel.class);
        List<Result> contactModels= obj.getResult();
        int a =contactModels.size();
        Toast.makeText(getContext(),Integer.toString(a),Toast.LENGTH_SHORT).show();


        recycler=(RecyclerView) v.findViewById(R.id.contactRecycler);
        linearLayoutManager=new LinearLayoutManager(getContext());
        recycler.setLayoutManager(linearLayoutManager);
        contactAdapter=new ContactAdapter(contactModels,getContext());
        recycler.setAdapter(contactAdapter);


        return v;
    }


}
