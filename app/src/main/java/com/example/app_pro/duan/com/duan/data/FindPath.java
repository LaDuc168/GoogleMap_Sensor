package com.example.app_pro.duan.com.duan.data;

/**
 * Created by LaVanDuc on 1/15/2018.
 */

public class FindPath {
    private String id;
    private String startAddress;
    private double startLat;
    private double startLng;
    private String endAddress;
    private double endLat;
    private double endLng;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLng() {
        return startLng;
    }

    public void setStartLng(double startLng) {
        this.startLng = startLng;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public double getEndLat() {
        return endLat;
    }

    public void setEndLat(double endLat) {
        this.endLat = endLat;
    }

    public double getEndLng() {
        return endLng;
    }

    public void setEndLng(double endLng) {
        this.endLng = endLng;
    }

    public FindPath(String id, String startAddress, double startLat, double startLng, String endAddress, double endLat, double endLng) {

        this.id = id;
        this.startAddress = startAddress;
        this.startLat = startLat;
        this.startLng = startLng;
        this.endAddress = endAddress;
        this.endLat = endLat;
        this.endLng = endLng;
    }
}
