package com.example.myapplication.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.ClassList.VanPhongPham;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CustomAdapterVPP extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<VanPhongPham> data;


    public CustomAdapterVPP(Context context, int resource, ArrayList<VanPhongPham> data) {
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

        ImageView imgVPP = view.findViewById(R.id.imgVPP);
        TextView tvMaVPP = view.findViewById(R.id.tvMaVPP);
        TextView tvTenVPP = view.findViewById(R.id.tvTenVPP);
        TextView tvDVT = view.findViewById(R.id.tvDvtVPP);
        TextView tvGiaNhap = view.findViewById(R.id.tvGnVPP);

        VanPhongPham vpp = data.get(position);
        imgVPP.setImageResource(R.drawable.ic_vietnam);
        tvDVT.setText(vpp.getdVT());
        tvGiaNhap.setText(vpp.getGiaNhap());
        tvMaVPP.setText(vpp.getMaVPP());
        tvTenVPP.setText(vpp.getTenVPP());

        return view;
    }
}
