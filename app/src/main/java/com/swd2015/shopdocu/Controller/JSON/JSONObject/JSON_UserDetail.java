package com.swd2015.shopdocu.Controller.JSON.JSONObject;

import android.support.annotation.NonNull;

import java.util.Date;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SherHolmes
 */
public class JSON_UserDetail {
    @SerializedName("ID")
    private int ID;
    @SerializedName("FullName")
    private String Fullname;
    @SerializedName("Email")
    private String Email;
    @SerializedName("PhoneNumber")
    private String Phone;
    @SerializedName("Birthday")
    private String DOB;
    @SerializedName("Gender")
    private String Gender;
    @SerializedName("Address")
    private String Address;
    @SerializedName("IsGuest")
    private String IsGuest;
    @SerializedName("ImageURL")
    private String Image;

    public JSON_UserDetail(){

    }

    public JSON_UserDetail(int ID, String Fullname, String Email, String Phone, String DOB, String Gender, String Address, String IsGuest, String Image){
        this.ID = ID;
        this.Fullname = Fullname;
        this.Email = Email;
        this.Phone = Phone;
        this.DOB = DOB;
        this.Gender = Gender;
        this.Address = Address;
        this.IsGuest = IsGuest;
        this.Image = Image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getIsGuest() {
        return IsGuest;
    }

    public void setIsGuest(String isGuest) {
        IsGuest = isGuest;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
