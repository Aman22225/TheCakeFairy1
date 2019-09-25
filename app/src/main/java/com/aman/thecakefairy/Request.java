package com.aman.thecakefairy;

import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class Request {
    private  String FullName;
    private  String Mobile;
    private  String CakeName;
    private String address;
    private  String totalPrice;

    public Request() {
    }

    public Request(String fullName, String mobile, String cakeName, String address, String totalPrice) {
        FullName = fullName;
        Mobile = mobile;
        CakeName = cakeName;
        this.address = address;
        this.totalPrice = totalPrice;
    }

    public Request(TextView cakeName, EditText editAddress, EditText editFullName, TextView cakePrice, EditText editNumber) {

    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getCakeName() {
        return CakeName;
    }

    public void setCakeName(String cakeName) {
        CakeName = cakeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
