package com.ikkong.draw;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ikkong.draw.common.Constant;
import com.ikkong.draw.fragment.DrawMenuColorFragment;
import com.ikkong.draw.fragment.DrawMenuShapeFragment;
import com.ikkong.draw.fragment.DrawMenuSizeFragment;
import com.ikkong.draw.fragment.DrawMenuTypeFragment;
import com.ikkong.draw.util.FileUtil;
import com.vilyever.drawingview.DrawingView;
import com.vilyever.drawingview.brush.drawing.CenterCircleBrush;
import com.vilyever.drawingview.brush.drawing.DrawingBrush;
import com.vilyever.drawingview.brush.drawing.LineBrush;
import com.vilyever.drawingview.brush.drawing.PenBrush;
import com.vilyever.drawingview.brush.drawing.RectangleBrush;
import com.vilyever.drawingview.brush.drawing.ShapeBrush;
import com.vilyever.drawingview.brush.text.TextBrush;
import com.vilyever.drawingview.model.DrawingStep;

import java.util.ArrayList;
import java.util.List;

import static com.ikkong.draw.common.Constant.COLORS;

public class FullscreenActivity extends AppCompatActivity implements 
        DrawMenuColorFragment.onSelPencilColorListerner,
        DrawMenuTypeFragment.OnTypeChangeListerner,
        DrawMenuShapeFragment.OnShapeChangeListerner,
        DrawMenuSizeFragment.OnChangeSizeListerner {
    
    private List<ShapeBrush> shapeBrushes = new ArrayList<>();
    private PenBrush penBrush;
    private LineBrush lineBrush;
    private RectangleBrush rectangleBrush;
    private CenterCircleBrush circleBrush;
    private TextBrush textBrush;
    
    TextView tvClearAll,tvUndo,tvRedo,tvDone;
    private DrawingView drawingView;
    
    DrawMenuTypeFragment drawMenuTypeFragment;
    DrawMenuColorFragment drawMenuColorFragment;
    DrawMenuSizeFragment drawMenuSizeFragment;
    DrawMenuShapeFragment drawMenuShapeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        initView();
        
    }

    private void initView() {
        drawMenuTypeFragment = (DrawMenuTypeFragment) getFragmentManager().findFragmentById(R.id.frag_menu_type);
        drawMenuColorFragment = (DrawMenuColorFragment)getFragmentManager().findFragmentById(R.id.frag_menu_color);
        drawMenuSizeFragment = (DrawMenuSizeFragment)getFragmentManager().findFragmentById(R.id.frag_menu_size);
        drawMenuShapeFragment = (DrawMenuShapeFragment)getFragmentManager().findFragmentById(R.id.frag_menu_shape);
        tvClearAll = (TextView) this.findViewById(R.id.tv_clearall);
        tvUndo = (TextView) this.findViewById(R.id.tv_undo);
        tvRedo = (TextView) this.findViewById(R.id.tv_redo);
        tvDone = (TextView) this.findViewById(R.id.tv_done);
        drawingView = (DrawingView) this.findViewById(R.id.drawingView);

        drawingView.setUndoRedoStateDelegate(new DrawingView.UndoRedoStateDelegate() {
            @Override
            public void onUndoRedoStateChange(DrawingView drawingView, boolean canUndo, boolean canRedo) {
                tvUndo.setEnabled(canUndo);
                tvRedo.setEnabled(canRedo);
            }
        });

        drawingView.setInterceptTouchDelegate(new DrawingView.InterceptTouchDelegate() {
            @Override
            public void requireInterceptTouchEvent(DrawingView drawingView, boolean isIntercept) {

            }
        });

        drawingView.setDrawingStepDelegate(new DrawingView.DrawingStepDelegate() {
            @Override
            public void onDrawingStepBegin(DrawingView drawingView, DrawingStep step) {
            }

            @Override
            public void onDrawingStepChange(DrawingView drawingView, DrawingStep step) {

            }

            @Override
            public void onDrawingStepEnd(DrawingView drawingView, DrawingStep step) {
            }

            @Override
            public void onDrawingStepCancel(DrawingView drawingView, DrawingStep step) {
            }
        });

        drawingView.setBackgroundDatasource(new DrawingView.BackgroundDatasource() {
            @Override
            public Drawable gainBackground(DrawingView drawingView, String identifier) {
                return null;
            }
        });

        tvUndo.setEnabled(false);
        tvUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.undo();
            }
        });

        tvRedo.setEnabled(false);
        tvRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.redo();
            }
        });

        tvClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.clear();
            }
        });
        
        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().post(saveDrawingView);
            }
        });

        penBrush = PenBrush.defaultBrush();
        textBrush = TextBrush.defaultBrush();
        lineBrush = LineBrush.defaultBrush();
        rectangleBrush = RectangleBrush.defaultBrush();
        circleBrush = CenterCircleBrush.defaultBrush();
        shapeBrushes.add(lineBrush);
        shapeBrushes.add(rectangleBrush);
        shapeBrushes.add(circleBrush);
    }

    @Override
    public void onSelPencilColor(int i) {
        //选了颜色改变笔尖
        drawMenuTypeFragment.changePencilTopColor(i);
        
        penBrush.setColor(ContextCompat.getColor(this, COLORS[i]));
        textBrush.setColor(ContextCompat.getColor(this, COLORS[i]));
        for (DrawingBrush brush : shapeBrushes) {
            brush.setColor(ContextCompat.getColor(this, COLORS[i]));
        }
    }

    @Override
    public void onTypeChange(Constant.DrawType type) {
        if(type == Constant.DrawType.ERASER){
            getFragmentManager().beginTransaction().hide(drawMenuColorFragment).commit();
        }else if(type == Constant.DrawType.PENCIL){
            getFragmentManager().beginTransaction().show(drawMenuColorFragment).commit();
        }
        penBrush.setIsEraser(type == Constant.DrawType.ERASER);
        for (DrawingBrush brush : shapeBrushes) {
            brush.setIsEraser(type == Constant.DrawType.ERASER);
        }
    }

    @Override
    public void onShapeChange(Constant.DrawShape shape) {
        if (shape == Constant.DrawShape.PEN){
            drawingView.setBrush(penBrush);
        }else if (shape == Constant.DrawShape.LINE){
            drawingView.setBrush(lineBrush);
        }else if (shape == Constant.DrawShape.RECT){
            drawingView.setBrush(rectangleBrush);
        }else if (shape == Constant.DrawShape.CIRCLE){
            drawingView.setBrush(circleBrush);
        }else if (shape == Constant.DrawShape.TEXT){
            drawingView.setBrush(textBrush);
        }
    }

    @Override
    public void onChangeSize(int size) {
        penBrush.setSize(size);
        textBrush.setSize(size);
        for (DrawingBrush brush : shapeBrushes) {
            brush.setSize(size);
        }
    }

    final Runnable saveDrawingView = new Runnable() {
        @Override
        public void run() {
            String fileName = FileUtil.viewSaveToImage(drawingView);
            Toast.makeText(FullscreenActivity.this,fileName==null?"保存失败":fileName,Toast.LENGTH_SHORT).show();
        }
    };
}
