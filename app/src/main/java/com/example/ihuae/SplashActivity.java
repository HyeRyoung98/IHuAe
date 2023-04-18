package com.example.ihuae;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ihuae.Util.DBContract;
import com.example.ihuae.Util.MainDBHelper;
import com.example.ihuae.Util.SharedPreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class SplashActivity extends AppCompatActivity {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private MainDBHelper dbHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
        createDB();
        nextActivity();
    }

    private void init(){
        dbHelper = new MainDBHelper(this);
    }


    private void nextActivity(){
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                requestNextActivity();
            }
        }, 3000);
    }

    private void requestNextActivity(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void createDB(){
        //todo 시작일 저장 > 첫 접속 체크 플래그
        //SharedPreferenceManager.setStartDay(this, new Date());
        setCalendarTable();

    }

    private void setCalendarTable(){
        Calendar c = Calendar.getInstance();
        int cnt = 0;
        while (cnt < 30){
            cnt++;
            c.add(Calendar.DATE, 1);
            Date d = c.getTime();

            int dateID = Integer.parseInt(sdf.format(d));
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            HashMap<String, Object> calMap = new HashMap<>();
            calMap.put(DBContract.CalendarEntry.COLUMN_NAME_1, dateID);
            calMap.put(DBContract.CalendarEntry.COLUMN_NAME_2, year);
            calMap.put(DBContract.CalendarEntry.COLUMN_NAME_3, month);
            calMap.put(DBContract.CalendarEntry.COLUMN_NAME_4, day);

            dbHelper.insertData(DBContract.CalendarEntry.TABLE_NAME, calMap);
        }
    }
}



