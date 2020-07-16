package com.example.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.ClassList.VanPhongPham;

import java.util.ArrayList;

public class DBVanPhongPham {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public DBVanPhongPham(Context context){
        dbHelper = new DBHelper(context);
    }

    public int Them(VanPhongPham vpp){
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from VPP where MaVPP = '" + vpp.getMaVPP()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put("MaVPP",vpp.getMaVPP());
            values.put("TenVPP",vpp.getTenVPP());
            values.put("DVT",vpp.getdVT());
            values.put("GiaNhap",vpp.getGiaNhap());
            db.insert("VPP",null,values);
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

    public  int Sua(VanPhongPham vpp)
    {
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from VPP where MaVPP = '" + vpp.getMaVPP()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("MaVPP",vpp.getMaVPP());
            values.put("TenVPP",vpp.getTenVPP());
            values.put("DVT",vpp.getdVT());
            values.put("GiaNhap",vpp.getGiaNhap());
            db.update("VPP",values,"MaVPP ='"+vpp.getMaVPP() +"'",null);
        }
        return cursor.getCount();
    }

//    public int Xoa(String val){
//        db = dbHelper.getWritableDatabase();
//        int resuilt = db.delete("VPP", "MaVPP =?",new String[]{val});
//        return resuilt;
//    }

    public  int Xoa(VanPhongPham vpp)
    {
        db = dbHelper.getWritableDatabase();
        String sql = "select * from VPP where MaVPP = '" + vpp.getMaVPP()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("MaVPP",vpp.getMaVPP());
            values.put("TenVPP",vpp.getTenVPP());
            values.put("DVT",vpp.getdVT());
            values.put("GiaNhap",vpp.getGiaNhap());
            db.delete("VPP","MaVPP = '"+vpp.getMaVPP()+"'",null);
        }
        return cursor.getCount();


    }


    public ArrayList<VanPhongPham> LayDL(){
        ArrayList<VanPhongPham> data = new ArrayList<>();
        String sql="select * from VPP";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 && cursor != null){
            do {
                VanPhongPham vpp = new VanPhongPham();
                vpp.setMaVPP(cursor.getString(1));
                vpp.setTenVPP(cursor.getString(2));
                vpp.setdVT(cursor.getString(3));
                vpp.setGiaNhap(cursor.getString(4));
                data.add(vpp);
            }
            while (cursor.moveToNext());
        }

        return  data;

    }
}
