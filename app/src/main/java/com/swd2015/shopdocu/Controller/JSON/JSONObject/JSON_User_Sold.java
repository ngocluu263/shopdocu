package com.swd2015.shopdocu.Controller.JSON.JSONObject;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by khiem on 11/30/2015.
 */
public class JSON_User_Sold {
    @SerializedName("OrderID")
    private int OrderID;
    @SerializedName("ProductName")
    private String ProductName;
    @SerializedName("CreateDate")
    private String CreateDate;
    @SerializedName("Total")
    private String Total;
    @SerializedName("OrderStatus")
    private String OrderStatus;

    public JSON_User_Sold(){

    }

    public JSON_User_Sold(int OrderID, String ProductName, String CreateDate, String Total, String OrderStatus){
        this.OrderID = OrderID;
        this.ProductName = ProductName;
        this.CreateDate = CreateDate;
        this.Total = Total;
        this.OrderStatus = OrderStatus;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }
}
