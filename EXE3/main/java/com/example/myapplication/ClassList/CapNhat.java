package com.example.myapplication.ClassList;

public class CapNhat {
    private String soPhieu;
    private String ngayCap;
    private String maVPP;
    private String maNV;
    private String soLuong;

    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public String getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(String ngayCap) {
        this.ngayCap = ngayCap;
    }

    public String getMaVPP() {
        return maVPP;
    }

    public void setMaVPP(String maVPP) {
        this.maVPP = maVPP;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "CapNhat{" +
                "soPhieu='" + soPhieu + '\'' +
                ", ngayCap='" + ngayCap + '\'' +
                ", maVPP='" + maVPP + '\'' +
                ", maNV='" + maNV + '\'' +
                ", soLuong='" + soLuong + '\'' +
                '}';
    }
}
