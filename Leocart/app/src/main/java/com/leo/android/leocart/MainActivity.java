package com.leo.android.leocart;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ViewPager viewpager;
    ViewpagerAdapter viewpageradapter;
    String[] photo={"https://www.leocart.com/wp-content/uploads/2017/07/ban3-1.jpg","https://www.leocart.com/wp-content/uploads/2018/01/WhatsApp-Image-2018-01-25-at-16.52.56.jpg","https://www.leocart.com/wp-content/uploads/2018/01/accesorries.jpg"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager=(ViewPager)findViewById(R.id.swipepager);
        viewpageradapter=new ViewpagerAdapter(MainActivity.this,photo);
        viewpager.setAdapter(viewpageradapter);

    }

}
