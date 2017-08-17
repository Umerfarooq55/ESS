package com.example.dell.finalfirstproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    Button buttonSignIn;
    Context ctx = this;
    String NAME;
    public static final String ESS_PREFERENCES = "GamePrefs";
    SharedPreferences mPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    //    first_time_check();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NAME = "";

        TextView b = (TextView) findViewById(R.id.signup);
        if (b != null) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(loginActivity.this, SignupActivity.class);
                    startActivity(intent);
                }
            });
        }

        Button b1 = (Button) findViewById(R.id.buttonSignIn);
        if (b1 != null) {
            b1.setOnClickListener(new View.OnClickListener() {
                int x = 0;

                @Override
                public void onClick(View v) {
                    String username = ((EditText) findViewById(R.id.editTextUserNameToLogin)).getText().toString();
                    String password = ((EditText) findViewById(R.id.editTextPasswordToLogin)).getText().toString();
                    if (password == null) {
                        Toast.makeText(loginActivity.this,
                                "Fill the all fields", Toast.LENGTH_LONG)
                                .show();
                    } else {
                        DatabaseOperations dop = new DatabaseOperations(ctx);
                        Toast.makeText(loginActivity.this,
                                "Please Wait.....", Toast.LENGTH_LONG)
                                .show();
                        lo l = dop.getInformation(dop);

                        int sucsess = l.getS();

                        if (sucsess == 0) {
                            Toast.makeText(loginActivity.this,
                                    "Register First", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            Cursor CR = l.getC();
                            CR.moveToFirst();
                            boolean status = false;
                            SQLiteDatabase SQ = dop.getWritableDatabase();


                            do {
                                if (username.equals(CR.getString(0)) && (password.equals(CR.getString(1)))) {

                                    status = true;
                                    NAME = CR.getString(0);
                                }
                            } while (CR.moveToNext());


                            if (status) {
//
         //                       SharedPreferences.Editor editor = mPreferences.edit();
           //                     editor.putString("first", "valu");

             //                   editor.commit();
                                Toast.makeText(loginActivity.this,"Login Successful", Toast.LENGTH_LONG).show();
                                // Close the activity
                                Intent intent = new Intent(loginActivity.this, LOGO.class);
                                startActivity(intent);

                                //


                                //                                  Intent intent = new Intent(loginActivity.this, LOGO.class);
//                                    startActivity(intent);
//                                    intent.putExtra("NAME", NAME);

                            } else {

                                Toast.makeText(loginActivity.this, "Incorrect Username or Password", Toast.LENGTH_LONG).show();

                            }

                        }
                    }
                }


            });
        }
//        TextView s = (TextView) findViewById(R.id.buttonForgotPassword);
    }
/*
    private boolean first_time_check() {
        mPreferences=getSharedPreferences(ESS_PREFERENCES, MODE_PRIVATE);
        String first = mPreferences.getString("first", null);
        if ((first == null)) {
            NAME = "";

            TextView b = (TextView) findViewById(R.id.signup);
            if (b != null) {
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(loginActivity.this, SignupActivity.class);
                        startActivity(intent);
                    }
                });
            }

            Button b1 = (Button) findViewById(R.id.buttonSignIn);
            if (b1 != null) {
                b1.setOnClickListener(new View.OnClickListener() {
                    int x = 0;

                    @Override
                    public void onClick(View v) {
                        String username = ((EditText) findViewById(R.id.editTextUserNameToLogin)).getText().toString();
                        String password = ((EditText) findViewById(R.id.editTextPasswordToLogin)).getText().toString();
                        if (password == null) {
                            Toast.makeText(loginActivity.this,
                                    "Fill the all fields", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            DatabaseOperations dop = new DatabaseOperations(ctx);
                            Toast.makeText(loginActivity.this,
                                    "Please Wait.....", Toast.LENGTH_LONG)
                                    .show();
                            lo l = dop.getInformation(dop);

                            int sucsess = l.getS();

                            if (sucsess == 0) {
                                Toast.makeText(loginActivity.this,
                                        "Register First", Toast.LENGTH_LONG)
                                        .show();
                            } else {
                                Cursor CR = l.getC();
                                CR.moveToFirst();
                                boolean status = false;
                                SQLiteDatabase SQ = dop.getWritableDatabase();


                                do {
                                    if (username.equals(CR.getString(0)) && (password.equals(CR.getString(1)))) {

                                        status = true;
                                        NAME = CR.getString(0);
                                    }
                                } while (CR.moveToNext());


                                if (status) {
//
                                    SharedPreferences.Editor editor = mPreferences.edit();
                                    editor.putString("first", "valu");

                                    editor.commit();
                                    Toast.makeText(loginActivity.this,"Login Successful", Toast.LENGTH_LONG).show();
                                    // Close the activity
                                    Intent intent = new Intent(loginActivity.this, LOGO.class);
                                    startActivity(intent);

                                    //


  //                                  Intent intent = new Intent(loginActivity.this, LOGO.class);
//                                    startActivity(intent);
//                                    intent.putExtra("NAME", NAME);

                                } else {

                                    Toast.makeText(loginActivity.this, "Incorrect Username or Password", Toast.LENGTH_LONG).show();

                                }

                            }
                        }
                    }


                });
            }
            TextView s = (TextView) findViewById(R.id.buttonForgotPassword);

        }


        return false;
    }
*/
}