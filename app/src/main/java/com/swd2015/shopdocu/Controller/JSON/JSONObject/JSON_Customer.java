package com.swd2015.shopdocu.Controller.JSON.JSONObject;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Minh on 11/28/2015.
 */
public class JSON_Customer {
    @SerializedName("ID")
    private int ID;
    @SerializedName("FullName")
    private String fullName;
    @SerializedName("Email")
    private String email;
    @SerializedName("PhoneNumber")
    private String  phoneNumber;
    @SerializedName("Birthday")
    private Date birthday;
    @SerializedName("Gender")
    private String  gender;
    @SerializedName("Address")
    private String  address;
    @SerializedName("ImageURL")
    private String avatar;


    public JSON_Customer(){

    }

    public JSON_Customer(int ID,String fullName,String email,String phoneNumber,Date birthday,String  gender,String address,String avatar){
        this.ID=ID;
        this.fullName=fullName;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.birthday=birthday;
        this.gender=gender;
        this.address=address;
        this.avatar=avatar;
    }

    public int getID(){return this.ID;}
    public String getFullName(){return this.fullName;}
    public String getEmail(){return this.email;}
    public String getPhoneNumber(){return this.phoneNumber;}
    public String getGender(){return this.gender;}
    public String getAddress(){return this.address;}
    public String getAvatar(){return this.avatar;}
    public Date getBirthday(){return  this.birthday;}

    public void setID(int ID){this.ID=ID;}

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
