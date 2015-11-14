package com.swd2015.shopdocu.Modal.DTO;

import java.util.Date;

/**
 * Created by Quang on 14-Nov-15.
 */
public class Product {
    protected int ID;
    protected String name;
    protected Integer price;
    protected String description;
    protected Integer category;
    protected Integer status;
    protected Date createDate;
    protected Integer amount;
    protected String image;

    public Product(){

    }

    public Product(int ID, String name, Integer price, String description, Integer category, Integer status, Date createDate, Integer amount, String image) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.status = status;
        this.createDate = createDate;
        this.amount = amount;
        this.image = image;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
