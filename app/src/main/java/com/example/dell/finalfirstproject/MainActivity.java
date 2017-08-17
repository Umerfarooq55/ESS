package com.example.dell.finalfirstproject;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    ToggleButton t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       /* Intent i= getIntent();
        if(i.hasExtra("stop"))
        {
            String stop = i.getStringExtra(Intent.EXTRA_TEXT);
            Log.e("value of Stop is ", stop);

                Toast.makeText(getApplicationContext(),"BAck to Main Activity",Toast.LENGTH_LONG).show();


        } */
// get data via the key

        Button  b=(Button) findViewById(R.id.fl);
        if (b != null) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Friendlist.class);
                    startActivity(intent);
                }
            });
        }

        Button  b1=(Button) findViewById(R.id.sc);
        if (b1 != null) {
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, securityQuestion.class);
                    startActivity(intent);
                }
            });
        }

        t= (ToggleButton) findViewById(R.id.toggleButton);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(getApplicationContext(), Power.class));

            }
        });


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 111: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        System.out.println("Permissions --> " + "Permission Granted: " + permissions[i]);


                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        System.out.println("Permissions --> " + "Permission Denied: " + permissions[i]);

                    }
                }
            }
            break;
            case 15: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        System.out.println("Permissions --> " + "Permission Granted: " + permissions[i]);


                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        System.out.println("Permissions --> " + "Permission Denied: " + permissions[i]);

                    }
                }
            }
            break;
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

}
