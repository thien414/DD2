package com.example.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.myapplication.ClassList.CapNhat;

import java.util.ArrayList;

public class DBCapNhat {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public DBCapNhat(Context context){dbHelper = new DBHelper(context);}

    public int Them(CapNhat cn){
        db = dbHelper.getWritableDatabase();
        String sql = "select * from CNVPP where SoPhieu = '" + cn.getSoPhieu()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put("SoPhieu",cn.getSoPhieu());
            values.put("NgayCap",cn.getNgayCap());
            values.put("MaVPP",cn.getMaVPP());
            values.put("MaNV",cn.getMaNV());
            values.put("SoLuong",cn.getSoLuong());
            db.insert("CNVPP",null,values);
        }
        return cursor.getCount();
    }


//    public void Them(VanPhongPham vpp)
//    {
//        db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("MaVPP",vpp.getMaVPP());
//        values.put("TenVPP",vpp.getTenVPP());
//        values.put("DVT",vpp.getdVT());
//        values.put("GiaNhap",vpp.getGiaNhap());
//        db.insert("VPP",null,values);
//    }

    public  int Sua(CapNhat cn)
    {
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from CNVPP where SoPhieu = '" + cn.getSoPhieu()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("SoPhieu",cn.getSoPhieu());
            values.put("NgayCap",cn.getNgayCap());
            values.put("MaVPP",cn.getMaVPP());
            values.put("MaNV",cn.getMaNV());
            values.put("SoLuong",cn.getSoLuong());
            db.update("CNVPP",values,"SoPhieu ='"+cn.getSoPhieu() +"'",null);
        }
        return cursor.getCount();
    }

//    public int Xoa(String val){
//        db = dbHelper.getWritableDatabase();
//        int resuilt = db.delete("VPP", "MaVPP =?",new String[]{val});
//        return resuilt;
//    }

    public  int Xoa(CapNhat cn)
    {
        db = dbHelper.getWritableDatabase();
        String sql = "select * from CNVPP where SoPhieu = '" + cn.getSoPhieu()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("SoPhieu",cn.getSoPhieu());
            values.put("NgayCap",cn.getNgayCap());
            values.put("MaVPP",cn.getMaVPP());
            values.put("MaNV",cn.getMaNV());
            values.put("SoLuong",cn.getSoLuong());
            db.delete("CNVPP","SoPhieu = '"+cn.getSoPhieu()+"'",null);
        }
        return cursor.getCount();


    }


    public ArrayList<CapNhat> LayDL(){
        ArrayList<CapNhat> data = new ArrayList<>();
        String sql="select * from NV";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 && cursor != null){
            do {
                CapNhat cn = new CapNhat();
                cn.setSoPhieu(cursor.getString(1));
                cn.setNgayCap(cursor.getString(2));
                cn.setMaVPP(cursor.getString(3));
                cn.setMaNV(cursor.getString(4));
                cn.setSoLuong(cursor.getString(5));
                data.add(cn);
            }
            while (cursor.moveToNext());
        }

        return  data;

    }
}
