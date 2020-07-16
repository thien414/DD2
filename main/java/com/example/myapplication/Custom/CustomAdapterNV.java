package com.example.myapplication.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.ClassList.NhanVien;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CustomAdapterNV extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NhanVien> data;


    public CustomAdapterNV(Context context, int resource, ArrayList<NhanVien> data) {
        super(context, resource);
        this.context = context;
        this.data = data;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource,null);

        ImageView imgNV = view.findViewById(R.id.imgNV);
        TextView tvMaNV = view.findViewById(R.id.tvMaNV);
        TextView tvTenNV = view.findViewById(R.id.tvTenNV);
        TextView tvNgaySinh = view.findViewById(R.id.tvNgSinhNV);
        TextView tvMaPB = view.findViewById(R.id.tvMaPB_NV);

        NhanVien nv = data.get(position);
        imgNV.setImageResource(R.drawable.ic_vietnam);
        tvMaNV.setText(nv.getMaNV());
        tvTenNV.setText(nv.getHoTenNV());
        tvNgaySinh.setText(nv.getNgaySinh());
        tvMaPB.setText(nv.getMaPB());

        return view;
    }
}
