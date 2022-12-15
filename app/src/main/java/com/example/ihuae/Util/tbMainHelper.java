package com.example.ihuae.Util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class tbMainHelper extends SQLiteOpenHelper {

    public tbMainHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE if not exists tbMain ("
                + "_id integer primary key autoincrement,"
                + "dDay integer,"
                + "date text);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE if exists tbMain";

        db.execSQL(sql);
        onCreate(db);
    }

    // tbMain Table 데이터 입력
    public void insert(int dDay, String date) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO tbMain VALUES(" + dDay + ", '" + date + "')");
        db.close();
    }

    // tbMain Table 조회
    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM tbMain", null);
        while (cursor.moveToNext()) {
            result += " dDay : "
                    + cursor.getInt(0)
                    + ", date : "
                    + cursor.getString(1)
                    + "\n";
        }

        return result;
    }
}
