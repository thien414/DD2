package com.example.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "QLVanPhongPham", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlVPP = "create table VPP(ID INTEGER PRIMARY KEY AUTOINCREMENT, MaVPP text, TenVPP text, DVT text, GiaNhap text)";
        String sqlPB = "create table PB(ID INTEGER PRIMARY KEY AUTOINCREMENT, MaPB text, TenPB text)";
        String sqlNV = "create table NV(ID INTEGER PRIMARY KEY AUTOINCREMENT, MaNV text, TenNV text, NgaySinh text, MaPB text)";
        String sqlCNVPP = "create table CNVPP(ID INTEGER PRIMARY KEY AUTOINCREMENT, SoPhieu text, NgayCap text, MaVPP text, MaNV text, SoLuong text)";
        db.execSQL(sqlVPP);
        db.execSQL(sqlPB);
        db.execSQL(sqlNV);
        db.execSQL(sqlCNVPP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
