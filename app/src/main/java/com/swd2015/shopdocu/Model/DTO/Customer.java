/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swd2015.shopdocu.Model.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 *
 * @author Quang
 */
public class Customer implements Serializable{
    @SerializedName("ID")
    private int ID;
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

    public Customer() {
    }

    public Customer(String name, String email, String address, String phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.isGuest = true;
    }


    public Customer(int ID, String name, String gender, String email, String address, String phone, Boolean isGuest) {
        this.ID = ID;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.isGuest = isGuest;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the isGuest
     */
    public Boolean getIsGuest() {
        return isGuest;
    }

    /**
     * @param isGuest the isGuest to set
     */
    public void setIsGuest(Boolean isGuest) {
        this.isGuest = isGuest;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
