package com.example.myapplication.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.ClassList.PhongBan;
import com.example.myapplication.ClassList.VanPhongPham;
import com.example.myapplication.R;
import com.example.myapplication.data.DBPhongBan;
import com.example.myapplication.data.DBVanPhongPham;
import com.example.myapplication.main.Main1;
import com.example.myapplication.main.Main2;

public class MainThemPB extends AppCompatActivity {

    EditText edtMa, edtTen;
    Button btnThem, btnXoa, btnSua, btnClear;
    DBPhongBan dbPhongBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_them_pb);
        setControl();
        setEvent();
    }

    private void setEvent() {
        dbPhongBan = new DBPhongBan(getApplicationContext());
        if (getIntent().getExtras() != null) {
            setPB();
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
                if(getPB().getMaPB().equals("") || getPB().getTenPB().equals("")){
                    Toast.makeText(getApplication(), "Co truong rong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dbPhongBan.Them(getPB()) == 0) {
                    Toast.makeText(getApplication(), "Thêm sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemPB.this, Main2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Sản phẩm đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dbPhongBan.Xoa(getPB()) != 0) {
                    Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainThemPB.this, Main2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "Xóa không thành công!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                if (dbPhongBan.Sua(getPB()) != 0) {
                    Toast.makeText(getApplication(), "Sửa sảm phẩm thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainThemPB.this, Main2.class);
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
            }
        });

    }

    private void setPB() {
        Bundle bundle = getIntent().getExtras();
        String val = bundle.getString("index");
        String[] vpp = val.split("-");

        edtMa.setText(vpp[0]);
        edtTen.setText(vpp[1]);
    }

    private PhongBan getPB() {
        PhongBan pb = new PhongBan();
        pb.setMaPB(edtMa.getText().toString());
        pb.setTenPB(edtTen.getText().toString());
        return pb;
    }

    private void setControl() {
        edtMa = findViewById(R.id.edtMaPB);
        edtTen = findViewById(R.id.edtTenPB);
        btnThem = findViewById(R.id.btnThemPB);
        btnClear = findViewById(R.id.btnClearPB);
        btnXoa = findViewById(R.id.btnXoaPB);
        btnSua = findViewById(R.id.btnSuaPB);
    }
}
