package com.example.dell.finalfirstproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by owner on 28-Jan-17.
 */

public class SecurityDatabase extends SQLiteOpenHelper {
    public static final int database_version=1;
    public String CREATE_QUERY = "CREATE TABLE "+ SecurityTable.SecurityInfo.Table_NAME+"("+ SecurityTable.SecurityInfo.question+" TEXT,"+ SecurityTable.SecurityInfo.answer+" TEXT);";


    public SecurityDatabase(Context context) {
        super(context, SecurityTable.SecurityInfo.DATABASE_NAME, null, database_version);
        Log.d("Database Operations", "Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {

        sdb.execSQL(CREATE_QUERY);
        Log.d("Database Operations", "Table is Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private static int a=0;
    public boolean putInformation(SecurityDatabase dop,String question,String answer){

        SQLiteDatabase SQ=dop.getWritableDatabase();
        ContentValues CV=new ContentValues();
        String[]coloms={SecurityTable.SecurityInfo.question, SecurityTable.SecurityInfo.answer};
        Cursor CR=SQ.query(SecurityTable.SecurityInfo.Table_NAME,coloms,null,null,null,null,null);
        lo n;
        n=dop.getInformation(dop);
        CR.moveToFirst();
        boolean status=true;
        if(a!=0) {

            do {
                if (question.equals(CR.getString(0))) {

                    status = false;

                }
            } while (CR.moveToNext());
        }

        if(status) {
            CV.put(SecurityTable.SecurityInfo.question, question);
            CV.put(SecurityTable.SecurityInfo.answer, answer);
            long k = SQ.insert(SecurityTable.SecurityInfo.Table_NAME, null, CV);
            Log.d("Database INSERT", "Friend Inserted Successfully");
            a=1;
            return true;
        }
        else { return false; }

    }
    public lo getInformation(SecurityDatabase dop){
        lo l;
        SQLiteDatabase SQ=dop.getReadableDatabase();
        String[]coloms={SecurityTable.SecurityInfo.question, SecurityTable.SecurityInfo.answer};
        String count="SELECT count(*) FROM "+ SecurityTable.SecurityInfo.Table_NAME;
        SQLiteDatabase cv=dop.getWritableDatabase();
        Cursor mcursor=cv.rawQuery(count,null);
        mcursor.moveToFirst();
        int i=mcursor.getInt(0);
        if(i>0) {
            Cursor CR = SQ.query(SecurityTable.SecurityInfo.Table_NAME, coloms, null, null, null, null, null);
            int suc=1;
            l=new lo(CR,suc);
            return l;
        }
        else {
            l=new lo(null,0);
            return l;
        }
    }
}
