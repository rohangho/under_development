package com.getterexample.android.stack;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button save;
    Button clear;
     Context context=this;
    static ArrayList<String> listOfAllImages;
    ArrayList<Bitmap> bitarray=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save=(Button)findViewById(R.id.importtostack);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_GRANTED) {
                        //  Toast.makeText(this,"hiiiiiiiii",Toast.LENGTH_LONG).show();
                        getAllImagesPath(context);
                    } else {

                        ActivityCompat.requestPermissions((Activity)context,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    }
                } else {
                    getAllImagesPath(context);
                }

               if(listOfAllImages.size()>0)
               {
                   for(int i=0;i<listOfAllImages.size();i++)
                   {
                       File imgFile = new  File(listOfAllImages.get(i));
                       if(imgFile.exists())
                       {
                           Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                           Bitmap myReduce=Bitmap.createScaledBitmap(myBitmap,240,320,true);
                           bitarray.add(myReduce);
                       }
                   }
               }
            }
        });
        clear=(Button)findViewById(R.id.removefromstack);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public static ArrayList<String> getAllImagesPath(Context context) {
        listOfAllImages = new ArrayList<>();
        listOfAllImages.addAll(getExternalImagesPath(context));
        listOfAllImages.addAll(getInternalImagesPath(context));
        return listOfAllImages;
    }
    private static ArrayList<String> getExternalImagesPath(Context context) {
        return getImagesPathFromUri(context, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }

    private static ArrayList<String> getInternalImagesPath(Context context) {
        return getImagesPathFromUri(context, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
    }

    private static ArrayList<String> getImagesPathFromUri(Context context, Uri uri) {
        Cursor cursor;
        int column_index_data;
        ArrayList<String> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage;
        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(column_index_data);
                listOfAllImages.add(absolutePathOfImage);
            }
            cursor.close();
        }
        return listOfAllImages;
    }

}
