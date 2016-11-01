package com.ikkong.draw.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2016/11/1
 * Description:
 */

public class FileUtil {

    public static String viewSaveToImage(View view) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);

        // 把一个View转换成图片  
        Bitmap cachebmp = loadBitmapFromView(view);

        FileOutputStream fos;
        String fileName;
        try {
            // 判断手机设备是否有SD卡  
            boolean isHasSDCard = Environment.getExternalStorageState().equals(
                    android.os.Environment.MEDIA_MOUNTED);
            if (isHasSDCard) {
                // SD卡根目录  
                File sdRoot = Environment.getExternalStorageDirectory();
                fileName = "/DrawingView/"+ UUID.randomUUID().toString()+".png";
                File file = new File(sdRoot, fileName);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                fos = new FileOutputStream(file);
            } else
                throw new Exception("创建文件失败!");

            cachebmp.compress(Bitmap.CompressFormat.PNG, 90, fos);

            fos.flush();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        view.destroyDrawingCache();
        return fileName;
    }

    private static Bitmap loadBitmapFromView(View v) {
        int w = v.getWidth();
        int h = v.getHeight();

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */
//        v.layout(0, 0, w, h);
        v.draw(c);
        return bmp;
    }
}
