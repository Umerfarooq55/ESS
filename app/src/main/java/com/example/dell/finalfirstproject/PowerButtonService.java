package com.example.dell.finalfirstproject;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

/**
 * Created by owner on 06-Nov-16.
 */

public class PowerButtonService extends Service {
    private TrackGPS gps;
    List<String> name;
    SecurityDatabase dop;
    String question,answer;
    double longitude;
    double latitude;
   Dialog dialog;
    private BroadcastReceiver mReceiver;
    LinearLayout mLinear;
    static int count=0;
    View mView,Dview;
    WindowManager wm;
    EditText abc;
    public PowerButtonService() {

    }


    LinearLayout dia;
    @Override
    public void onCreate() {
        super.onCreate();

        final Environment e =new Environment();
         mLinear = new LinearLayout(getApplicationContext()) {
            private String m_Text = "";
            //final EditText input = new EditText(getContext());
            //home or recent button
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            public void onCloseSystemDialogs(String reason) {
                if ("globalactions".equals(reason)) {
                    Log.e("Key", "Long press on power button");
                   Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
                    sendBroadcast(closeDialog);
                    dia=new LinearLayout(getApplicationContext());
                   //     dia = (LinearLayout) findViewById(R.id.lout);
                    dia.setFocusable(true);
                    Dview = LayoutInflater.from(getContext()).inflate(R.layout.lauus, null);

                 //   abc=new EditText(getContext());
                  //  dia.addView(abc);
                    //int idx = dia.indexOfChild(abc);
                    //abc.setTag(Integer.toString(idx));
                    dop=new SecurityDatabase(getApplicationContext());
                    lo l=dop.getInformation(dop);
                    Cursor CR=l.getC();
                    int sucsess=l.getS();
                    if(sucsess==0)
                    {
                        question="are you sure ?";
                        answer="";
                    }
                    else {
                        int i = 0;
                        CR.moveToFirst();
                        do {
                            question=CR.getString(0);
                            answer=CR.getString(1);

                            i++;
                        } while (CR.moveToNext());
                    }
                   AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext())
                    .setTitle("Security Question")
                            .setMessage(question)
                            .setView(Dview)
                           //.setContentView(R.layout.dial)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                  //  Dview.findViewWithTag(()\)
                                   EditText alo= (EditText) Dview.findViewById(R.id.input);
                                    m_Text = alo.getText().toString();
                                     mReceiver = null;
                     /*               IntentFilter filter = new IntentFilter(Intent.ACTION_SHUTDOWN);
                                    mReceiver = new ShutdownReceiver();
                                    registerReceiver(mReceiver, filter);*/
                          if(m_Text.equals(answer)){
                              String stop="stop";
                              stopSelf();
//                              stopService(new Intent(PowerButtonService.this,PowerButtonService.class));
//                              Intent iu=new Intent(PowerButtonService.this,LOGO.class);
//                              iu.putExtra(Intent.EXTRA_TEXT,stop);
//                              iu.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                              startActivity(iu);
/*                              try {
                                  try {
                                      e.getClass().getConstructor();
                                  } catch (NoSuchMethodException e1) {
                                      e1.printStackTrace();
                                  }
                                  Runtime.getRuntime()
                                          .exec(new String[]{"su", "-c", "reboot -p"});
                              } catch (IOException e1) {
                                  e1.printStackTrace();
                              }*/
                                   // Log.e("Shutdown"," Condition is MAtched ");
                          /*    try {
                                  Log.e("Shutdown"," inside try ");
                               /*  // Process proc =
                                          Runtime.getRuntime()
                                          .exec(new String[]{"su", "-c", "reboot -p"});
                                //  proc.waitFor();
                                  Log.e("Shutdown","proc.waitFor()");
                              } catch (Exception ex) {
                                  Log.e("Shutdown","inside Ecxeption");
                                  ex.printStackTrace();
                              }*/
                           //   Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
                            //  Intent i = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
                          /*   Intent i = new Intent(Intent.ACTION_SHUTDOWN);
                              i.putExtra("android.intent.extra.KEY_CONFIRM", true);
                             i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);*/
                           //   Intentfilter = new IntentFilter(Intent.ACTION_SHUTDOWN);
                             // sendBroadcast(i);
                              //   mReceiver = new ShutdownReceiver();
                            //  registerReceiver(mReceiver, filter);
                              //Log.e("Shutdown","iNTENT");
                             // startActivity(i);
                              //sendBroadcast(i);
                          Intent shutdown = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
                         //    Intent shutdown = new Intent("Intent.ACTION_SHUTDOWN");
                              shutdown.putExtra("android.intent.extra.KEY_CONFIRM", false);
                              shutdown.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                              getApplicationContext().startActivity(shutdown);

                       /*      try {
                                  Process proc = Runtime.getRuntime()
                                          .exec(new String[]{ "su", "-c", "reboot -p" });
                                  proc.waitFor();
                              } catch (Exception ex) {
                                Log.e("catch","dhutdown");
                                  ex.printStackTrace();
                              }*/


                      /*            PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
                                  //powerManager.shutdown(false, false);
                              getApplicationContext().enforceCallingOrSelfPermission(android.Manifest.permission.REBOOT, null);

                              powerManager.reboot(null);*/
                           /*  String cmd = "su -c shutdown";
                              try{
                                  Runtime.getRuntime().exec(cmd);
                              }catch(IOException e){
                                  Log.e("catch","dhutdown");
                                  e.printStackTrace();
                              }*/

/*
                              try {
                                  Process p = Runtime.getRuntime().exec("su");
                                  OutputStream os = p.getOutputStream();
                                  os.write("reboot\n".getBytes());
                                  os.flush();
                              } catch (IOException e1) {
                                  Log.e("catch","dhutdown");
                                  e1.printStackTrace();
                              } */

                          }
                                  //  Toast.makeText(getContext(),"answer is "+answer, Toast.LENGTH_LONG).show();
                                    //  m_Text= String.valueOf(Message.obtain());

                                }
                            })

