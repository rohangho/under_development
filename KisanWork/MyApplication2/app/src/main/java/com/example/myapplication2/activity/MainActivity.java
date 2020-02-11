package com.example.myapplication2.activity;

import com.example.myapplication2.adapter.PagerAdapter;
import com.example.myapplication2.R;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        tabLayout=(TabLayout)findViewById(R.id.tabs);
        viewPager=(ViewPager) findViewById(R.id.container);
        pagerAdapter=new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }


}