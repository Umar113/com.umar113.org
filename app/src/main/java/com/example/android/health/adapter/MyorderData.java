package com.example.android.health.adapter;

/**
 * Created by Android on 25-01-2017.
 */
public class MyorderData {

    private String name;
    private String hospital;
    private String address;
    private String exp;
    private String contact;
    private String faddress;

    public MyorderData(String name, String hospital, String address, String exp, String contact, String faddress) {
        this.name = name;
        this.hospital = hospital;
        this.address = address;
        this.exp = exp;
        this.contact = contact;
        this.faddress = faddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFaddress() {
        return faddress;
    }

    public void setFaddress(String faddress) {
        this.faddress = faddress;
    }
}
