package com.example.mainactivity.obj;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class san_pham implements Parcelable {
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

    protected san_pham(Parcel in) {
        gia = in.readInt();
        so_luong = in.readInt();
        name_anh = in.readString();
        ten_sp = in.readString();
        kich_thuoc = in.readString();
        mo_ta = in.readString();
    }

    public static final Creator<san_pham> CREATOR = new Creator<san_pham>() {
        @Override
        public san_pham createFromParcel(Parcel in) {
            return new san_pham(in);
        }

        @Override
        public san_pham[] newArray(int size) {
            return new san_pham[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(gia);
        dest.writeInt(so_luong);
        dest.writeString(name_anh);
        dest.writeString(ten_sp);
        dest.writeString(kich_thuoc);
        dest.writeString(mo_ta);
    }
}
