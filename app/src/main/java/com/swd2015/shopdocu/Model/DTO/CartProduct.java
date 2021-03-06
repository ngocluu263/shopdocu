package com.swd2015.shopdocu.Model.DTO;

/**
 * Created by Quang on 14-Nov-15.
 */
public class CartProduct extends Product {
    private int orderID;
    private String status;
    private int quantity;
    private float total;

    public CartProduct(){}

    public CartProduct(int quantity, long total) {
        this.setQuantity(quantity);
        this.setTotal(total);
    }

    public CartProduct(Product product, String status) {
        super(product.getID(), product.getName(), product.getPrice(), product.getDescription(), product.getCategory(),
                product.getCreateDate(), product.getImage());
//        this.setOrderID(orderID);
        this.setQuantity(0);
        this.setTotal(0);
        this.setStatus(status);
    }

    public CartProduct(Product product, String status, int quantity) {
        super(product.getID(), product.getName(), product.getPrice(), product.getDescription(), product.getCategory(),
                product.getCreateDate(), product.getImage());
//        this.setOrderID(orderID);
        this.setQuantity(quantity);
        this.setTotal(this.getPrice() * quantity);
        this.setStatus(status);
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
