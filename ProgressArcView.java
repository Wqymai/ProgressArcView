package com.mm.draw;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义计步控件1
 */

public class ProgressArcView extends View {
    int progress = 0;
    RectF arcRectF = new RectF();
    static String TEXT = "步数";
    public ProgressArcView(Context context) {
        super(context);
    }

    public ProgressArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int border = dipToPx(30);
        int right = width - border;
        int bottom = height - border;

        arcRectF.set(border,border,right,bottom);

        Paint paintBg = new Paint();
        paintBg.setColor(getResources().getColor(R.color.test_primary));
        paintBg.setAntiAlias(true);
        paintBg.setStrokeWidth(border);
        paintBg.setStrokeCap(Paint.Cap.ROUND);
        paintBg.setStyle(Paint.Style.STROKE);
        canvas.drawArc(arcRectF,135,270,false,paintBg);

        Paint paintCir = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCir.setColor(getResources().getColor(R.color.colorCold));
        paintCir.setAntiAlias(true);
        paintCir.setStrokeWidth(dipToPx(20));
        paintCir.setStrokeCap(Paint.Cap.ROUND);
        paintCir.setStyle(Paint.Style.STROKE);
        paintCir.setFakeBoldText(true);
        canvas.drawArc( arcRectF,135,progress,false,paintCir);

        Paint paintPro = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintPro.setTextSize(dipToPx(20));
        paintPro.setColor(getResources().getColor(R.color.colorCold));
        canvas.drawText(progress+"",width/2 - paintPro.measureText(String.valueOf(progress))/2,height/2,paintPro);

        Paint paintTxt = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintTxt.setTextSize(dipToPx(10));
        canvas.drawText(TEXT,width/2 - paintTxt.measureText(TEXT)/2,height/2+dipToPx(15),paintTxt);

    }
    private int previous = 0 ;
    public  void setCount(int current){
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "progress", previous, current);
        animator.setDuration(3000);
        animator.start();
        previous = current;
    }


}
