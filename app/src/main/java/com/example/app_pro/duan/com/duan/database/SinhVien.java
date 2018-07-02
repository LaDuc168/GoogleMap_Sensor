package com.example.app_pro.duan.com.duan.database;

import java.io.Serializable;

/**
 * Created by APP-PRO on 12/3/2017.
 */

public class SinhVien implements Serializable{
    private int id;
    private String name;
    private String firstname;
    private String lastname;
    private String gender;
    private String pass;
    private int status;

    public SinhVien() {
    }

    public SinhVien(int id, String name, String firstname, String lastname, String gender, String pass, int status) {

        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.pass = pass;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
