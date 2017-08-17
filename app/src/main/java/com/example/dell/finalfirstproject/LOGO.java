package com.example.dell.finalfirstproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class LOGO extends AppCompatActivity {
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestContactPermission();
        setContentView(R.layout.activity_logo);
        mHandler.postDelayed(new Runnable() {
            public void run() {
                Intent i = new Intent(LOGO.this, MainActivity.class);
                startActivity(i);
            }
        }, 2000);
    }

 /*   private void requestContactPermission() {


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int hasCameraPermission = checkSelfPermission(CAMERA);

            List<String> permissions = new ArrayList<String>();

            if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(CAMERA);

            }
            if (!permissions.isEmpty()) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), 111);
            }

        }
    }*/

}