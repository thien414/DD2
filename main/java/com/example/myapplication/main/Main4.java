package com.example.myapplication.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.ClassList.CapNhat;
import com.example.myapplication.ClassList.PhongBan;
import com.example.myapplication.Custom.CustomAdapterCNVPP;
import com.example.myapplication.Custom.CustomAdapterPB;
import com.example.myapplication.R;
import com.example.myapplication.data.DBCapNhat;
import com.example.myapplication.data.DBPhongBan;
import com.example.myapplication.model.MainCapnhatVPP;
import com.example.myapplication.model.MainThemPB;

public class Main4 extends AppCompatActivity {

    boolean check = false;
    DBCapNhat dbCapNhat;
    CustomAdapterCNVPP adapterCNVPP;
    Button btnThem, btnIndex, btnTK;
    EditText edtTK;
    ListView lvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        setControl();
        setEvent();
    }

    private void setEvent() {

        LoadData();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainCapnhatVPP.class);
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
        btnTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tk();
                check = true;
            }
        });

        lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(check){
                    CapNhat cnvpp = dbCapNhat.TiemKiem(edtTK.getText().toString()).get(position);
                    String sp = cnvpp.getSoPhieu() +"-"+ cnvpp.getNgayCap() +"-"+ cnvpp.getMaVPP() +"-"+ cnvpp.getMaNV() +"-"+ cnvpp.getSoLuong();
                    Intent intent = new Intent(Main4.this, MainCapnhatVPP.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("index", sp);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    CapNhat cnvpp = dbCapNhat.LayDL().get(position);
                    String sp = cnvpp.getSoPhieu() +"-"+ cnvpp.getNgayCap() +"-"+ cnvpp.getMaVPP() +"-"+ cnvpp.getMaNV() +"-"+ cnvpp.getSoLuong();
                    Intent intent = new Intent(Main4.this, MainCapnhatVPP.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("index", sp);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        lvShow.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                if(check){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main4.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dbCapNhat.Xoa(dbCapNhat.TiemKiem(edtTK.getText().toString()).get(position)) != 0) {
                                Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                                tk();
                            }
                            else{
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
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main4.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(dbCapNhat.Xoa(dbCapNhat.LayDL().get(position)) != 0) {
                                Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                                LoadData();
                            }
                            else{
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
                }

                return false;
            }
        });
    }

    private void tk() {
        dbCapNhat = new DBCapNhat(this);
        adapterCNVPP = new CustomAdapterCNVPP(this, R.layout.show_capnhat_vpp, dbCapNhat.TiemKiem(edtTK.getText().toString()) );
        lvShow.setAdapter(adapterCNVPP);
    }

    private void LoadData() {
        dbCapNhat = new DBCapNhat(this);
        adapterCNVPP = new CustomAdapterCNVPP(this, R.layout.show_capnhat_vpp, dbCapNhat.LayDL() );
        lvShow.setAdapter(adapterCNVPP);
    }

    private void setControl() {
        btnTK = findViewById(R.id.btnTKCNVPP);
        edtTK = findViewById(R.id.edtTKCNVPP);
        btnThem = findViewById(R.id.btnThem_cnvpp);
        btnIndex = findViewById(R.id.btnDSCNVPP_GoIndex);
        lvShow = findViewById(R.id.lvCNVPP);
    }
}
