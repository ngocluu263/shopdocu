package com.swd2015.shopdocu.Modal.DTO;

/**
 * Created by Quang on 14-Nov-15.
 */
public class OrderedProduct extends Product {
    private int orderID;
    private String status;
    private int quantity;
    private float total;

    public OrderedProduct(int quantity, long total) {
        this.setQuantity(quantity);
        this.setTotal(total);
    }

    public OrderedProduct(Product product, int orderID,String status, int quantity, float total) {
        super(product.getID(), product.getName(), product.getPrice(), product.getDescription(), product.getCategory(),
                product.getCreateDate(), product.getImage());
        this.setOrderID(orderID);
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
