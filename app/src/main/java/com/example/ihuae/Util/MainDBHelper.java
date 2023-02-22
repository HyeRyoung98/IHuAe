package com.example.ihuae.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Main.db";

    public MainDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.CalendarEntry.SQL_CREATE_ENTRIES);
        db.execSQL(DBContract.EmoIcEntry.SQL_CREATE_ENTRIES);
        db.execSQL(DBContract.QnAEntry.SQL_CREATE_ENTRIES);
        db.execSQL(DBContract.GuideCardEntry.SQL_CREATE_ENTRIES);
        db.execSQL(DBContract.DairyEntry.SQL_CREATE_ENTRIES);
        db.execSQL(DBContract.MessageEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(getTableDropSql(DBContract.CalendarEntry.TABLE_NAME));
        db.execSQL(getTableDropSql(DBContract.EmoIcEntry.TABLE_NAME));
        db.execSQL(getTableDropSql(DBContract.QnAEntry.TABLE_NAME));
        db.execSQL(getTableDropSql(DBContract.GuideCardEntry.TABLE_NAME));
        db.execSQL(getTableDropSql(DBContract.DairyEntry.TABLE_NAME));
        db.execSQL(getTableDropSql(DBContract.MessageEntry.TABLE_NAME));

        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private String getTableDropSql(String tableName){
        String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + tableName;
        return SQL_DELETE_ENTRIES;
    }

}
