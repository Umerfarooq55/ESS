package com.example.dell.finalfirstproject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class securityQuestion extends AppCompatActivity {
    TextView t;
    EditText sq,sa;
    SecurityDatabase dop;
    String question,answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sq=(EditText)findViewById(R.id.TextEnterQuestion);
        sa=(EditText)findViewById(R.id.editText);
        dop=new SecurityDatabase(this);
        lo l=dop.getInformation(dop);
        Cursor CR=l.getC();
        int sucsess=l.getS();
        if(sucsess==0)
        {
            t= (TextView) findViewById(R.id.textView);
            t.setText("Sorry No Security Question please add Question first");
        }
        else {
            int i = 0;
            CR.moveToFirst();
            do {
                question=CR.getString(0);
                answer=CR.getString(1);

                i++;
            } while (CR.moveToNext());

            sq.setText(question);
            sa.setText(answer);
            }
        Button sub= (Button) findViewById(R.id.buttonSignIn);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sqinput = sq.getText().toString();
                String sainput = sa.getText().toString();
                if(sqinput!=null && sainput!=null){
                    boolean status=dop.putInformation(dop,sqinput,sainput);
                    if(status) {
                        Toast.makeText(getApplicationContext(),
                                "Security Question Added SUCCESSFULLY ", Toast.LENGTH_LONG)
                                .show();

                        }
                    }
                else {
                    Toast.makeText(getApplicationContext(),"Input Properly",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}



