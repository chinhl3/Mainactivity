package com.example.mainactivity.obj;

public class san_pham {
    private int gia,so_luong;
    private String name_anh,ten_sp,kich_thuoc,mo_ta;

    public san_pham(int gia, int so_luong, String name_anh, String ten_sp, String kich_thuoc, String mo_ta) {
        this.gia = gia;
        this.so_luong = so_luong;
        this.name_anh = name_anh;
        this.ten_sp = ten_sp;
        this.kich_thuoc = kich_thuoc;
        this.mo_ta = mo_ta;
    }

    public san_pham() {
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public String getName_anh() {
        return name_anh;
    }

    public void setName_anh(String name_anh) {
        this.name_anh = name_anh;
    }

    public String getTen_sp() {
        return ten_sp;
    }

    public void setTen_sp(String ten_sp) {
        this.ten_sp = ten_sp;
    }

    public String getKich_thuoc() {
        return kich_thuoc;
    }

    public void setKich_thuoc(String kich_thuoc) {
        this.kich_thuoc = kich_thuoc;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }
}
