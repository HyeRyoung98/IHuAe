package com.example.ihuae;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ihuae.Util.DBContract;
import com.example.ihuae.Util.MainDBHelper;

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

        createData();
        nextActivity();     //todo createData() 가 끝나면 실행 되도록 수정
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

    private void createData(){
        //todo 시작일 저장 > 첫 접속 체크 플래그
        //SharedPreferenceManager.setStartDay(this, new Date());
        init();
        setTables();

    }

    private void init(){
        dbHelper = new MainDBHelper(this);
    }

    private void setTables(){
        Calendar c = Calendar.getInstance();
        int cnt = 0;
        while (cnt < 30) {
            cnt++;
            c.add(Calendar.DATE, 1);
            Date d = c.getTime();

            int dateID = Integer.parseInt(sdf.format(d));
            setCalendarTable(c, dateID);
            setEmoIcTable(dateID);
            setEmoTxtTable(dateID);
            setQnATable(dateID);
            setGuideCardTable(dateID);
        }
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

            dbHelper.insertData(DBContract.CalendarEntry.TABLE_NAME, m);
        }

    private void setEmoIcTable(int dateID){
        HashMap<String, Object> m = new HashMap<>();
        m.put(DBContract.EmoIcEntry.COLUMN_NAME_1, dateID);
        m.put(DBContract.EmoIcEntry.COLUMN_NAME_2, "");
        dbHelper.insertData(DBContract.EmoIcEntry.TABLE_NAME, m);
    }

    private void setEmoTxtTable(int dateID){
        HashMap<String, Object> m = new HashMap<>();
        m.put(DBContract.EmoTxtEntry.COLUMN_NAME_1, dateID);
        m.put(DBContract.EmoTxtEntry.COLUMN_NAME_2, "");
        dbHelper.insertData(DBContract.EmoTxtEntry.TABLE_NAME, m);
    }

    private void setQnATable(int dateID){
        HashMap<String, Object> m = new HashMap<>();
        m.put(DBContract.QnAEntry.COLUMN_NAME_1, dateID);
        m.put(DBContract.QnAEntry.COLUMN_NAME_2, "");       //todo 질문 넣기, String 더미 제작 예정
        m.put(DBContract.QnAEntry.COLUMN_NAME_3, "");
        dbHelper.insertData(DBContract.QnAEntry.TABLE_NAME, m);
    }

    private void setGuideCardTable(int dateID){
        HashMap<String, Object> m = new HashMap<>();
        m.put(DBContract.GuideCardEntry.COLUMN_NAME_1, dateID);
        m.put(DBContract.GuideCardEntry.COLUMN_NAME_2, "");     //todo 가이드 카드 내용 넣기, String 더미 제작 예정
        m.put(DBContract.GuideCardEntry.COLUMN_NAME_3, "");     //todo 가이드 카드 이미지 이름 넣기, String 더미 제작 예정
        dbHelper.insertData(DBContract.GuideCardEntry.TABLE_NAME, m);
    }
}



