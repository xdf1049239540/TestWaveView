package com.example.xdf.testwaveview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by xdf on 2017/7/24.
 */

public class WaveView extends View {
    int wave_width=1000;
    Path path;
    Paint paint=null;
    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        path=new Path();
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);
        startAnim();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        path.moveTo(-wave_width+dx,800-dy);
        int half_wave=wave_width/2;
        for (int i = -wave_width; i <getWidth()+wave_width ; i+=wave_width) {
            path.rQuadTo(half_wave/2,-200,half_wave,0);
            path.rQuadTo(half_wave/2,200,half_wave,0);
        }
        //path.lineTo(getWidth(),getHeight());
        //path.lineTo(0,getHeight());
        path.lineTo(getWidth(),0);
        path.lineTo(0,0);
        path.close();
        canvas.drawPath(path,paint);
    }
    int dx;
    int dy;
    private void startAnim(){
        ValueAnimator an=ValueAnimator.ofInt(0,wave_width);
        an.setInterpolator(new LinearInterpolator());
        an.setRepeatCount(ValueAnimator.INFINITE);
        an.setDuration(2000);
        an.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                dx= (int) valueAnimator.getAnimatedValue();
                //dy=(int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        an.start();
    }

}
