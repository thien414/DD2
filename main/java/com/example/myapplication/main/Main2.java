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

import com.example.myapplication.ClassList.PhongBan;
import com.example.myapplication.ClassList.VanPhongPham;
import com.example.myapplication.Custom.CustomAdapterPB;
import com.example.myapplication.Custom.CustomAdapterVPP;
import com.example.myapplication.R;
import com.example.myapplication.data.DBPhongBan;
import com.example.myapplication.data.DBVanPhongPham;
import com.example.myapplication.model.MainThemPB;
import com.example.myapplication.model.MainThemVPP;

public class Main2 extends AppCompatActivity {

    DBPhongBan dbPhongBan;
    CustomAdapterPB adapterPB;
    Button btnThem, btnIndex;
    ListView lvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setControl();
        setEvent();
    }

    private void setEvent() {

        LoadData();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainThemPB.class);
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

                PhongBan pb = dbPhongBan.LayDL().get(position);
                String sp = pb.getMaPB() +"-"+ pb.getTenPB();
                Intent intent = new Intent(Main2.this, MainThemPB.class);
                Bundle bundle = new Bundle();
                bundle.putString("index", sp);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        lvShow.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Main2.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(dbPhongBan.Xoa(dbPhongBan.LayDL().get(position)) != 0) {
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

                return false;
            }
        });
    }

    private void LoadData() {
        dbPhongBan = new DBPhongBan(this);
        adapterPB = new CustomAdapterPB(this, R.layout.show_pb, dbPhongBan.LayDL() );
        lvShow.setAdapter(adapterPB);
    }

    private void setControl() {
        btnThem = findViewById(R.id.btnThem_pb);
        btnIndex = findViewById(R.id.btnDSPB_GoIndex);
        lvShow = findViewById(R.id.lvPB);
    }
}
