package com.example.myapplication.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.ClassList.VanPhongPham;
import com.example.myapplication.R;
import com.example.myapplication.data.DBVanPhongPham;
import com.example.myapplication.main.Main1;


public class MainThemVPP extends AppCompatActivity {

    EditText edtMa, edtTen, edtDVT, edtGiaNhap;
    Button btnThem, btnXoa, btnSua, btnClear;
    DBVanPhongPham dbVanPhongPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_vpp);

        setControl();
        setEvent();
    }

    private void setEvent() {
        dbVanPhongPham = new DBVanPhongPham(getApplicationContext());
        if (getIntent().getExtras() != null) {
            setVPP();
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
                if(getvpp().getMaVPP().equals("") || getvpp().getTenVPP().equals("") || getvpp().getdVT().equals("") || getvpp().getGiaNhap().equals("")){
                    Toast.makeText(getApplication(), "Co truong rong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbVanPhongPham.Them(getvpp()) == 0) {
                    Toast.makeText(getApplication(), "Thêm sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemVPP.this, Main1.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbVanPhongPham = new DBVanPhongPham(getApplicationContext());
                if (dbVanPhongPham.Xoa(getvpp()) != 0) {
                    Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainThemVPP.this, Main1.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Xóa không thành công!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (dbVanPhongPham.Sua(getvpp()) != 0) {
                    Toast.makeText(getApplication(), "Sửa sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemVPP.this, Main1.class);
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
                edtGiaNhap.setText("");
                edtDVT.setText("");
            }
        });

    }

    private void setVPP() {
        Bundle bundle = getIntent().getExtras();
        String val = bundle.getString("index");
        String[] vpp = val.split("-");

        edtMa.setText(vpp[0]);
        edtTen.setText(vpp[1]);
        edtDVT.setText(vpp[2]);
        edtGiaNhap.setText(vpp[3]);
    }

    private VanPhongPham getvpp() {
        VanPhongPham vpp = new VanPhongPham();
        vpp.setMaVPP(edtMa.getText().toString());
        vpp.setTenVPP(edtTen.getText().toString());
        vpp.setdVT(edtDVT.getText().toString());
        vpp.setGiaNhap(edtGiaNhap.getText().toString());
        return vpp;
    }

    private void setControl() {
        edtMa = findViewById(R.id.edtMaVPP);
        edtDVT = findViewById(R.id.edtDvtVPP);
        edtGiaNhap = findViewById(R.id.edtGnVPP);
        edtTen = findViewById(R.id.edtTenVPP);
        btnThem = findViewById(R.id.btnThemVPP);
        btnClear = findViewById(R.id.btnClearVPP);
        btnXoa = findViewById(R.id.btnXoaVPP);
        btnSua = findViewById(R.id.btnSuaVPP);
    }
}
