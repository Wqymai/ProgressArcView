package com.mm.draw;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义计步控件2
 */

public class ProgressArcView2 extends View {
    private int progress = 0;
    public ProgressArcView2(Context context) {
        super(context);
    }

    public ProgressArcView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressArcView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 创建 getter 方法
    public float getProgress() {
        return progress;
    }

    // 创建 setter 方法
    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY);
        Path dashPath = new Path();// 使用一个三角形来做 dash
        dashPath.addRect(0,0,16,70, Path.Direction.CW);
        PathEffect pathEffect = new PathDashPathEffect(dashPath, 24, 0,
                PathDashPathEffect.Style.MORPH);
        paint.setPathEffect(pathEffect);

        canvas.drawArc(0,0,600,600,135,270,false,paint);


        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint1.setColor(Color.BLUE);

        Shader shader = new SweepGradient(300, 300, Color.GREEN,
                Color.RED);
        paint1.setShader(shader);


        Path dashPath1 = new Path();// 使用一个三角形来做 dash
        dashPath1.addRect(0,0,16,70, Path.Direction.CW);
        PathEffect pathEffect1 = new PathDashPathEffect(dashPath1, 24, 0,
                PathDashPathEffect.Style.MORPH);
        paint1.setPathEffect(pathEffect1);

        canvas.drawArc(0,0,600,600,135,progress,false,paint1);
    }

    private int previous = 0 ;
    public  void setCount(int current){
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "progress", previous, current);
        animator.setDuration(3000);
        animator.start();
        previous = current;
    }
}
