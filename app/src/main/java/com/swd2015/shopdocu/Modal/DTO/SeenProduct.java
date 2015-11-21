package com.swd2015.shopdocu.Modal.DTO;

/**
 * Created by Quang on 14-Nov-15.
 */
public class SeenProduct {
    private int ID;
    private int productID;

    public SeenProduct(int ID, int productID) {
        this.setID(ID);
        this.setProductID(productID);
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
