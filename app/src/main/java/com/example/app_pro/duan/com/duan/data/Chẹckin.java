package com.example.app_pro.duan.com.duan.data;

/**
 * Created by LaVanDuc on 1/15/2018.
 */

public class Chẹckin {
    private String ma;
    private String address;
    private double lat;
    private double lng;

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Chẹckin(String ma, String address, double lat, double lng) {

        this.ma = ma;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }
}
