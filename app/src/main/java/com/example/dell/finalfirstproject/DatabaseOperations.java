package com.example.dell.finalfirstproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dell.finalfirstproject.TableData.TableInfo;

/**
 * Created by owner on 11-Sep-16.
 */
public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_version=1;
    public String CREATE_QUERY = "CREATE TABLE "+ TableInfo.Table_NAME+"("+TableInfo.USER_NAME+" TEXT,"+TableInfo.USER_PASS+" TEXT,"+TableInfo.USER_EMAIL+" TEXT,"+TableInfo.USER_PHONE+" TEXT);";


    public DatabaseOperations(Context context) {
        super(context, TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database Operations","Database Created");
    }
    private static int a=0;
    @Override
    public void onCreate(SQLiteDatabase sdb) {

        sdb.execSQL(CREATE_QUERY);
        Log.d("Database Operations", "Table is Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean putInformation(DatabaseOperations dop,String name,String pass,String email,String phone){

        SQLiteDatabase SQ=dop.getWritableDatabase();
        ContentValues CV=new ContentValues();
        String[]coloms={TableInfo.USER_NAME,TableInfo.USER_PASS,TableInfo.USER_EMAIL,TableInfo.USER_PHONE};
        Cursor CR=SQ.query(TableInfo.Table_NAME, coloms, null, null, null, null, null);

        boolean status=true;
            CR.moveToFirst();

            if (a != 0) {
                do {
                    if (name.equals(CR.getString(0))) {

                        status = false;

                    }
                } while (CR.moveToNext());
            }
            if (status) {
                CV.put(TableInfo.USER_NAME, name);
                CV.put(TableInfo.USER_PASS, pass);
                CV.put(TableInfo.USER_EMAIL, email);
                CV.put(TableInfo.USER_PHONE, phone);
                long k = SQ.insert(TableInfo.Table_NAME, null, CV);
                Log.d("Databse INSERT", "Account Successfully Created");
                a = 1;
                return true;
            } else {
                return false;
            }
        }

    public lo getInformation(DatabaseOperations dop){
        lo l;
        SQLiteDatabase SQ=dop.getReadableDatabase();
        String[]coloms={TableInfo.USER_NAME,TableInfo.USER_PASS,TableInfo.USER_EMAIL,TableInfo.USER_PHONE};
        String count="SELECT count(*) FROM "+ TableInfo.Table_NAME;
        SQLiteDatabase cv=dop.getWritableDatabase();
        Cursor mcursor=cv.rawQuery(count,null);
        mcursor.moveToFirst();
        int i=mcursor.getInt(0);
        if(i>0) {
            Cursor CR = SQ.query(TableInfo.Table_NAME, coloms, null, null, null, null, null);
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
