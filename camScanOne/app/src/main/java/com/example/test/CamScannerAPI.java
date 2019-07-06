package com.example.test;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.intsig.csopen.sdk.ReturnCode;

import java.io.File;

public class CamScannerAPI extends AppCompatActivity {

    private static final String DIR_IMAGE = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/CSOpenApiDemo";
    Button select;
    ImageView imageDisplayer;
    String APP_KEY = "KLPt0gTtYUyP0fTXV8aH44e7";
    String outputPath;
    String outputPDF;
    String original;
    Bitmap bitmap;
    //Activity activity = getParent();
    private CSOpenAPI mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_scanner_api);
        mApi = CSOpenApiFactory.createCSOpenApi(this, APP_KEY, null);
        select = (Button) findViewById(R.id.selectImg);
        imageDisplayer = (ImageView) findViewById(R.id.imageButton);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Please provide Premission", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);

                    }
                } else {
                    openGallery();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        imageDisplayer.setImageBitmap(bitmap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 1 && grantResults[0] != -1 && grantResults[1] != -1) {
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
        if (resultCode == RESULT_OK && requestCode != 2) {
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
        if (requestCode == 2) {

            mApi.handleResult(requestCode, resultCode, data, new CSOpenApiHandler() {
                @Override
                public void onSuccess() {
                    new AlertDialog.Builder(CamScannerAPI.this)
                            .setTitle("Succesful")
                            .setMessage("oprning is successful")
                            .setPositiveButton(android.R.string.ok, null)
                            .create().show();
                    bitmap = BitmapFactory.decodeFile(outputPath);
                }

                @Override
                public void onError(int errorCode) {
                    String msg = handleResponse(errorCode);
                    new AlertDialog.Builder(CamScannerAPI.this)
                            .setTitle("Fail")
                            .setMessage(msg)
                            .setPositiveButton(android.R.string.ok, null)
                            .create().show();
                }

                @Override
                public void onCancel() {

                }
            });

        }


    }

    private void cropUsingCanmScanner(String picturePath) {


        outputPath = DIR_IMAGE + "/camScanOutput.jpg";
        outputPDF = DIR_IMAGE + "/camScanOutputFile.jpg";
        original = DIR_IMAGE + "/camOriginal.jpg";

//        try {
//            FileOutputStream fos = new FileOutputStream(picturePath);
//            fos.write(3);
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        CSOpenAPIParam param = new CSOpenAPIParam(picturePath,
                outputPath, outputPDF, original, 1.0f);
        boolean res = mApi.scanImage(this, 2, param);

        //     android.util.Log.d("Hiii", "send to CamScanner result: " + res);

        Intent intent = new Intent("com.intsig.camscanner.ACTION_SCAN");
//
        Uri uri = Uri.fromFile(new File(picturePath)); //Or  content uri picked from gallery
//
        intent.putExtra(Intent.EXTRA_STREAM, uri);
//
        intent.putExtra("scanned_image", outputPath);
//
        intent.putExtra("pdf_path", outputPDF);
//
        intent.putExtra("org_image", original);
        intent.putExtra("app_key", APP_KEY);
//
        try {
            startActivityForResult(intent, 2);
        } catch (ActivityNotFoundException var9) {
            com.intsig.csopen.util.Log.e("CSOpenApi", "CamScanner is not Installed");
        }


    }

    private String handleResponse(int code) {
        switch (code) {
            case ReturnCode.OK:
                return getString(R.string.a_msg_api_success);
            case ReturnCode.INVALID_APP:
                return getString(R.string.a_msg_invalid_app);
            case ReturnCode.INVALID_SOURCE:
                return getString(R.string.a_msg_invalid_source);
            case ReturnCode.AUTH_EXPIRED:
                return getString(R.string.a_msg_auth_expired);
            case ReturnCode.MODE_UNAVAILABLE:
                return getString(R.string.a_msg_mode_unavailable);
            case ReturnCode.NUM_LIMITED:
                return getString(R.string.a_msg_num_limit);
            case ReturnCode.STORE_JPG_ERROR:
                return getString(R.string.a_msg_store_jpg_error);
            case ReturnCode.STORE_PDF_ERROR:
                return getString(R.string.a_msg_store_pdf_error);
            case ReturnCode.STORE_ORG_ERROR:
                return getString(R.string.a_msg_store_org_error);
            case ReturnCode.APP_UNREGISTERED:
                return getString(R.string.a_msg_app_unregistered);
            case ReturnCode.API_VERSION_ILLEGAL:
                return getString(R.string.a_msg_api_version_illegal);
            case ReturnCode.DEVICE_LIMITED:
                return getString(R.string.a_msg_device_limited);
            case ReturnCode.NOT_LOGIN:
                return getString(R.string.a_msg_not_login);
            default:
                return "Return code = " + code;
        }
    }
}
