package com.example.dell.finalfirstproject;

import android.database.Cursor;

/**
 * Created by owner on 28-Sep-16.
 */

class lo{
    private Cursor c;
    private int s;

    public Cursor getC() {
        return c;
    }

    public void setC(Cursor c) {
        this.c = c;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public lo() {
    }

    public lo(Cursor c, int s) {
        this.c = c;
        this.s = s;
    }
}