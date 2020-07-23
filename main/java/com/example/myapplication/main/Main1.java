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

import com.example.myapplication.ClassList.VanPhongPham;
import com.example.myapplication.Custom.CustomAdapterVPP;
import com.example.myapplication.R;
import com.example.myapplication.data.DBVanPhongPham;
import com.example.myapplication.model.MainThemVPP;

public class Main1 extends AppCompatActivity {

    boolean check = false;
    DBVanPhongPham dbVanPhongPham;
    CustomAdapterVPP adapterVPP;
    Button btnThem, btnIndex, btnTK;
    EditText edtTK;
    ListView lvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        setControl();
        setEvent();
    }

    private void setEvent() {

        LoadData();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainThemVPP.class);
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
                Tk();
                check = true;
            }
        });
//        btnShowGrit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Main1.this, Main5Grit.class);
//                startActivity(intent);
//            }
//        });

        lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (check) {
                    VanPhongPham vpp = dbVanPhongPham.TiemKiem(edtTK.getText().toString()).get(position);
                    String sp = vpp.getMaVPP() + "-" + vpp.getTenVPP() + "-" + vpp.getdVT() + "-" + vpp.getGiaNhap();
                    Intent intent = new Intent(Main1.this, MainThemVPP.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("index", sp);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    VanPhongPham vpp = dbVanPhongPham.LayDL().get(position);
                    String sp = vpp.getMaVPP() + "-" + vpp.getTenVPP() + "-" + vpp.getdVT() + "-" + vpp.getGiaNhap();
                    Intent intent = new Intent(Main1.this, MainThemVPP.class);
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
                if (check) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main1.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dbVanPhongPham.Xoa(dbVanPhongPham.TiemKiem(edtTK.getText().toString()).get(position)) != 0) {
                                Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                                Tk();
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
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Main1.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (dbVanPhongPham.Xoa(dbVanPhongPham.LayDL().get(position)) != 0) {
                                Toast.makeText(getApplication(), "Xóa thành công", Toast.LENGTH_LONG).show();
                                LoadData();
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

    private void Tk() {
        dbVanPhongPham = new DBVanPhongPham(this);
        adapterVPP = new CustomAdapterVPP(this, R.layout.show_vpp, dbVanPhongPham.TiemKiem(edtTK.getText().toString()));
        lvShow.setAdapter(adapterVPP);
    }


    private void LoadData() {
        dbVanPhongPham = new DBVanPhongPham(this);
        adapterVPP = new CustomAdapterVPP(this, R.layout.show_vpp, dbVanPhongPham.LayDL());
        lvShow.setAdapter(adapterVPP);
    }

    private void setControl() {
        btnThem = findViewById(R.id.btnThem_vpp);
        btnIndex = findViewById(R.id.btnDSVPP_GoIndex);
        lvShow = findViewById(R.id.lvVPP);
        btnTK = findViewById(R.id.btnTKVPP);
        edtTK = findViewById(R.id.edtTKVPP);
    }
}
