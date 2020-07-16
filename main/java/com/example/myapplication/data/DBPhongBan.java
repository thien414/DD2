package com.example.myapplication.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.ClassList.PhongBan;
import com.example.myapplication.ClassList.VanPhongPham;

import java.util.ArrayList;

public class DBPhongBan {
    DBHelper dbHelper;
    SQLiteDatabase db;

    public DBPhongBan(Context context){
        dbHelper = new DBHelper(context);
    }

    public int Them(PhongBan pb){
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from PB where MaPB = '" + pb.getMaPB()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() == 0){
            ContentValues values = new ContentValues();
            values.put("MaPB",pb.getMaPB());
            values.put("TenPB",pb.getTenPB());
            db.insert("PB",null,values);
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

    public  int Sua(PhongBan pb)
    {
        db = dbHelper.getWritableDatabase();
        //kiem tra san pham có tồn tại chưa
        String sql = "select * from PB where MaPB = '" + pb.getMaPB()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("MaPB",pb.getMaPB());
            values.put("TenPB",pb.getTenPB());
            db.update("PB",values,"MaPB ='"+pb.getMaPB() +"'",null);
        }
        return cursor.getCount();
    }

//    public int Xoa(String val){
//        db = dbHelper.getWritableDatabase();
//        int resuilt = db.delete("VPP", "MaVPP =?",new String[]{val});
//        return resuilt;
//    }

    public  int Xoa(PhongBan pb)
    {
        db = dbHelper.getWritableDatabase();
        String sql = "select * from PB where MaPB = '" + pb.getMaPB()+"'";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if(cursor.getCount() != 0){
            ContentValues values = new ContentValues();
            values.put("MaPB",pb.getMaPB());
            values.put("TenPB",pb.getTenPB());
            db.delete("PB","MaPB = '"+pb.getMaPB()+"'",null);
        }
        return cursor.getCount();


    }


    public ArrayList<PhongBan> LayDL(){
        ArrayList<PhongBan> data = new ArrayList<>();
        String sql="select * from PB";
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0 && cursor != null){
            do {
                PhongBan pb = new PhongBan();
                pb.setMaPB(cursor.getString(1));
                pb.setTenPB(cursor.getString(2));
                data.add(pb);
            }
            while (cursor.moveToNext());
        }

        return  data;

    }
}
