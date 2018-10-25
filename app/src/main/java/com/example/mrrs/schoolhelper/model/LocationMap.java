package com.example.mrrs.schoolhelper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationMap {

    @SerializedName("idlocation")
    @Expose
    private String idlocation;
    @SerializedName("latmap")
    @Expose
    private String latmap;
    @SerializedName("longmap")
    @Expose
    private String longmap;
    @SerializedName("stay")
    @Expose
    private String stay;

    public LocationMap(String idlocation, String latmap, String longmap, String stay) {
        this.idlocation = idlocation;
        this.latmap = latmap;
        this.longmap = longmap;
        this.stay = stay;
    }

    public String getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(String idlocation) {
        this.idlocation = idlocation;
    }

    public String getLatmap() {
        return latmap;
    }

    public void setLatmap(String latmap) {
        this.latmap = latmap;
    }

    public String getLongmap() {
        return longmap;
    }

    public void setLongmap(String longmap) {
        this.longmap = longmap;
    }

    public String getStay() {
        return stay;
    }

    public void setStay(String stay) {
        this.stay = stay;
    }

}