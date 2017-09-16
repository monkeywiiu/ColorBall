package com.example.pointanimation;

import android.animation.TypeEvaluator;

/**
 * Created by Administrator on 2017/9/15 0015.
 */

public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float v, Object o, Object t1) {
        Point startPoint = (Point) o;
        Point endPoint = (Point) t1;
        float x = v * (endPoint.getX() - startPoint.getX()) + startPoint.getX();
        float y = v * (endPoint.getY() - startPoint.getY()) + startPoint.getY();
        Point vpoint = new Point(x, y);
        return vpoint;
    }
}
