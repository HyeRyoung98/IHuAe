package com.example.ihuae.Util;

import android.content.Context;
import android.util.DisplayMetrics;

public class DpToPxConverter {

    public static int convert(Context context, int dp){
        int dpToPx = 0;
        dpToPx = (int) (dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return dpToPx;
    }
    public static float convert(Context context,float dp){
        float dpToPx = 0;
        dpToPx =(dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return dpToPx;
    }
}
