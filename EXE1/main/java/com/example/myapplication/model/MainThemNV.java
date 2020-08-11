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

import com.example.myapplication.ClassList.NhanVien;
import com.example.myapplication.ClassList.PhongBan;
import com.example.myapplication.ClassList.VanPhongPham;
import com.example.myapplication.R;
import com.example.myapplication.data.DBNhanVien;
import com.example.myapplication.data.DBPhongBan;
import com.example.myapplication.data.DBVanPhongPham;
import com.example.myapplication.main.Main1;
import com.example.myapplication.main.Main3;

import java.util.ArrayList;

public class MainThemNV extends AppCompatActivity {

    EditText edtMa, edtTen, edtNgaySinh;
    Spinner spMaPB;
    Button btnThem, btnXoa, btnSua, btnClear;
    DBNhanVien dbNhanVien;
    DBPhongBan dbPhongBan;
    ArrayList<String> data_pb = new ArrayList<>();
    ArrayAdapter adapter_pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_nv);
        setControl();
        setEvent();
    }

    private void setEvent() {
        dbNhanVien = new DBNhanVien(getApplicationContext());
        KhoiTaoSpinner();
        if (getIntent().getExtras() != null) {
            setNV();
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
                if(getNV().getMaNV().equals("") || getNV().getHoTenNV().equals("") || getNV().getNgaySinh().equals("") || getNV().getMaPB().equals("")){
                    Toast.makeText(getApplication(), "Co truong rong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbNhanVien.Them(getNV()) == 0) {
                    Toast.makeText(getApplication(), "Thêm sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemNV.this, Main3.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbNhanVien.Xoa(getNV()) != 0) {
                    Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainThemNV.this, Main3.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Xóa không thành công!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (dbNhanVien.Sua(getNV()) != 0) {
                    Toast.makeText(getApplication(), "Sửa sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemNV.this, Main3.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm không tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMa.setText("");
                edtTen.setText("");
                edtNgaySinh.setText("");
                spMaPB.setSelection(0);
            }
        });

    }

    private void setNV() {
        Bundle bundle = getIntent().getExtras();
        String val = bundle.getString("index");
        String[] vpp = val.split("-");

        edtMa.setText(vpp[0]);
        edtTen.setText(vpp[1]);
        edtNgaySinh.setText(vpp[2]);
        spMaPB.setSelection(data_pb.indexOf(vpp[3]));
    }

    private void KhoiTaoSpinner(){
        dbPhongBan = new DBPhongBan(getApplicationContext());
        for (PhongBan pb: dbPhongBan.LayDL()) {
            data_pb.add(pb.getMaPB());
        }
        adapter_pb = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data_pb);
        spMaPB.setAdapter(adapter_pb);
    }

    private NhanVien getNV() {
        NhanVien nv = new NhanVien();
        nv.setMaNV(edtMa.getText().toString());
        nv.setHoTenNV(edtTen.getText().toString());
        nv.setNgaySinh(edtNgaySinh.getText().toString());
        nv.setMaPB(spMaPB.getSelectedItem().toString());
        return nv;
    }

    private void setControl() {
        edtMa = findViewById(R.id.edtMaNV);
        edtTen = findViewById(R.id.edttenNV);
        edtNgaySinh = findViewById(R.id.edtNgSinhNV);
        spMaPB = findViewById(R.id.spMaPB_NV);
        btnThem = findViewById(R.id.btnThemNV);
        btnClear = findViewById(R.id.btnClearNV);
        btnXoa = findViewById(R.id.btnXoaNV);
        btnSua = findViewById(R.id.btnSuaNV);
    }
}
