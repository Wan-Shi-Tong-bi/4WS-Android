package com.example.gps.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.gps.MainActivity;

public class MySqLiteHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "gps.db";
    private final static int DB_VERSION = 1;


    public MySqLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE GPSDaten (gpsID int, longitude number, latitude number, date varchar(255));");
        db.execSQL("INSERT INTO GPSDaten VALUES (1, 12.4323, 32.32323, '02.02.20');");
        db.execSQL("INSERT INTO GPSDaten VALUES (2, 234.34, 3.23, '02.02.20');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
