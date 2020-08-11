package com.example.myapplication.ClassList;

public class NhanVien {
    private String maNV;
    private String hoTenNV;
    private String ngaySinh;
    private String maPB;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMaPB() {
        return maPB;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV='" + maNV + '\'' +
                ", hoTenNV='" + hoTenNV + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", maPB='" + maPB + '\'' +
                '}';
    }
}
