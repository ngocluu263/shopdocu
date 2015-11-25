package com.swd2015.shopdocu.Model.DTO;

/**
 * Created by Quang on 14-Nov-15.
 */
public class Order_Status {
    private int ID;
    private String name;

    public Order_Status(){}

    public Order_Status(int ID, String name) {
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
