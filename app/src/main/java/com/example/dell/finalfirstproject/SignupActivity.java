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

public class SignupActivity extends AppCompatActivity {
    EditText editTextUserName,editEmail, editTextPassword, editTextConfirmPassword,editTextphone;
    Button btnCreateAccount;
    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        editEmail= (EditText) findViewById(R.id.editEmail);
        editTextphone= (EditText) findViewById(R.id.editTextphone);

        Button b=(Button) findViewById(R.id.buttonCreateAccount);
        if (b != null) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userName = editTextUserName.getText().toString();
                    String password = editTextPassword.getText().toString();
                    String confirmPassword = editTextConfirmPassword.getText()
                            .toString();
                    String Email=editEmail.getText().toString();
                    String Phone=editTextphone.getText().toString();
                    if (userName.equals("") || password.equals("")
                            || confirmPassword.equals("") || Email.equals("")||Phone.equals("")) {

                        Toast.makeText(getApplicationContext(), "Fill the all Field",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (!password.equals(confirmPassword)) {
                        Toast.makeText(getApplicationContext(),
                                "Password must be same", Toast.LENGTH_LONG)
                                .show();
                        return;
                    }
                    else {
                            DatabaseOperations db=new DatabaseOperations(context);
                                boolean status=db.putInformation(db,userName,password,Email,Phone);
                        if(status) {
                            Toast.makeText(getApplicationContext(),
                                    "Registered SUCCESSFULLY ", Toast.LENGTH_LONG)
                                    .show();
                            Intent i = new Intent(SignupActivity.this,
                                    loginActivity.class);
                            startActivity(i);

                            finish();
                        }
                        else {
                            editTextUserName.setText("");
                            editTextPassword.setText("");
                            editEmail.setText("");
                            editTextConfirmPassword.setText("");
                            editTextphone.setText("");
                            Toast.makeText(getApplicationContext(),
                                    "User Already EXIST ", Toast.LENGTH_LONG)
                                    .show();
                        }
                        }

                         /*   Toast.makeText(getApplicationContext(),
                                    "Problem in database ", Toast.LENGTH_LONG)
                                    .show();
*/

                    }
                    //Intent intent = new Intent(SignupActivity.this, varify.class);
                    //startActivity(intent);


            });
        }

    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();


    }
}
