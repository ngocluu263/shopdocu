package com.swd2015.shopdocu.Controller.JSON.JSONObject;

import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.List;

/**
 * Created by Quang on 18-Nov-15.
 */
public class JSON_Product {
    @SerializedName("ProductID")
    private int ID;
    @SerializedName("ProductName")
    private String name;
    @SerializedName("ProductPrice")
    private int price;
    @SerializedName("ProductDescription")
    private String description;
    @SerializedName("CategoryID")
    private String category;
    @SerializedName("CreateDate")
    private String createDate;
    @SerializedName("ImageURL")
    private List<String> image;
    @SerializedName("Status")
    private String status;

    public JSON_Product(){

    }

    public JSON_Product(int ID, String name, int price, String description, String category, String createDate, List<String> image, String status) {
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

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
