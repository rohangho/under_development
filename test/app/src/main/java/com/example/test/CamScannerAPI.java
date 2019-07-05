package com.example.test;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.intsig.csopen.sdk.CSOpenAPI;
import com.intsig.csopen.sdk.CSOpenAPIParam;
import com.intsig.csopen.sdk.CSOpenApiFactory;
import com.intsig.csopen.sdk.CSOpenApiHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CamScannerAPI extends AppCompatActivity {

    Button select;
    ImageView imageDisplayer;
    //Activity activity = getParent();
    private CSOpenAPI mApi;
    String APP_KEY="KLPt0gTtYUyP0fTXV8aH44e7";
    private static final String DIR_IMAGE = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/CSOpenApiDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_scanner_api);
        mApi = CSOpenApiFactory.createCSOpenApi(this, APP_KEY, null);
        select = (Button) findViewById(R.id.selectImg);
        imageDisplayer=(ImageView) findViewById(R.id.imageButton);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Please provide Premission", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);

                    }
                } else {
                    openGallery();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            Toast.makeText(getApplicationContext(), "Permissio Granted", Toast.LENGTH_SHORT).show();
            openGallery();
        } else
            Toast.makeText(getApplicationContext(), "Permission Failed", Toast.LENGTH_SHORT).show();

    }

    protected void openGallery() {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

        startActivityForResult(chooserIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                imageDisplayer.setImageBitmap(bitmap);
                Cursor cursor = getContentResolver().query(selectedImageUri, projection, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(projection[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                Log.d("Picture Path", picturePath);

                cropUsingCanmScanner(picturePath);
            } catch (Exception e) {
                Log.e("Path Error", e.toString());
            }
        }
    }

    private void cropUsingCanmScanner(String picturePath) {

       // Intent intent = new Intent("com.intsig.camscanner.ACTION_SCAN");
    String outputPath=DIR_IMAGE+"/camScanOutput.jpg";
    String outputPDF=DIR_IMAGE+"/camScanOutputFile.jpg";
    String original=DIR_IMAGE+"/camOriginal.jpg";

//        try {
//            FileOutputStream fos = new FileOutputStream(picturePath);
//            fos.write(3);
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        CSOpenAPIParam param = new CSOpenAPIParam(picturePath,
//                outputPath, outputPDF,picturePath , 1.0f);
//        boolean res = mApi.scanImage(this,, param);
//        android.util.Log.d("Hiii", "send to CamScanner result: " + res);

        Intent intent = new Intent("com.intsig.camscanner.ACTION_SCAN");

        Uri uri = Uri.fromFile(new File(picturePath)); //Or  content uri picked from gallery

        intent.putExtra(Intent.EXTRA_STREAM, uri);

        intent.putExtra("scanned_image", outputPath);

        intent.putExtra("pdf_path", outputPDF);

        intent.putExtra("org_image", original);

        startActivityForResult(intent, 2);


    }
}
