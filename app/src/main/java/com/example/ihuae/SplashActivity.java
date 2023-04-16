package com.example.ihuae;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ihuae.Util.MainDBHelper;
import com.example.ihuae.Util.SharedPreferenceManager;

import java.util.Date;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        nextActivity();
    }


    private void nextActivity(){
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                //SubscribeVO subscribeVO = SharedPreferenceManager.getSubscribeVO(SplashActivity.this);
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
        //시작일 저장
        SharedPreferenceManager.setStartDay(this, new Date());

        MainDBHelper dbHelper = new MainDBHelper(this);

        //todo
        /***
         *
         * db -
         */
    }
}



