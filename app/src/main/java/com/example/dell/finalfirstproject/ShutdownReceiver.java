package com.example.dell.finalfirstproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Dell PC on 11/14/2016.
 */

public class ShutdownReceiver extends BroadcastReceiver {
    private static final String TAG = "ShutdownReceiver";

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(Intent.ACTION_SHUTDOWN.equals(intent.getAction())) {
            Log.e(TAG, "System shutting down");
            context.stopService(new Intent(context, PowerButtonService.class));

        }
    }


}
