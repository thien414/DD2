package com.example.myapplication.Custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.ClassList.PhongBan;
import com.example.myapplication.R;

import java.util.ArrayList;

public class CustomAdapterPB extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<PhongBan> data;


    public CustomAdapterPB(Context context, int resource, ArrayList<PhongBan> data) {
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

        ImageView imgPB = view.findViewById(R.id.imgPB);
        TextView tvMaPB = view.findViewById(R.id.tvMaPB);
        TextView tvTenPB = view.findViewById(R.id.tvTenPB);

        PhongBan vpp = data.get(position);
        imgPB.setImageResource(R.drawable.ic_vietnam);
        tvMaPB.setText(vpp.getMaPB());
        tvTenPB.setText(vpp.getTenPB());

        return view;
    }
}
