package com.getterexample.android.stack;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

/**
 * Created by ROHAN on 11-02-2018.
 */

public class widgetremote extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyWidgetRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    class MyWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

        private ArrayList<Bitmap> imgtodisplay;
        private Context context;
        private int mAppWidgetId;

        MainActivity obj=new MainActivity();
        public MyWidgetRemoteViewsFactory(Context context, Intent intent) {
            this.context = context;
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
            imgtodisplay = new ArrayList<>();
            if (obj.bitarray != null)
                for (int i = 0; i < obj.bitarray.size(); i++) {
                    imgtodisplay.add(obj.bitarray.get(i));
                }

        }

        @Override
        public void onCreate() {
            int a=imgtodisplay.size();

            for(int j=0;j<a;j++)
            {
                imgtodisplay.add(obj.bitarray.get(j));
            }
        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if (imgtodisplay != null)
                return imgtodisplay.size();
            else
                return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.stackwidget);
            rv.setImageViewBitmap(R.id.img,imgtodisplay.get(position));
            return rv;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }

}

