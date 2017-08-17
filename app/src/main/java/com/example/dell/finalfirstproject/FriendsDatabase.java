package com.example.dell.finalfirstproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.dell.finalfirstproject.FriendsTable.*;
/**
 * Created by owner on 11-Sep-16.
 */
public class FriendsDatabase extends SQLiteOpenHelper{
    public static final int database_version=1;
    public String CREATE_QUERY = "CREATE TABLE "+ FriendsInfo.Table_NAME+"("+ FriendsInfo.Friend_NAME+" TEXT,"+ FriendsInfo.Friend_EMAIL+" TEXT,"+ FriendsInfo.Friend_PHONE+" TEXT);";


    public FriendsDatabase(Context context) {
        super(context, FriendsInfo.DATABASE_NAME, null, database_version);
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
    public boolean putInformation(FriendsDatabase dop,String name,String email,String phone){

        SQLiteDatabase SQ=dop.getWritableDatabase();
        ContentValues CV=new ContentValues();
        String[]coloms={FriendsInfo.Friend_NAME,FriendsInfo.Friend_EMAIL,FriendsInfo.Friend_PHONE};
        Cursor CR=SQ.query(FriendsInfo.Table_NAME,coloms,null,null,null,null,null);
       lo n;
        n=dop.getInformation(dop);
        CR.moveToFirst();
        boolean status=true;
        if(a!=0) {

            do {
                if (phone.equals(CR.getString(2))) {

                    status = false;

                }
            } while (CR.moveToNext());
        }

        if(status) {
            CV.put(FriendsInfo.Friend_NAME, name);
            CV.put(FriendsInfo.Friend_EMAIL, email);
            CV.put(FriendsInfo.Friend_PHONE, phone);
            long k = SQ.insert(FriendsInfo.Table_NAME, null, CV);
            Log.d("Database INSERT", "Friend Inserted Successfully");
            a=1;
            return true;
        }
        else { return false; }

    }
    public lo getInformation(FriendsDatabase dop){
        lo l;
        SQLiteDatabase SQ=dop.getReadableDatabase();
        String[]coloms={FriendsInfo.Friend_NAME,FriendsInfo.Friend_EMAIL,FriendsInfo.Friend_PHONE};
        String count="SELECT count(*) FROM "+FriendsInfo.Table_NAME;
        SQLiteDatabase cv=dop.getWritableDatabase();
        Cursor mcursor=cv.rawQuery(count,null);
        mcursor.moveToFirst();
        int i=mcursor.getInt(0);
        if(i>0) {
            Cursor CR = SQ.query(FriendsInfo.Table_NAME, coloms, null, null, null, null, null);
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
