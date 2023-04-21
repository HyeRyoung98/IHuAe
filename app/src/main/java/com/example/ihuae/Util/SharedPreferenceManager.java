package com.example.ihuae.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SharedPreferenceManager {
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private static SharedPreferences getPreferences(Context context){
        return context.getSharedPreferences("user_preference", Context.MODE_PRIVATE);
    }

    public static void setStartDay(Context context,  Date startDay){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        String startDayString = format.format(startDay);

        editor.putString("startDay", startDayString);
        editor.apply();
    }

    public static Date getStartDay(Context context) {
        SharedPreferences prefs = getPreferences(context);
        String startDayString = prefs.getString("startDay", null);
        Date startDay = new Date();
        try {
            if(startDayString!=null) startDay = format.parse(startDayString);
        }catch (Exception e){
            Log.e("error", "[SharedPreferences - getStartDay] error");
        }
        return startDay;
    }

    public static void clearAll(Context context){
        SharedPreferences sf = getPreferences(context);
        SharedPreferences.Editor editor = sf.edit();
        editor.clear();
        editor.apply();
    }
/*
    public static void setSubscribeVO(Context context, SubscribeVO subscribeVO){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(subscribeVO);
        editor.putString("subscribe", json);

        editor.apply();
    }


    public static SubscribeVO getSubscribeVO(Context context){
        SharedPreferences prefs = getPreferences(context);
        String json = prefs.getString("subscribe", null);
        Gson gson = new Gson();
        return gson.fromJson(json, SubscribeVO.class);
    }

    public static void clearAll(Context context){
        SharedPreferences sf = getPreferences(context);
        SharedPreferences.Editor editor = sf.edit();
        editor.clear();
        editor.apply();
    }
*/
}
