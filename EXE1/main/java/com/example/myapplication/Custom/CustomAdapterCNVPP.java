package com.example.myapplication.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.ClassList.CapNhat;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CustomAdapterCNVPP extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<CapNhat> data;


    public CustomAdapterCNVPP(Context context, int resource, ArrayList<CapNhat> data) {
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

        ImageView imgCN = view.findViewById(R.id.imgCnVPP);
        TextView tvSoPhieu = view.findViewById(R.id.tvSoPhieuVPP);
        TextView tvNgayCap = view.findViewById(R.id.tvNgCVPP);
        TextView tvMaVPP = view.findViewById(R.id.tvMaVPP_VPP);
        TextView tvMaNV = view.findViewById(R.id.tvMaNV_VPP);
        TextView tvSoLuong = view.findViewById(R.id.tvSoLuongCnVPP);

        CapNhat cnvpp = data.get(position);
        imgCN.setImageResource(R.drawable.ic_vietnam);
        tvSoPhieu.setText(cnvpp.getSoPhieu());
        tvNgayCap.setText(cnvpp.getNgayCap());
        tvMaVPP.setText(cnvpp.getMaVPP());
        tvMaNV.setText(cnvpp.getMaNV());
        tvSoLuong.setText(cnvpp.getSoLuong());

        return view;
    }
}
