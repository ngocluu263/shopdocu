package com.swd2015.shopdocu.Model.DTO;

import java.util.Date;

/**
 * Created by Quang on 14-Nov-15.
 */
public class Product {
    private int ID;
    private String name;
    private Float price;
    private String description;
    private Integer category;
    private Date createDate;
    private String image;

    public Product(){

    }

    public Product(int ID, String name, Float price, String description, Integer category, Date createDate, String image) {
        this.setID(ID);
        this.setName(name);
        this.setPrice(price);
        this.setDescription(description);
        this.setCategory(category);
        this.setCreateDate(createDate);
        this.setImage(image);
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
