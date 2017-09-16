package com.example.pointanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class MyPiontView extends View {
    private float radius = 100f;
    private Point currentPoint;
    private Paint mpaint;
    private String color;
    public MyPiontView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mpaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint != null) {
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            if (color != null) {
                try {
                    mpaint.setColor(Color.parseColor(color));
                } catch (Exception e) {
                    Log.d("mcolor", color);
                }

            }
            canvas.drawCircle(x , y, radius, mpaint);

        } else {
            currentPoint = new Point(radius, radius);
            translateAnim();
        }
    }

    public void translateAnim() {
        Point startPoint = new Point(radius, radius);
        Point endPoint = new Point(getWidth() - radius, getHeight() - radius);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                currentPoint = (Point)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        ObjectAnimator coloranim = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(), "#000000", "#FFFFFF");
        coloranim.setRepeatCount(ValueAnimator.INFINITE);
        coloranim.setRepeatMode(ValueAnimator.REVERSE);
        coloranim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                color = (String) valueAnimator.getAnimatedValue();

            }
        });
        AnimatorSet set = new AnimatorSet();
        set.play(anim).with(coloranim);
        set.setDuration(5000);
        set.start();
    }
}
