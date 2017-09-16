package com.example.pointanimation;

import android.animation.TypeEvaluator;

/**
 * Created by Administrator on 2017/9/16 0016.
 */

public class ColorEvaluator implements TypeEvaluator {
    int mcurrentRed = -1;
    int mcurrentGreen = -1;
    int mcurrentBlue = -1;
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        String startColor = (String) startValue;
        String endColor = (String) endValue;

        int startRed = Integer.parseInt(startColor.substring(1, 3), 16);
        int endRed = Integer.parseInt(endColor.substring(1, 3), 16);
        int startGreen = Integer.parseInt(startColor.substring(3, 5), 16);
        int endGreen = Integer.parseInt(endColor.substring(3, 5), 16);
        int startBlue = Integer.parseInt(startColor.substring(5, 7), 16);
        int endBlue = Integer.parseInt(endColor.substring(5, 7), 16);
        int diffRed = Math.abs(startRed - endRed);
        int diffGreen = Math.abs(startGreen - endGreen);
        int diffBlue = Math.abs(startBlue - endBlue);
        int diffColor = diffRed + diffGreen + diffBlue;
        if (mcurrentRed == -1) {
            mcurrentRed = startRed;
        }
        if (mcurrentGreen == -1) {
            mcurrentGreen = startRed;
        }
        if (mcurrentBlue == -1) {
            mcurrentBlue = startBlue;
        }
        if (fraction * diffColor < diffRed){
            if (startRed < endRed) {
                mcurrentRed = (int) (startRed + fraction * diffColor);
            } else {
                mcurrentRed = (int) (startRed - fraction * diffColor);
            }
        } else if ((fraction *diffColor) < (diffRed + diffGreen)) {
            if (startGreen < endGreen) {
                mcurrentGreen = (int) (startGreen + fraction * diffColor - diffRed);
            } else {
                mcurrentGreen = (int) (startGreen - (fraction * diffColor - diffRed));
            }
        } else {
            if (startBlue < endBlue) {
                mcurrentBlue = (int) (startBlue + (fraction * diffColor - diffRed - diffGreen));
            } else {
                mcurrentBlue = (int) (startBlue - (fraction * diffColor - diffRed - diffGreen));
            }
        }

        String currentColor = '#' + getHexString(mcurrentRed) +
                getHexString(mcurrentGreen) + getHexString(mcurrentBlue);
        //Log.d("fraction", fraction + "");
        return currentColor;
    }

    public String getHexString(int value){
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = '0' + hexString;
        }
        return hexString;
    }
}
