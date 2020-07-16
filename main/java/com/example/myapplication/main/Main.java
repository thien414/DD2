package com.example.myapplication.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

public class Main extends AppCompatActivity {
    boolean lang = true;


    Button btnVPP, btnPB, btnNV, btnCNVPP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }

    private void setEvent() {
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

    private void setControl() {
        btnCNVPP = findViewById(R.id.btnThemMain1_cnvpp);
        btnNV = findViewById(R.id.btnThemMain1_nv);
        btnPB = findViewById(R.id.btnThemMain1_pb);
        btnVPP = findViewById(R.id.btnThemMain1_vpp);
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
            case R.id.mnExit:
                AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
                builder.setTitle("Thông Báo");
                builder.setMessage("Bạn có muốn thoát???");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
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
