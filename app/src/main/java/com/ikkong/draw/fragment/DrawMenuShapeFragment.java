package com.ikkong.draw.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.ikkong.draw.R;
import com.ikkong.draw.common.Constant;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2016/10/31
 * Description:
 */

public class DrawMenuShapeFragment extends Fragment{
    LinearLayout layShape;
    Spinner spinner;
    OnShapeChangeListerner listerner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.draw_menu_shape,null);
        layShape = (LinearLayout) v.findViewById(R.id.lay_shape);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (spinner.getSelectedItem().toString()){
                    case "任意":
                        listerner.onShapeChange(Constant.DrawShape.PEN);
                        break;
                    case "直线":
                        listerner.onShapeChange(Constant.DrawShape.LINE);
                        break;
                    case "方形":
                        listerner.onShapeChange(Constant.DrawShape.RECT);
                        break;
                    case "圆形":
                        listerner.onShapeChange(Constant.DrawShape.CIRCLE);
                        break;
                    case "文字":
                        listerner.onShapeChange(Constant.DrawShape.TEXT);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                listerner.onShapeChange(Constant.DrawShape.PEN);
            }
        });
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            listerner = (OnShapeChangeListerner) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnShapeChangeListerner");
        }
    }

    public interface OnShapeChangeListerner{
        void onShapeChange(Constant.DrawShape shape);
    }

}
