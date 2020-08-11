package com.example.myapplication.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.ClassList.CapNhat;
import com.example.myapplication.ClassList.NhanVien;
import com.example.myapplication.ClassList.VanPhongPham;
import com.example.myapplication.R;
import com.example.myapplication.data.DBCapNhat;
import com.example.myapplication.data.DBNhanVien;
import com.example.myapplication.data.DBVanPhongPham;
import com.example.myapplication.main.Main4;

import java.util.ArrayList;

public class MainCapnhatVPP extends AppCompatActivity {

    EditText edtSoPhieu, edtNgayCap, edtSoLuong;
    Spinner spNV, spVPP;
    Button btnThem, btnXoa, btnSua, btnClear;
    DBCapNhat dbCapNhat;
    DBVanPhongPham dbVanPhongPham;
    DBNhanVien dbNhanVien;
    ArrayAdapter adapter_VPP, adapter_NV;
    ArrayList<String> data_VPP = new ArrayList<>(), data_NV = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_capnhat_vpp);
        setControl();
        setEvent();
    }

    private void setEvent() {
        dbCapNhat = new DBCapNhat(getApplicationContext());
        KhoiTaoSpinner();
        if (getIntent().getExtras() != null) {
            setCN();
            btnThem.setEnabled(false);
            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
        }
        else {
            btnThem.setEnabled(true);
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
        }
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCN().getMaVPP().equals("") || getCN().getSoPhieu().equals("") || getCN().getNgayCap().equals("") || getCN().getSoLuong().equals("") || getCN().getMaNV().equals("")){
                    Toast.makeText(getApplication(), "Co truong rong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbCapNhat.Them(getCN()) == 0) {
                    Toast.makeText(getApplication(), "Thêm sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainCapnhatVPP.this, Main4.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbCapNhat.Xoa(getCN()) != 0) {
                    Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainCapnhatVPP.this, Main4.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Xóa không thành công!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (dbCapNhat.Sua(getCN()) != 0) {
                    Toast.makeText(getApplication(), "Sửa sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainCapnhatVPP.this, Main4.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm không tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtSoPhieu.setText("");
                edtNgayCap.setText("");
                edtSoLuong.setText("");
                spNV.setSelection(0);
                spVPP.setSelection(0);
            }
        });

    }

    private void setCN() {
        Bundle bundle = getIntent().getExtras();
        String val = bundle.getString("index");
        String[] vpp = val.split("-");

        edtSoPhieu.setText(vpp[0]);
        edtNgayCap.setText(vpp[1]);
        spVPP.setSelection(data_VPP.indexOf(vpp[2]));
        spNV.setSelection(data_NV.indexOf(vpp[3]));
        edtSoLuong.setText(vpp[4]);
    }

    private  void KhoiTaoSpinner(){
        dbNhanVien = new DBNhanVien(getApplicationContext());
        dbVanPhongPham = new DBVanPhongPham(getApplicationContext());

        for (NhanVien nv: dbNhanVien.LayDL()) {
            data_NV.add(nv.getMaNV());
        }
        for (VanPhongPham vpp: dbVanPhongPham.LayDL()) {
            data_VPP.add(vpp.getMaVPP());
        }

        adapter_NV = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data_NV);
        spNV.setAdapter(adapter_NV);
        adapter_VPP = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data_VPP);
        spVPP.setAdapter(adapter_VPP);
    }

    private CapNhat getCN() {
        CapNhat cnVPP = new CapNhat();
        cnVPP.setSoPhieu(edtSoPhieu.getText().toString());
        cnVPP.setNgayCap(edtNgayCap.getText().toString());
        cnVPP.setSoLuong(edtSoLuong.getText().toString());
        cnVPP.setMaVPP(spVPP.getSelectedItem().toString());
        cnVPP.setMaNV(spNV.getSelectedItem().toString());
        return cnVPP;
    }

    private void setControl() {
        edtSoPhieu = findViewById(R.id.edtsophieuVPP);
        edtNgayCap = findViewById(R.id.edtNgCapVPP);
        edtSoLuong = findViewById(R.id.edtSlVPP);
        spNV = findViewById(R.id.spManv_VPP);
        spVPP = findViewById(R.id.spMaVPP);
        btnThem = findViewById(R.id.btnThemCnVPP);
        btnClear = findViewById(R.id.btnClearCnVPP);
        btnXoa = findViewById(R.id.btnXoaCnVPP);
        btnSua = findViewById(R.id.btnSuaCnVPP);
    }
}
