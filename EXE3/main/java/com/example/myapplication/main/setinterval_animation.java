package com.example.myapplication.main;

import android.view.animation.Interpolator;

public class setinterval_animation implements Interpolator {
    private double myAmptodu = 1;
    private double myFrequecy = 10;
    setinterval_animation(double amptodu, double frequecy){
        myAmptodu = amptodu;
        myFrequecy = frequecy;
    }
    @Override
    public float getInterpolation(float time) {
        return (float)(-1*Math.pow(Math.E, -time/myAmptodu)*Math.cos(myFrequecy*time)+1);
    }
}
