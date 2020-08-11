package com.example.myapplication.main;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MainShow extends AppCompatActivity {
    //ProgressBar
    ProgressBar progressBar;
    TextView percent;
    //Hooks
    View first, second, third, fourth, fifth, sixth;
    ImageView imgView;
    //animation
    Animation first_anima, second_anima, third_anima, fourth_anima, fifth_anima, sixth_anima, img_animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_show);

        first_anima = AnimationUtils.loadAnimation(this, R.anim.first_animation);
        second_anima = AnimationUtils.loadAnimation(this, R.anim.second_anima);
        third_anima = AnimationUtils.loadAnimation(this, R.anim.third_anima);
        fourth_anima = AnimationUtils.loadAnimation(this, R.anim.fouth_anima);
        fifth_anima = AnimationUtils.loadAnimation(this, R.anim.fifth_anima);
        sixth_anima = AnimationUtils.loadAnimation(this, R.anim.sixth_anima);
        img_animation = AnimationUtils.loadAnimation(this, R.anim.img_animation);
        setControl();
        setAnima();
        //img
        AnimationDrawable runningcat = (AnimationDrawable)imgView.getDrawable();
        runningcat.start();

        setProgressAnimation();
    }

    private void setProgressAnimation() {
        progressbaranimation progress = new progressbaranimation(this, progressBar, percent, 0f, 100f);
        progress.setDuration(3100);
        progressBar.setAnimation(progress);
    }

    private void setAnima() {
        first.setAnimation(first_anima);
        second.setAnimation(second_anima);
        third.setAnimation(third_anima);
        fourth.setAnimation(fourth_anima);
        fifth.setAnimation(fifth_anima);
        sixth.setAnimation(sixth_anima);
        imgView.setAnimation(img_animation);
        percent.setAnimation(img_animation);
    }

    private void setControl() {
        first = findViewById(R.id.first_line);
        second = findViewById(R.id.second_line);
        third = findViewById(R.id.third_line);
        fourth = findViewById(R.id.fourth_line);
        fifth = findViewById(R.id.fifth_line);
        sixth = findViewById(R.id.sixth_line);
        imgView = findViewById(R.id.imgView);

        //progress
        progressBar = findViewById(R.id.progress_id);
        percent = findViewById(R.id.percent);
        progressBar.setMax(100);
        progressBar.setScaleY(3f);
    }

}
