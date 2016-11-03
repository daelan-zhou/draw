package com.ikkong.draw.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ikkong.draw.R;
import com.ikkong.draw.common.Constant;
import com.ikkong.draw.util.DensityUtil;

/**
 * Author:  ikkong
 * Email:   ikkong@163.com
 * Date:    2016/10/25
 * Description:
 */

public class DrawMenuTypeFragment extends Fragment implements View.OnClickListener{
    private ImageView ivPencilTop;
    private LinearLayout layPencil,layEraser;
    private Constant.DrawType drawType;
    private OnTypeChangeListerner listerner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.draw_menu_type,null);
        ivPencilTop = (ImageView) v.findViewById(R.id.iv_pencil_top);
        layPencil = (LinearLayout) v.findViewById(R.id.lay_pencil);
        layEraser = (LinearLayout) v.findViewById(R.id.lay_eraser);
        layEraser.setOnClickListener(this);
        layPencil.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.lay_eraser){
            Log.d("onClick","====iv_eraser====");
            LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) layPencil.getLayoutParams();
            lp2.setMargins(d2p(10),d2p(60),d2p(5),d2p(0));
            layPencil.setLayoutParams(lp2);
            LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) layEraser.getLayoutParams();
            lp1.setMargins(d2p(5),d2p(20),d2p(6),d2p(0));
            layEraser.setLayoutParams(lp1);
            drawType = Constant.DrawType.ERASER;
            listerner.onTypeChange(drawType);
        }else if(view.getId() == R.id.lay_pencil){
            Log.d("onClick","====lay_pencil====");
            LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) layPencil.getLayoutParams();
            lp2.setMargins(d2p(10),d2p(10),d2p(5),d2p(0));
            layPencil.setLayoutParams(lp2);
            LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) layEraser.getLayoutParams();
            lp1.setMargins(d2p(5),d2p(70),d2p(6),d2p(0));
            layEraser.setLayoutParams(lp1);
            drawType = Constant.DrawType.PENCIL;
            listerner.onTypeChange(drawType);
        }
    }

    /**
     * 改变笔尖颜色
     * @param colorIndex
     */
    public void changePencilTopColor(int colorIndex){
        switch (colorIndex){
            case 0:
                ivPencilTop.setImageResource(R.drawable.pencil_top_0);
                break;
            case 1:
                ivPencilTop.setImageResource(R.drawable.pencil_top_1);
                break;
            case 2:
                ivPencilTop.setImageResource(R.drawable.pencil_top_2);
                break;
            case 3:
                ivPencilTop.setImageResource(R.drawable.pencil_top_3);
                break;
            case 4:
                ivPencilTop.setImageResource(R.drawable.pencil_top_4);
                break;
            case 5:
                ivPencilTop.setImageResource(R.drawable.pencil_top_5);
                break;
            case 6:
                ivPencilTop.setImageResource(R.drawable.pencil_top_6);
                break;
            case 7:
                ivPencilTop.setImageResource(R.drawable.pencil_top_7);
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            listerner = (OnTypeChangeListerner) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnTypeChangeListerner");
        }
    }

    private int d2p(int dp){
        return DensityUtil.dip2px(getActivity(),dp);
    }
    
    public interface OnTypeChangeListerner{
        void onTypeChange(Constant.DrawType type);
    }
}
