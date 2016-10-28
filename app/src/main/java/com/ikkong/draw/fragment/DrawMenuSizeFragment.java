package com.ikkong.draw.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ikkong.draw.R;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2016/10/28
 * Description:
 */

public class DrawMenuSizeFragment extends Fragment implements View.OnClickListener{
    LinearLayout laySize;
    TextView tvSize;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.draw_menu_size,null);
        laySize = (LinearLayout) v.findViewById(R.id.lay_size);
        tvSize = (TextView) v.findViewById(R.id.tv_size);
        laySize.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.lay_size){
            //弹出一个seekbar
        }
    }
}
