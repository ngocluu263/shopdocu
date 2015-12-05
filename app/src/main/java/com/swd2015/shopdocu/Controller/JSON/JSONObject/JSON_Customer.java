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
    private String name;
    @SerializedName("Gender")
    private String gender;
    @SerializedName("Email")
    private String email;
    @SerializedName("Address")
    private String address;
    @SerializedName("PhoneNumber")
    private String phone;
    @SerializedName("isGuest")
    private Boolean isGuest;
    @SerializedName("Password")
    private String password;
    @SerializedName("Birthday")
    private Date birthday;
    @SerializedName("ImageURL")
    private String customerImageURL;

    public JSON_Customer (){


    }

    public JSON_Customer(int customerID, String customerName, String customerEmail,
                         String customerPhoneNumber, String customerBirthday, String customerGender,
                         String customerAddress, String isGuest, String customerImageURL) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerBirthday = customerBirthday;
        this.customerGender = customerGender;
        this.customerAddress = customerAddress;
        this.isGuest = isGuest;
        this.customerImageURL = customerImageURL;
    }
    public JSON_Customer(int ID,String fullName,String email,String phoneNumber,Date birthday,String  gender,String address,String avatar){
        this.ID=ID;
        this.name=fullName;
        this.email=email;
        this.phone=phoneNumber;
        this.birthday=birthday;
        this.gender=gender;
        this.address=address;
        this.avatar=avatar;
    }

    public int getID(){return this.ID;}
    public String getName(){return this.name;}
    public String getEmail(){return this.email;}
    public String getPhoneNumber(){return this.phone;}
    public String getGender(){return this.gender;}
    public String getAddress(){return this.address;}
    public String getAvatar(){return this.avatar;}
    public Date getBirthday(){return  this.birthday;}

    public String getCustomerEmail(){
        return customerEmail;
    }


    public void setName(String fullName) {
        this.name = fullName;

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


    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;

    }

    public String getCustomerAddress(){
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress){
        this.customerAddress = customerAddress;
    }

    public String getCustomerIsGuest(){
        return isGuest;
    }

    public void setCustomerIsGuest(String isGuest){
        this.isGuest = isGuest;
    }

    public String getCustomerImageURL(){
        return customerImageURL;
    }

    public void setCustomerImageURL(String customerImageURL){
        this.customerImageURL = customerImageURL;
    }
}
