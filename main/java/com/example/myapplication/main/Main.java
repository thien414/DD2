package com.example.myapplication.main;

import androidx.annotation.AnimRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.util.Locale;

public class Main extends AppCompatActivity {


    Button btnVPP, btnPB, btnNV, btnCNVPP;
    ImageView imIcon;
    Locale mMyLocale;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }

    private void setEvent() {
        setIcon(R.anim.imgstart);

        imIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIcon(R.anim.imgclick);
            }
        });
        btnVPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Main1.class);
                startActivity(intent);
            }
        });
        btnCNVPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Main4.class);
                startActivity(intent);
            }
        });
        btnNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Main3.class);
                startActivity(intent);
            }
        });
        btnPB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Main2.class);
                startActivity(intent);
            }
        });
    }

    private void setIcon(@AnimRes int id) {
        Animation animation = AnimationUtils.loadAnimation(Main.this,id);
        imIcon.startAnimation(animation);
    }

    private void setControl() {
        btnCNVPP = findViewById(R.id.btnThemMain1_cnvpp);
        btnNV = findViewById(R.id.btnThemMain1_nv);
        btnPB = findViewById(R.id.btnThemMain1_pb);
        btnVPP = findViewById(R.id.btnThemMain1_vpp);
        imIcon = findViewById(R.id.imgIcon);
    }
    

    private void onChangLanguage(Locale locale) {
        DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();
        Configuration configuration = new Configuration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        getBaseContext().getResources().updateConfiguration(configuration, displayMetrics);
        Intent intent = new Intent(Main.this, Main.class);
        startActivity(intent);
        finish();

    }
}
