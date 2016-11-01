package com.ikkong.draw.common;

import com.ikkong.draw.R;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2016/10/28
 * Description:
 */

public class Constant {
    public enum DrawType{
        PENCIL,ERASER
    }
    public enum DrawShape{
        LINE,RECT,PEN,CIRCLE,TEXT
    }
    public static final int COLORS[] = new int[]{
            R.color.draw_color_0,
            R.color.draw_color_1,
            R.color.draw_color_2,
            R.color.draw_color_3,
            R.color.draw_color_4,
            R.color.draw_color_5,
            R.color.draw_color_6,
            R.color.draw_color_7
    };
}
