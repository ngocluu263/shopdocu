package com.swd2015.shopdocu.Controller.Util.Object;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_User_Sold;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khiem on 12/3/2015.
 */
public class User_SoldObj {
    private int ID;
    private List<String> ProductName;
    private String Date;
    private String Price;
    private String Status;

    public User_SoldObj(){
        this.ProductName = new ArrayList<String>();
    }

    public void addSoldList(String name){
        this.ProductName.add(name);
    }

    public User_SoldObj(int ID, List<String> ProductName, String Date, String Price, String Status){
        this.ID = ID;
        this.ProductName = ProductName;
        this.Date = Date;
        this.Price = Price;
        this.Status = Status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<String> getProductName() {
        return ProductName;
    }

    public void setProductName(List<String> productName) {
        ProductName = productName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
