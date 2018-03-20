package com.leo.android.leocart;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by ROHAN on 20-03-2018.
 */

public class ViewpagerAdapter extends PagerAdapter {

    Activity activity;
    String[] images;
    LayoutInflater inflator;

    public ViewpagerAdapter(Activity activity, String[] images) {
        this.activity = activity;
        this.images = images;
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflator=(LayoutInflater) activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView=inflator.inflate(R.layout.vewpagerlayout,container,false);
        ImageView banner;
        banner=(ImageView)itemView.findViewById(R.id.bannerimage);
        Picasso.get().load(images[0]).into(banner);
        container.addView(itemView);
        return itemView;
    }

   // @Override
    //public void destroyItem(ViewGroup container, int position, Object object) {
    //    ((ViewPager)container).removeView((View)object);
    //}
}
