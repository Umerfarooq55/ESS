package com.example.dell.finalfirstproject;

import android.provider.BaseColumns;

/**
 * Created by owner on 11-Sep-16.
 */
public class TableData {
    public TableData() {
    }
    public static abstract class TableInfo implements BaseColumns{
        public static final String USER_NAME="user_name";
        public static final String USER_PASS="user_pass";
        public static final String USER_EMAIL="user_email";
        public static final String USER_PHONE="user_phone";
        public static final String DATABASE_NAME="user_info";
        public static final String Table_NAME="reg_info";

    }

}
