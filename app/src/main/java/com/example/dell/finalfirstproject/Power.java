package com.example.dell.finalfirstproject;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.SEND_SMS;
import static android.Manifest.permission_group.CAMERA;

public class Power extends AppCompatActivity {
    public final static int REQUEST_CODE = 10101;
String data="abc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestContactPermission();
        if (checkDrawOverlayPermission()) {
            startService(new Intent(this, PowerButtonService.class).putExtra("data", data) );
        }
    }

    public boolean checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (!Settings.canDrawOverlays(this)) {
            /** if not construct intent to request permission */
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            /** request permission via start activity for result */
            startActivityForResult(intent, REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {

                startService(new Intent(this, PowerButtonService.class));

            }
        }
    }

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private void requestContactPermission() {


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int hasCameraPermission = checkSelfPermission(CAMERA);
            int hasContactPermission = checkSelfPermission(SEND_SMS);
            List<String> permissions = new ArrayList<String>();
            List<String> persms = new ArrayList<String>();
            if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(CAMERA);

            }
            if (!permissions.isEmpty()) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), 111);
            }
            if (hasContactPermission != PackageManager.PERMISSION_GRANTED) {
                persms.add(SEND_SMS);

            }
            if (!persms.isEmpty()) {
                requestPermissions(persms.toArray(new String[persms.size()]), 123);
            }

        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {


            // break;
            case 111: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        System.out.println("Permissions --> " + "Permission Granted: " + permissions[i]);


                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        System.out.println("Permissions --> " + "Permission Denied: " + permissions[i]);

                    }
                }

            }

            case 123: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        System.out.println("Permissions --> " + "Permission Granted: " + permissions[i]);


                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        System.out.println("Permissions --> " + "Permission Denied: " + permissions[i]);

                    }
                }


            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        stopService(new Intent(Power.this,PowerButtonService.class));
    }
}