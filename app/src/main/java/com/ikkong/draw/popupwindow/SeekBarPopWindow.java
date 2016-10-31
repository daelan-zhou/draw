package com.ikkong.draw.popupwindow;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.ikkong.draw.R;
import com.labo.kaji.relativepopupwindow.RelativePopupWindow;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2016/10/28
 * Description:
 */

public class SeekBarPopWindow extends RelativePopupWindow {

    public SeekBarPopWindow(Context context, int defValue, final onSeekBarValueChangeListerner listerner) {
        View v = LayoutInflater.from(context).inflate(R.layout.popup_seekbar, null);
        setContentView(v);
        SeekBar seekBar = (SeekBar) v.findViewById(R.id.seekbar);
        seekBar.setProgress(defValue);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // Disable default animation for circular reveal
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setAnimationStyle(0);
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (listerner != null) {
                    listerner.onSeekBarValueChange(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void showOnAnchor(@NonNull View anchor, int vertPos, int horizPos, int x, int y) {
        super.showOnAnchor(anchor, vertPos, horizPos, x, y);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circularReveal(anchor);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void circularReveal(@NonNull final View anchor) {
        final View contentView = getContentView();
        contentView.post(new Runnable() {
            @Override
            public void run() {
                final int[] myLocation = new int[2];
                final int[] anchorLocation = new int[2];
                contentView.getLocationOnScreen(myLocation);
                anchor.getLocationOnScreen(anchorLocation);
                final int cx = anchorLocation[0] - myLocation[0] + anchor.getWidth() / 2;
                final int cy = anchorLocation[1] - myLocation[1] + anchor.getHeight() / 2;

                contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                final int dx = Math.max(cx, contentView.getMeasuredWidth() - cx);
                final int dy = Math.max(cy, contentView.getMeasuredHeight() - cy);
                final float finalRadius = (float) Math.hypot(dx, dy);
                Animator animator = ViewAnimationUtils.createCircularReveal(contentView, cx, cy, 0f, finalRadius);
                animator.setDuration(300);
                animator.start();
            }
        });
    }

    public interface onSeekBarValueChangeListerner {
        void onSeekBarValueChange(int i);
    }
}
