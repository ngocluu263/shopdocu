package com.swd2015.shopdocu.Modal.DTO;

/**
 * Created by Quang on 14-Nov-15.
 */
public class OrderDetail {
    private int ID;
    private int orderID;
    private int productID;
    private int price;
    private int quantity;
    private Boolean isDelete;

    public OrderDetail(){}

    public OrderDetail(int ID, int orderID, int productID, int price, int quantity, Boolean isDelete) {
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
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
