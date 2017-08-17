package com.example.dell.finalfirstproject;

import android.provider.BaseColumns;

/**
 * Created by owner on 11-Sep-16.
 */
public class FriendsTable {
    public FriendsTable() {
    }
    public static abstract class FriendsInfo implements BaseColumns {
        public static final String Friend_NAME="friend_name";
        public static final String Friend_EMAIL="user_email";
        public static final String Friend_PHONE="user_phone";
        public static final String DATABASE_NAME="friends";
        public static final String Table_NAME="add_friend";

    }

}
