package com.example.myapplication.ClassList;

public class ThongKe {
    private int soLuong;
    private String tenVPP;

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenVPP() {
        return tenVPP;
    }

    public void setTenVPP(String tenVPP) {
        this.tenVPP = tenVPP;
    }

    public ThongKe(int soLuong, String tenVPP) {
        this.soLuong = soLuong;
        this.tenVPP = tenVPP;
    }

    @Override
    public String toString() {
        return "ThongKe{" +
                "soLuong=" + soLuong +
                ", tenVPP='" + tenVPP + '\'' +
                '}';
    }
}
