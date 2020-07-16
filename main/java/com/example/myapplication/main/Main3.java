package com.example.myapplication.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.ClassList.NhanVien;
import com.example.myapplication.Custom.CustomAdapterNV;
import com.example.myapplication.R;
import com.example.myapplication.data.DBNhanVien;
import com.example.myapplication.model.MainThemNV;

public class Main3 extends AppCompatActivity {

    DBNhanVien dbNhanVien;
    CustomAdapterNV adapterNV;
    Button btnThem, btnIndex;
    ListView lvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        setControl();
        setEvent();
    }

    private void setEvent() {

        LoadData();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainThemNV.class);
                startActivity(intent);
            }
        });
        btnIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        });

        lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NhanVien nv = dbNhanVien.LayDL().get(position);
                String sp = nv.getMaNV() + "-" + nv.getHoTenNV() + "-" + nv.getNgaySinh() + "-" + nv.getMaPB();
                Intent intent = new Intent(Main3.this, MainThemNV.class);
                Bundle bundle = new Bundle();
                bundle.putString("index", sp);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        lvShow.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Main3.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dbNhanVien.Xoa(dbNhanVien.LayDL().get(position)) != 0) {
                            Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                            LoadData();
                        } else {
                            Toast.makeText(getApplication(), "Xóa không thành công", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return false;
            }
        });
    }

    private void LoadData() {
        dbNhanVien = new DBNhanVien(this);
        adapterNV = new CustomAdapterNV(this, R.layout.show_nv, dbNhanVien.LayDL());
        lvShow.setAdapter(adapterNV);
    }

    private void setControl() {
        btnThem = findViewById(R.id.btnThem_nv);
        btnIndex = findViewById(R.id.btnDSNV_GoIndex);
        lvShow = findViewById(R.id.lvNV);
    }
}
