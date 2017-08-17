package com.example.dell.finalfirstproject;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import androidhiddencamera.CameraConfig;
import androidhiddencamera.HiddenCameraService;
import androidhiddencamera.config.CameraFacing;
import androidhiddencamera.config.CameraImageFormat;
import androidhiddencamera.config.CameraResolution;

/**
 * Created by owner on 30-Jan-17.
 */
public class Democamservice extends HiddenCameraService {

    @Override
    public void onCreate() {
        super.onCreate();
        CameraConfig mcamconfig = new CameraConfig().getBuilder(getApplication())
                .setCameraFacing(CameraFacing.FRONT_FACING_CAMERA)
                .setCameraResolution(CameraResolution.MEDIUM_RESOLUTION)
                .setImageFormat(CameraImageFormat.FORMAT_JPEG).build();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            startCamera(mcamconfig);
            takePicture();

        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
