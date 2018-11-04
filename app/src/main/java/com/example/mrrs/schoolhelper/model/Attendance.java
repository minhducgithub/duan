package com.example.mrrs.schoolhelper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attendance {

    @SerializedName("idLichhoc")
    @Expose
    private String idLichhoc;
    @SerializedName("ngay")
    @Expose
    private String ngay;
    @SerializedName("phong")
    @Expose
    private String phong;
    @SerializedName("giangduong")
    @Expose
    private String giangduong;
    @SerializedName("mamon")
    @Expose
    private String mamon;
    @SerializedName("mon")
    @Expose
    private String mon;
    @SerializedName("lop")
    @Expose
    private String lop;
    @SerializedName("giangvien")
    @Expose
    private String giangvien;
    @SerializedName("thoigian")
    @Expose
    private String thoigian;
    @SerializedName("chitiet")
    @Expose
    private String chitiet;

    public Attendance(String giangvien) {
        this.giangvien = giangvien;
    }

    public String getIdLichhoc() {
        return idLichhoc;
    }

    public void setIdLichhoc(String idLichhoc) {
        this.idLichhoc = idLichhoc;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getGiangduong() {
        return giangduong;
    }

    public void setGiangduong(String giangduong) {
        this.giangduong = giangduong;
    }

    public String getMamon() {
        return mamon;
    }

    public void setMamon(String mamon) {
        this.mamon = mamon;
    }

    public String getMon() {
        return mon;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getGiangvien() {
        return giangvien;
    }

    public void setGiangvien(String giangvien) {
        this.giangvien = giangvien;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }

}