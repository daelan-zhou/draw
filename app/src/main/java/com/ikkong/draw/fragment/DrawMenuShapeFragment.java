package com.ikkong.draw.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.ikkong.draw.R;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2016/10/31
 * Description:
 */

public class DrawMenuShapeFragment extends Fragment{
    LinearLayout layShape;
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.draw_menu_shape,null);
        layShape = (LinearLayout) v.findViewById(R.id.lay_shape);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        return v;
    }

}
