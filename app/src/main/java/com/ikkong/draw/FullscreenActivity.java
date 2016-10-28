package com.ikkong.draw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ikkong.draw.fragment.DrawMenuColorFragment;
import com.ikkong.draw.fragment.DrawMenuTypeFragment;

public class FullscreenActivity extends AppCompatActivity implements 
        DrawMenuColorFragment.onSelPencilColorListerner {
    
    DrawMenuTypeFragment drawMenuTypeFragment;
    DrawMenuColorFragment drawMenuColorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        drawMenuTypeFragment = (DrawMenuTypeFragment) getFragmentManager().findFragmentById(R.id.frag_menu_type);
        drawMenuColorFragment = (DrawMenuColorFragment)getFragmentManager().findFragmentById(R.id.frag_menu_color);

    }

    @Override
    public void onSelPencilColor(int i) {
        //选了颜色改变笔尖
        drawMenuTypeFragment.changePencilTopColor(i);
    }
}
