package com.swd2015.shopdocu.Controller.Util.Object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SherHolmes
 */
public class User_PurchaseObj {
    private int ID;
    private List<String> ProductName;
    private String Date;
    private String Price;
    private String Status;

    public User_PurchaseObj(){
        this.ProductName = new ArrayList<String>();
    }

    public void addPurchaseList(String name){
        this.ProductName.add(name);
    }

    public User_PurchaseObj(int ID, List<String> ProductName, String Date, String Price, String Status){
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
