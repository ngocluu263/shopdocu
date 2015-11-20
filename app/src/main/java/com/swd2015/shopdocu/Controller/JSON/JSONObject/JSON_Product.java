package com.swd2015.shopdocu.Controller.JSON.JSONObject;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Quang on 18-Nov-15.
 */
public class JSON_Product {
    @SerializedName("ID")
    private int ID;
    @SerializedName("Name")
    private String name;
    @SerializedName("Price")
    private int price;
    @SerializedName("Description")
    private String description;
    @SerializedName("CategoryName")
    private String category;
    @SerializedName("CreateDate")
    private String createDate;
    @SerializedName("ImageURL")
    private String image;
//    @SerializedName("ImageURL")
//    private List<String> image;
    @SerializedName("Status")
    private String status;

    public JSON_Product(){

    }

    public JSON_Product(int ID, String name, int price, String description, String category, String createDate, String image, String status) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.createDate = createDate;
        this.image = image;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
