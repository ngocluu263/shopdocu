package com.swd2015.shopdocu.Controller.JSON.JSONObject;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by PhucLHSE61219 on 03/12/2015.
 */
public class JSON_PurchasedOrder {
    @SerializedName("CustomerID")
    private int customerID;
    @SerializedName("EmployeeID")
    private int employeeID;
    @SerializedName("CreateDate")
    private Timestamp createDate;
    @SerializedName("OrderStatus")
    private int orderStatus;
    @SerializedName("Address")
    private String address;
    @SerializedName("Total")
    private int total;
    @SerializedName("ProductName")
    private String productName;
    @SerializedName("Description")
    private String description;
    @SerializedName("Category")
    private int category;
    @SerializedName("ImageURL")
    private String imageURL;

    public JSON_PurchasedOrder(){

    }

    public JSON_PurchasedOrder(int customerID, int employeeID, Timestamp createDate, int orderStatus,
                               String address, int total, String productName, String description,
                               int category, String imageURL) {
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.createDate = createDate;
        this.orderStatus = orderStatus;
        this.address = address;
        this.total = total;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.imageURL = imageURL;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
