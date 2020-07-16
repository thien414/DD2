package com.example.myapplication.ClassList;

public class PhongBan {
    private String maPB;
    private String tenPB;

    public String getMaPB() {
        return maPB;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    @Override
    public String toString() {
        return "PhongBan{" +
                "maPB='" + maPB + '\'' +
                ", tenPB='" + tenPB + '\'' +
                '}';
    }
}
