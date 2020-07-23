package com.example.myapplication.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.myapplication.R;

public class MainShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_show);
        ImageView cat = findViewById(R.id.imgView);
        final AnimationDrawable runningCat = (AnimationDrawable) cat.getDrawable();
        runningCat.start();
        final long SPLASH_TIME_OUT = 5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainShow.this, Main.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
