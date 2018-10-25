package com.example.mrrs.schoolhelper.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {

    @SerializedName("idsinhvien")
    @Expose
    private String idsinhvien;
    @SerializedName("svhinh")
    @Expose
    private String svhinh;
    @SerializedName("svten")
    @Expose
    private String svten;
    @SerializedName("svblock")
    @Expose
    private String svblock;
    @SerializedName("svcode")
    @Expose
    private String svcode;
    @SerializedName("svcourse")
    @Expose
    private String svcourse;
    @SerializedName("svstatus")
    @Expose
    private String svstatus;
    @SerializedName("svphone")
    @Expose
    private String svphone;
    @SerializedName("svemail")
    @Expose
    private String svemail;
    @SerializedName("svaddress")
    @Expose
    private String svaddress;
    @SerializedName("svspecialized")
    @Expose
    private String svspecialized;
    @SerializedName("svstart")
    @Expose
    private String svstart;

//    public Student(String idsinhvien, String svhinh, String svten, String svblock, String svcode,
//                   String svcourse, String svstatus, String svphone, String svemail, String svaddress,
//                   String svspecialized, String svstart) {
//        this.idsinhvien = idsinhvien;
//        this.svhinh = svhinh;
//        this.svten = svten;
//        this.svblock = svblock;
//        this.svcode = svcode;
//        this.svcourse = svcourse;
//        this.svstatus = svstatus;
//        this.svphone = svphone;
//        this.svemail = svemail;
//        this.svaddress = svaddress;
//        this.svspecialized = svspecialized;
//        this.svstart = svstart;
//    }


    public String getIdsinhvien() {
        return idsinhvien;
    }

    public void setIdsinhvien(String idsinhvien) {
        this.idsinhvien = idsinhvien;
    }

    public String getSvhinh() {
        return svhinh;
    }

    public void setSvhinh(String svhinh) {
        this.svhinh = svhinh;
    }

    public String getSvten() {
        return svten;
    }

    public void setSvten(String svten) {
        this.svten = svten;
    }

    public String getSvblock() {
        return svblock;
    }

    public void setSvblock(String svblock) {
        this.svblock = svblock;
    }

    public String getSvcode() {
        return svcode;
    }

    public void setSvcode(String svcode) {
        this.svcode = svcode;
    }

    public String getSvcourse() {
        return svcourse;
    }

    public void setSvcourse(String svcourse) {
        this.svcourse = svcourse;
    }

    public String getSvstatus() {
        return svstatus;
    }

    public void setSvstatus(String svstatus) {
        this.svstatus = svstatus;
    }

    public String getSvphone() {
        return svphone;
    }

    public void setSvphone(String svphone) {
        this.svphone = svphone;
    }

    public String getSvemail() {
        return svemail;
    }

    public void setSvemail(String svemail) {
        this.svemail = svemail;
    }

    public String getSvaddress() {
        return svaddress;
    }

    public void setSvaddress(String svaddress) {
        this.svaddress = svaddress;
    }

    public String getSvspecialized() {
        return svspecialized;
    }

    public void setSvspecialized(String svspecialized) {
        this.svspecialized = svspecialized;
    }

    public String getSvstart() {
        return svstart;
    }

    public void setSvstart(String svstart) {
        this.svstart = svstart;
    }

}