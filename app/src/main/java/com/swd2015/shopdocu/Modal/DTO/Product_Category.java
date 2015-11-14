package com.swd2015.shopdocu.Modal.DTO;

/**
 * Created by Quang on 14-Nov-15.
 */
public class Product_Category {
    private int ID;
    private String name;

    public Product_Category(){}

    public Product_Category(int ID, String name) {
        this.setID(ID);
        this.setName(name);
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
}
