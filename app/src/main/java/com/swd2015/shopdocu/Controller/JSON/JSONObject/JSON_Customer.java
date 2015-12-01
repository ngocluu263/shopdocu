package com.swd2015.shopdocu.Controller.JSON.JSONObject;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by PhucLHSE61219 on 28/11/2015.
 */
public class JSON_Customer {
    @SerializedName("ID")
    private int customerID;
    @SerializedName("FullName")
    private String customerName;
    @SerializedName("Email")
    private String customerEmail;
    @SerializedName("PhoneNumber")
    private String customerPhoneNumber;
    @SerializedName("Birthday")
    private String customerBirthday;
    @SerializedName("Gender")
    private String customerGender;
    @SerializedName("Address")
    private String customerAddress;
    @SerializedName("ImageURL")
    private String customerImageURL;

    public JSON_Customer (){

    }

    public JSON_Customer(int customerID, String customerName, String customerEmail,
                         String customerPhoneNumber, String customerBirthday, String customerGender,
                         String customerAddress, String customerImageURL) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerBirthday = customerBirthday;
        this.customerGender = customerGender;
        this.customerAddress = customerAddress;
        this.customerImageURL = customerImageURL;
    }

    public String getCustomerName(){
        return customerName;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public String getCustomerEmail(){
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail){
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber(){
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber){
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerBirthday(){
        return customerBirthday;
    }

    public void setCustomerBirthday(String customerBirthday){
        this.customerBirthday = customerBirthday;
    }

    public String getCustomerGender(){
        return customerGender;
    }

    public void setCustomerGender(String customerGender){
        this.customerGender = customerGender;
    }

    public String getCustomerAddress(){
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress){
        this.customerAddress = customerAddress;
    }

    public String getCustomerImageURL(){
        return customerGender;
    }

    public void setCustomerImageURL(String customerImageURL){
        this.customerImageURL = customerImageURL;
    }
}
