package com.example.android.swipeup;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;


public class Fragment_one extends Fragment implements OnChartGestureListener,OnChartValueSelectedListener {

    LineChart lc;
    ArrayList<Entry> yvalues;
    public Fragment_one() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.fragment_fragment_one, container, false);

        lc=(LineChart)rootview.findViewById(R.id.chart);
        yvalues=new ArrayList<Entry>();
        yvalues.add(new Entry(0,6));
        yvalues.add(new Entry(1,2));
        yvalues.add(new Entry(2,2));
        yvalues.add(new Entry(4,8));
        yvalues.add(new Entry(7,9));

        LineDataSet set1=new LineDataSet(yvalues,"Data set 1");

        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(set1);
        LineData ld=new LineData(dataSets);
        lc.setData(ld);
        lc.setOnChartGestureListener(this);
        lc.setOnChartValueSelectedListener(this);
        lc.setDragEnabled(true);
        lc.setScaleEnabled(true);
        lc.setTouchEnabled(true);
        lc.setPinchZoom(true);
        lc.setDoubleTapToZoomEnabled(true);
        lc.setDragDecelerationFrictionCoef(0.5f);

        ViewPager viewPagermini=(ViewPager)rootview.findViewById(R.id.detailofeachtransaction);
        ViewPagerMiniAdapter miniadapter = new ViewPagerMiniAdapter(getFragmentManager());


         //Add Fragments to adapter one by one
        miniadapter.addFragment(new Fragment_one(), "CASH IN");
        miniadapter.addFragment(new Fragment_two(), "CASH OUT");

        viewPagermini.setAdapter(miniadapter);
        TabLayout tabLayout = (TabLayout)rootview.findViewById(R.id.minitab);
        tabLayout.setupWithViewPager(viewPagermini);

        return rootview;
    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