                    .create();

//*****************************************

                //    Camera camCapture=new Camera(getApplicationContext());
              /*    Intent front_translucent = new Intent(getApplication()
 .getApplicationContext(), CamerService.class);
 front_translucent.putExtra("Front_Request", true);
 front_translucent.putExtra("Quality_Mode",
 1);
 getApplication().getApplicationContext().startService(
 front_translucent);
*/
 //***************************************************
    /*Intent sec =new Intent(PowerButtonService.this,Cam.class);
         startService(sec);*/
              /*   if(mView!=null){
                       wm.removeView(mView);

                    }*/
               //******************************************//

           //        Intent i=new Intent(PowerButtonService.this,Democamservice.class);
             //       startService(i);
                    Log.e("check Gallery","check Gallery");
                    //****************************************/

                   wm = (WindowManager) getSystemService(WINDOW_SERVICE);
                    alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ERROR);

                    //wm.addView(alertDialog.getW,WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    alertDialog.show(); //AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext())

                      //      getWindow();
                    // I'm using fragment here so I'm using getView() to provide ViewGroup
                    // but you can provide here any other instance of ViewGroup from your Fragment / Activity

                    // Set up the input




                    //Log.e("Key", "Long press on power button");
                } else if ("homekey".equals(reason)) {
                    //home key pressed
                } else if ("recentapss".equals(reason)) {
                    // recent apps button clicked
                    Log.e("Taps", "Taps on screen");
                }
            }

            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                        || event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP

                        || event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN
                        || event.getKeyCode() == KeyEvent.KEYCODE_CAMERA
                        || event.getKeyCode() == KeyEvent.KEYCODE_POWER) {
                    Log.i("Key", "keycode " + event.getKeyCode());
                }
                return super.dispatchKeyEvent(event);
            }

        };



        mLinear.setFocusable(true);

        mView = LayoutInflater.from(this).inflate(R.layout.service_layout, mLinear);


         wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        //params
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                100,
                100,
                WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                         |WindowManager.LayoutParams.FLAG_FULLSCREEN
                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                        |WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        wm.addView(mView, params);

mView.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.e("Touch", "Touch on surface");
        count=count+1;
        try {
                    boolean lock=((KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE)).isKeyguardLocked();
                    if(lock && count%3==0) {

                        gps= new TrackGPS(getApplicationContext());
                        if(gps.canGetLocation()){
                            longitude=gps.getLongitude();
                            latitude=gps.getLatitude();

                            sendSMS("+923135107625", "Hey Friend my location is"+Double.toString(longitude)+Double.toString(latitude)+"please retrive me");
                            Toast.makeText(getApplicationContext(), Double.toString(longitude),Toast.LENGTH_LONG).show();
                        }else {
                            sendSMS("+923135107625", "I AM IN TROUBLE HELP ME");
                            Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_LONG)
                                    .show();
                            Log.e("Lock screen", " yes sms send");
                        }
                    }
                else{
                      //  Toast.makeText(getApplicationContext(), "touch will work only on locked screen", Toast.LENGTH_LONG)
                            //    .show();
                        Log.e("Touch", "touch will work only on locked screen");
                    }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS Not Send Properly Sent", Toast.LENGTH_LONG)
                    .show();
        }
        /*
        try {

            Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_LONG)
                    .show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Toast.makeText(getApplicationContext(), e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }*/
        return false;
    }
});
    }
    private static final int PERMISSION_SEND_SMS = 123;

    public void sendSMS(String number, String msg) throws Exception {



        FriendsDatabase dop = new FriendsDatabase(getApplicationContext());
        lo l = dop.getInformation(dop);
        Cursor CR = l.getC();
        int sucsess = l.getS();
        if (sucsess == 0) {
            Toast.makeText(getApplicationContext(), "There is no user in your friend list sms not send", Toast.LENGTH_LONG)
                    .show();

        } else {
            int i = 0;
            CR.moveToFirst();
            do {
                String num=CR.getString(2);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(num, null, msg, null, null);
                i++;
            } while (CR.moveToNext());
            int size=name.size();
            String arr[]=new String[size];
                SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, msg, null, null);

        }

    }





    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
     //   unregisterReceiver(mReceiver);
      // startService(new Intent(this, PowerButtonService.class));
    /*    try {
            Process proc = Runtime.getRuntime()
                    .exec(new String[]{ "su", "-c", "reboot -p" });
            proc.waitFor();
        } catch (Exception ex) {
            Log.e("catch","dhutdown");
            ex.printStackTrace();
        }*/
        Toast.makeText(getApplicationContext(), "OnDestroy", Toast.LENGTH_LONG).show();

    }

}
