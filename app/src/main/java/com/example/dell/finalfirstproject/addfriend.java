package com.example.dell.finalfirstproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addfriend extends AppCompatActivity {
Context ctx=this;
    EditText name,email,c_email,phone,c_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfriend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name= (EditText) findViewById(R.id.editTextUserName);
        email= (EditText) findViewById(R.id.editEmail);
        c_email= (EditText) findViewById(R.id.conf_Email);
        phone= (EditText) findViewById(R.id.editPhone);
        c_phone= (EditText) findViewById(R.id.conf_Phone);

        Button b=(Button) findViewById(R.id.buttonDONE);
        if (b != null) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            String Name=name.getText().toString();
                    String Email=email.getText().toString();
                    String C_email=c_email.getText().toString();
                    String Phone=phone.getText().toString();
                    String C_phone=c_phone.getText().toString();
                    if (Name.equals("") || C_email.equals("")
                            || C_phone.equals("") || Email.equals("")||Phone.equals("")) {

                        Toast.makeText(getApplicationContext(), "Fill the all Field",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (!Email.equals(Email)) {
                        Toast.makeText(getApplicationContext(),
                                "Email must be same", Toast.LENGTH_LONG)
                                .show();
                        return;
                    }
                    if (!Phone.equals(Phone)) {
                        Toast.makeText(getApplicationContext(),
                                "PHONE Number must be same", Toast.LENGTH_LONG)
                                .show();
                        return;
                    }
                    else {
                        FriendsDatabase fd = new FriendsDatabase(ctx);
                        boolean status= fd.putInformation(fd, Name, Email, Phone);

                        if(status) {
                            Intent intent = new Intent(addfriend.this, Friendlist.class);
                            startActivity(intent);
                        }
                        else {
                            name.setText("");
                            email.setText("");
                            c_email.setText("");
                            phone.setText("");
                            c_phone.setText("");
                            Toast.makeText(getApplicationContext(),
                                    "Friend Already in the list with same number ", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                }
            });
        }

    }

}
