package com.example.ihuae.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

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

    public boolean insertData(String tableNM, HashMap<String, Object> datas){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            ArrayList<String> keys = (ArrayList<String>) datas.keySet();
            for (String key : keys) {
                String type = datas.get(key).getClass().getName();
                if (type.equals("Integer")) contentValues.put(key, (Integer) datas.get(key));
                else if (type.equals("Date")) contentValues.put(key, (byte[]) datas.get(key));
                else contentValues.put(key, (String) datas.get(key));
            }
            long result = db.insert(tableNM, null, contentValues);
            if (result == -1)
                return false;
            else
                return true;
        }catch (Exception e){
            Log.e("===============MainDBHelper insertData Exception================",e.getMessage());
            return false;
        }
    }

    public Cursor selectData(String tableNM, String sql){
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(sql, null);
        }catch (Exception e){
            Log.e("===============MainDBHelper selectData Exception================", e.getMessage());
        }
        return cursor;
    }

}
