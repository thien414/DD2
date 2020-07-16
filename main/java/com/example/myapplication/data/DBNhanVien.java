package com.example.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.ClassList.NhanVien;

import java.util.ArrayList;

public class DBNhanVien {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public DBNhanVien(Context context){dbHelper = new DBHelper(context);}

    public int Them(NhanVien nv){
        db = dbHelper.getWritableDatabase();
        String sql = "select * from NV where MaNV = '" + nv.getMaNV()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put("MaNV",nv.getMaNV());
            values.put("TenNV",nv.getHoTenNV());
            values.put("NgaySinh",nv.getNgaySinh());
            values.put("MaPB",nv.getMaPB());
            db.insert("NV",null,values);
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

    public  int Sua(NhanVien nv)
    {
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from NV where MaNV = '" + nv.getMaNV()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("MaNV",nv.getMaNV());
            values.put("TenNV",nv.getHoTenNV());
            values.put("NgaySinh",nv.getNgaySinh());
            values.put("MaPB",nv.getMaPB());
            db.update("NV",values,"MaNV ='"+nv.getMaNV() +"'",null);
        }
        return cursor.getCount();
    }

//    public int Xoa(String val){
//        db = dbHelper.getWritableDatabase();
//        int resuilt = db.delete("VPP", "MaVPP =?",new String[]{val});
//        return resuilt;
//    }

    public  int Xoa(NhanVien nv)
    {
        db = dbHelper.getWritableDatabase();
        String sql = "select * from NV where MaNV = '" + nv.getMaNV()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("MaNV",nv.getMaNV());
            values.put("TenNV",nv.getHoTenNV());
            values.put("NgaySinh",nv.getNgaySinh());
            values.put("MaPB",nv.getMaPB());
            db.delete("NV","MaNV = '"+nv.getMaNV()+"'",null);
        }
        return cursor.getCount();


    }


    public ArrayList<NhanVien> LayDL(){
        ArrayList<NhanVien> data = new ArrayList<>();
        String sql="select * from NV";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 && cursor != null){
            do {
                NhanVien nv = new NhanVien();
                nv.setMaNV(cursor.getString(1));
                nv.setHoTenNV(cursor.getString(2));
                nv.setNgaySinh(cursor.getString(3));
                nv.setMaPB(cursor.getString(4));
                data.add(nv);
            }
            while (cursor.moveToNext());
        }

        return  data;

    }
}
