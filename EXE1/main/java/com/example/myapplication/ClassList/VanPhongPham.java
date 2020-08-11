package com.example.myapplication.ClassList;

public class VanPhongPham {
    private String maVPP;
    private String tenVPP;
    private String dVT;
    private String giaNhap;

    public String getMaVPP() {
        return maVPP;
    }

    public void setMaVPP(String maVPP) {
        this.maVPP = maVPP;
    }

    public String getTenVPP() {
        return tenVPP;
    }

    public void setTenVPP(String tenVPP) {
        this.tenVPP = tenVPP;
    }

    public String getdVT() {
        return dVT;
    }

    public void setdVT(String dVT) {
        this.dVT = dVT;
    }

    public String getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(String giaNhap) {
        this.giaNhap = giaNhap;
    }

    @Override
    public String toString() {
        return "VanPhongPham{" +
                "maVPP='" + maVPP + '\'' +
                ", tenVPP='" + tenVPP + '\'' +
                ", dVT=" + dVT +
                ", giaNhap=" + giaNhap +
                '}';
    }
}
