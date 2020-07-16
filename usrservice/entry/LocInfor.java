package com.example.usrservice.entry;

import java.io.Serializable;

public class LocInfor implements Serializable {
    public String getReceiver() {
        return receiver;
    }

    public String getPhone() {
        return phone;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }

    public String getTown() {
        return town;
    }

    public String getLoc() {
        return loc;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    String receiver;
    String phone;
    String province;
    String city;
    String area;
    String town;
    String loc;

    public LocInfor(String receiver, String phone, String province, String city, String area, String town, String loc) {
        this.receiver = receiver;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.area = area;
        this.town = town;
        this.loc = loc;
    }
}
