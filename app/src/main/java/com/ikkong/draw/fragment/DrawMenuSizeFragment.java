package com.ikkong.draw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ikkong.draw.R;
import com.ikkong.draw.popupwindow.SeekBarPopWindow;
import com.labo.kaji.relativepopupwindow.RelativePopupWindow.HorizontalPosition;
import com.labo.kaji.relativepopupwindow.RelativePopupWindow.VerticalPosition;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2016/10/28
 * Description:
 */

public class DrawMenuSizeFragment extends Fragment implements View.OnClickListener {
    LinearLayout laySize;
    TextView tvSize;
    int size;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.draw_menu_size, null);
        laySize = (LinearLayout) v.findViewById(R.id.lay_size);
        tvSize = (TextView) v.findViewById(R.id.tv_size);
        laySize.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Log.d("DrawMenuSizeFragment", "====lay_size====");
        
        //弹出一个seekbar
        new SeekBarPopWindow(getActivity(),size,listerner).showOnAnchor(laySize,
                VerticalPosition.BELOW, HorizontalPosition.CENTER);
    }

    SeekBarPopWindow.onSeekBarValueChangeListerner listerner = new SeekBarPopWindow.onSeekBarValueChangeListerner() {
        @Override
        public void onSeekBarValueChange(int i) {
            size = i;
            tvSize.setText(size+"");
        }
    };
    
    public int getSize(){
        return size;
    }
}
