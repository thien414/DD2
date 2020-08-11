package com.example.myapplication.main;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ClassList.CapNhat;
import com.example.myapplication.ClassList.ThongKe;
import com.example.myapplication.ClassList.VanPhongPham;
import com.example.myapplication.R;
import com.example.myapplication.data.DBCapNhat;
import com.example.myapplication.data.DBVanPhongPham;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;

public class PieChartActivity extends AppCompatActivity {
    DBCapNhat dbCapNhat = new DBCapNhat(this);
    DBVanPhongPham dbVanPhongPham = new DBVanPhongPham(this);
    ArrayList<ThongKe> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        PieChart pieChart = findViewById(R.id.pieChart);
        data = new ArrayList<>();
        for(VanPhongPham vp : dbVanPhongPham.LayDL()){
            ThongKe tk = new ThongKe(0,"");
            for(CapNhat cn : dbCapNhat.LayDL()){
                if(vp.getMaVPP().equals(cn.getMaVPP())){
                    tk.setSoLuong(tk.getSoLuong()+Integer.parseInt(cn.getSoLuong()));
                    tk.setTenVPP(vp.getTenVPP());
                }
            }
            if(tk.getSoLuong() != 0){

                data.add(tk);
            }
        }

        ArrayList<PieEntry> visitors = new ArrayList<>();
        for(ThongKe tk : data){
            visitors.add(new PieEntry(tk.getSoLuong(), tk.getTenVPP()));
        }

        PieDataSet pieDataSet = new PieDataSet(visitors,"| VPP");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(10f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Thống Kê");
        pieChart.animate();

    }
}
