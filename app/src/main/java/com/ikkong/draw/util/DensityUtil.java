package com.ikkong.draw.util;

import android.content.Context;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2016/10/27
 * Description:
 */

public class DensityUtil {
    public static int dip2px(Context c,int dp){
        float density = c.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    public static int px2dip(Context c,int px){
        float density = c.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }
}
