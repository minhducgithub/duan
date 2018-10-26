package com.example.mrrs.schoolhelper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("idsv")
    @Expose
    private String idsv;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("pass")
    @Expose
    private String pass;

    public String getIdsv() {
        return idsv;
    }

    public void setIdsv(String idsv) {
        this.idsv = idsv;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}