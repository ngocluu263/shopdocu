package com.swd2015.shopdocu.Modal.DTO;

/**
 * Created by Quang on 14-Nov-15.
 */
public class OrderedProduct extends Product {
    private int orderID;
    private int quantity;
    private long total;

    public OrderedProduct(int quantity, long total) {
        this.setQuantity(quantity);
        this.setTotal(total);
    }

    public OrderedProduct(Product product, int orderID, int quantity, long total) {
        super(product.ID, product.name, product.price, product.description, product.category,
                product.status, product.createDate, product.amount, product.image);
        this.setOrderID(orderID);
        this.setQuantity(quantity);
        this.setTotal(this.price*quantity);
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
