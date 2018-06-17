package com.example.rohan.realmtutorial;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.rohan.realmtutorial.models.AmountPojo;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements OnChartGestureListener,OnChartValueSelectedListener {

    String currentDateandTime;
    EditText amount;
    EditText dateandtime;
    LineChart lc;
    float s;
    Calendar mcalendar;
    ArrayList<Entry> values;
    int weekofmonth;
    int[]a;
    ArrayList<AmountPojo> database=new ArrayList<AmountPojo>();
    int day;
    int month;
    int week;
    String time;
    int year;
    Realm realm=Realm.getDefaultInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount=(EditText)findViewById(R.id.amount);
        dateandtime=(EditText)findViewById(R.id.date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy/hh:mm");
        currentDateandTime = sdf.format(new Date());
        dateandtime.setText(currentDateandTime);
        mcalendar=Calendar.getInstance();
        day=mcalendar.get(Calendar.DAY_OF_MONTH);
        month=mcalendar.get(Calendar.MONTH);
        year=mcalendar.get(Calendar.YEAR);

        dateandtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datepicker=new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, final int year, final int monthofyear, final int dayofmonth) {
                        dateandtime.setText(dayofmonth+"/"+monthofyear+"/"+year+"/"+"00"+":"+"00");

                       // Beginning to get the time
                        Calendar mcurrentTime = Calendar.getInstance();
                        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                        int minute = mcurrentTime.get(Calendar.MINUTE);
                        TimePickerDialog mTimePicker;
                        mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                dateandtime.setText(dayofmonth+"/"+monthofyear+"/"+year+"/"+selectedHour+":"+selectedMinute);
                            }
                        }, hour, minute, true);//Yes 24 hour time
                        mTimePicker.setTitle("Select Time");
                        mTimePicker.show();

                        // Ending to get the time


                    }
                },year,month,day);
                datepicker.show();
            }
        });

        displaychart();
    }



    private void displaychart() {

        RealmResults<AmountPojo> results=realm.where(AmountPojo.class).findAll();
        for(AmountPojo ap:results)
            database.add(ap);

        lc=(LineChart)findViewById(R.id.chart);
        values=new ArrayList<Entry>();
        for(int i=0;i<=12;i++)
        {
            for(int j=0;j<database.size();j++)
            {
                if(Datetoint.returnmonthinnumber(database.get(j).getDate())==i)
                {
                    s=s+Float.parseFloat(database.get(j).getAmount());
                }
                values.add(new Entry((float)i,s));
                s=0.0f;
            }
        }

        LineDataSet set1=new LineDataSet(values,"Data set 1");

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

    }



    public void Submit(View view) {
        realm=Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                AmountPojo amountpojo= bgRealm.createObject(AmountPojo.class);
                amountpojo.setAmount(amount.getText().toString());
                try {
                    Date date1=new SimpleDateFormat("dd/MM/yyyy/hh:mm").parse(dateandtime.getText().toString());
                    amountpojo.setDate(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

                Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_LONG).show();



            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(MainActivity.this,"Unsuccessful",Toast.LENGTH_LONG).show();
            }
        });


    }
@Override
    public void onDestroy()
    {
        super.onDestroy();
        realm.close();
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
