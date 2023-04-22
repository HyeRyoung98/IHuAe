package com.example.ihuae;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ihuae.Util.ArrayItems;
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
    private ArrayItems arrayItems = new ArrayItems();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Date startDay = SharedPreferenceManager.getStartDay(this);
        if(startDay==null){
            startDay = new Date();
            createData(startDay);
        }else {
            long cal = new Date().getTime() - startDay.getTime();
            MainActivity.dDay = (int) (cal / (24 * 60 * 60 * 1000));
            MainActivity.dDay += 1;
            MainActivity.dDay = MainActivity.dDay > 30 ? 30:MainActivity.dDay;      //30일 이후는 막기
        }
        nextActivity();
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

    private void createData(Date startDate){
        init();
        SharedPreferenceManager.setStartDay(this, startDate);
        setTables(startDate);

    }

    private void init(){
        dbHelper = new MainDBHelper(this);
    }

    private void setTables(Date startDate){
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        int cnt = 0;
        while (cnt < arrayItems.questions.length) {
            Date d = c.getTime();
            int dateID = Integer.parseInt(sdf.format(d));

            setCalendarTable(c, dateID);
            setEmoTable(dateID);
            setQnATable(dateID, cnt);
            setGuideCardTable(dateID, cnt);

            c.add(Calendar.DATE, 1);
            cnt++;
        }
        setIconTable();
    }

    private void setCalendarTable(Calendar c, int dateID){
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            HashMap<String, Object> m = new HashMap<>();
            m.put(DBContract.CalendarEntry.COLUMN_NAME_1, dateID);
            m.put(DBContract.CalendarEntry.COLUMN_NAME_2, year);
            m.put(DBContract.CalendarEntry.COLUMN_NAME_3, month);
            m.put(DBContract.CalendarEntry.COLUMN_NAME_4, day);
            boolean isSuccess = dbHelper.insertData(DBContract.CalendarEntry.TABLE_NAME, m);
            if(isSuccess) Log.e("===============Success setCalendarTable================", "dateID = " + dateID);
            else Log.e("===============Fail setCalendarTable================", "dateID = " + dateID);

        }

    private void setEmoTable(int dateID){
        HashMap<String, Object> m = new HashMap<>();
        m.put(DBContract.EmoEntry.COLUMN_NAME_1, dateID);
        m.put(DBContract.EmoEntry.COLUMN_NAME_2, -1);
        m.put(DBContract.EmoEntry.COLUMN_NAME_3, "");
        boolean isSuccess = dbHelper.insertData(DBContract.EmoEntry.TABLE_NAME, m);
        if(isSuccess) Log.e("===============Success setEmoTable================", "dateID = " + dateID);
        else Log.e("===============Fail setEmoTable================", "dateID = " + dateID);
    }


    private void setQnATable(int dateID, int cnt){
        String qes = arrayItems.questions[cnt];
        HashMap<String, Object> m = new HashMap<>();
        m.put(DBContract.QnAEntry.COLUMN_NAME_1, dateID);
        m.put(DBContract.QnAEntry.COLUMN_NAME_2, qes);
        m.put(DBContract.QnAEntry.COLUMN_NAME_3, "");
        boolean isSuccess = dbHelper.insertData(DBContract.QnAEntry.TABLE_NAME, m);
        if(isSuccess) Log.e("===============Success setQnATable================", "dateID = " + dateID);
        else Log.e("===============Fail setQnATable================", "dateID = " + dateID);
    }

    private void setGuideCardTable(int dateID, int cnt){
        HashMap<String, Object> m = new HashMap<>();
        m.put(DBContract.GuideCardEntry.COLUMN_NAME_1, dateID);
        m.put(DBContract.GuideCardEntry.COLUMN_NAME_2, "");     //todo 가이드 카드 내용 넣기, 정말 필요한 지 검토.
        m.put(DBContract.GuideCardEntry.COLUMN_NAME_3, arrayItems.IDs[cnt]);
        boolean isSuccess = dbHelper.insertData(DBContract.GuideCardEntry.TABLE_NAME, m);
        if(isSuccess) Log.e("===============Success setGuideCardTable================", "dateID = " + dateID);
        else Log.e("===============Fail setGuideCardTable================", "dateID = " + dateID);
    }

    private void setIconTable(){
        for(int i = 0 ; i < arrayItems.emoIcons.length ; i++){
            HashMap<String, Object> m = new HashMap<>();
            m.put(DBContract.IconEntry.COLUMN_NAME_1, arrayItems.emoIcons[i]);
            m.put(DBContract.IconEntry.COLUMN_NAME_2, arrayItems.emoGuides[i]);
            boolean isSuccess = dbHelper.insertData(DBContract.IconEntry.TABLE_NAME, m);
            if(isSuccess) Log.e("===============Success setIconTable================", "IconID = " + i);
            else Log.e("===============Fail setIconTable================", "IconID = " + i);
        }

    }
}



