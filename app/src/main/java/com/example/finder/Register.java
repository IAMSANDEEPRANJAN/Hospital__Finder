package com.example.finder;

import java.io.Serializable;

public class Register implements Serializable {//create class

    String h_name;
    String reg_no;
    String state;
    String contact;
    String address;
    String area;
    String city;
    String pincode;
    String Speciality;
    int status;
    String manager;



    String hospitalPushKey;

    public Register() {
    }
    //Generate constructor for all the variable
    public Register(String h_name, String reg_no, String state, String contact, String address, String area, String city, String pincode, String speciality, int status, String hospitalPushKey,String manager) {
        this.h_name = h_name;
        this.reg_no = reg_no;
        this.state = state;
        this.contact = contact;
        this.address = address;
        this.area = area;
        this.city = city;
        this.pincode = pincode;
        Speciality = speciality;
        this.status = status;
        this.hospitalPushKey = hospitalPushKey;
        this.manager = manager;
    }
    //Generate getter and shetter for all the variable

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getH_name() {
        return h_name;
    }

    public void setH_name(String h_name) {
        this.h_name = h_name;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHospitalPushKey() {
        return hospitalPushKey;
    }

    public void setHospitalPushKey(String hospitalPushKey) {
        this.hospitalPushKey = hospitalPushKey;
    }
}
