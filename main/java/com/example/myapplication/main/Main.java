package com.example.myapplication.main;

import androidx.annotation.AnimRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapplication.R;

public class Main extends AppCompatActivity {
    boolean lang = true;


    Button btnVPP, btnPB, btnNV, btnCNVPP;
    ImageView imIcon;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnLang:
                if(lang)
                {
                    item.setIcon(R.drawable.ic_usa_today);
                }
                else{
                    item.setIcon(R.drawable.ic_vietnam);
                }
                lang = !lang;
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
