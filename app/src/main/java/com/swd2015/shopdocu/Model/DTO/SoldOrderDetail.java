package com.swd2015.shopdocu.Model.DTO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quang on 14-Nov-15.
 */
public class SoldOrderDetail {
    @SerializedName("ID")
    private int ID;
    @SerializedName("SoldOrderID")
    private int soldOrderID;
    @SerializedName("ProductID")
    private int productID;
    @SerializedName("Price")
    private float price;
    @SerializedName("Quantity")
    private int quantity;
    @SerializedName("IsDelete")
    private Boolean isDelete;

    public SoldOrderDetail(){}

    public SoldOrderDetail(CartProduct cartProduct){
        this.setProductID(cartProduct.getID());
        this.setPrice(cartProduct.getPrice());
        this.setQuantity(cartProduct.getQuantity());
    }

    public SoldOrderDetail(int ID, int orderID, int productID, int price, int quantity, Boolean isDelete) {
        this.setID(ID);
        this.setOrderID(orderID);
        this.setProductID(productID);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setIsDelete(isDelete);
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getOrderID() {
        return soldOrderID;
    }

    public void setOrderID(int orderID) {
        this.soldOrderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}
