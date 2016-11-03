package com.ikkong.draw.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ikkong.draw.R;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2016/10/28
 * Description:
 */

public class DrawMenuColorFragment extends Fragment implements View.OnClickListener{
    ImageView iv0,iv1,iv2,iv3,iv4,iv5,iv6,iv7;
    onSelPencilColorListerner listerner;
    int nowColorIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.draw_menu_color,null);
        iv0 = (ImageView) v.findViewById(R.id.iv_0);
        iv1 = (ImageView) v.findViewById(R.id.iv_1);
        iv2 = (ImageView) v.findViewById(R.id.iv_2);
        iv3 = (ImageView) v.findViewById(R.id.iv_3);
        iv4 = (ImageView) v.findViewById(R.id.iv_4);
        iv5 = (ImageView) v.findViewById(R.id.iv_5);
        iv6 = (ImageView) v.findViewById(R.id.iv_6);
        iv7 = (ImageView) v.findViewById(R.id.iv_7);
        iv0.setOnClickListener(this);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        iv6.setOnClickListener(this);
        iv7.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_0:
                nowColorIndex = 0;
                selColor(iv0);
                break;
            case R.id.iv_1:
                nowColorIndex = 1;
                selColor(iv1);
                break;
            case R.id.iv_2:
                nowColorIndex = 2;
                selColor(iv2);
                break;
            case R.id.iv_3:
                nowColorIndex = 3;
                selColor(iv3);
                break;
            case R.id.iv_4:
                nowColorIndex = 4;
                selColor(iv4);
                break;
            case R.id.iv_5:
                nowColorIndex = 5;
                selColor(iv5);
                break;
            case R.id.iv_6:
                nowColorIndex = 6;
                selColor(iv6);
                break;
            case R.id.iv_7:
                nowColorIndex = 7;
                selColor(iv7);
                break;
        }
        listerner.onSelPencilColor(nowColorIndex);
    }
    
    private void selColor(ImageView iv){
        iv0.setImageResource(R.mipmap.ic_none);
        iv1.setImageResource(R.mipmap.ic_none);
        iv2.setImageResource(R.mipmap.ic_none);
        iv3.setImageResource(R.mipmap.ic_none);
        iv4.setImageResource(R.mipmap.ic_none);
        iv5.setImageResource(R.mipmap.ic_none);
        iv6.setImageResource(R.mipmap.ic_none);
        iv7.setImageResource(R.mipmap.ic_none);
        iv.setImageResource(R.mipmap.ic_ok);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            listerner = (onSelPencilColorListerner) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()
                    + " must implement onSelPencilColorListerner");
        }
    }

    public interface onSelPencilColorListerner{
        void onSelPencilColor(int i);
    }
}
