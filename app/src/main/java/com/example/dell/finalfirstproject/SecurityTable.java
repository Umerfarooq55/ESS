package com.example.dell.finalfirstproject;

import android.provider.BaseColumns;

/**
 * Created by owner on 29-Sep-16.
 */

public class SecurityTable {

    public SecurityTable() {
    }
    public static abstract class SecurityInfo implements BaseColumns {
        public static final String question="question";
        public static final String answer="answer";
        public static final String DATABASE_NAME="security";
        public static final String Table_NAME="qa";


    }
}
