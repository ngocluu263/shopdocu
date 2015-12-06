package com.swd2015.shopdocu.Controller.Util.Object;

/**
 * Created by Minh on 11/21/2015.
 */
public class ProductForAdapter {
    private int ID;
    private String name;
    private String price;
    private String image;
    public ProductForAdapter(int productID, String name,String price,String image){
        this.ID = productID;
        this.name=name;
        this.price=price;
        this.image=image;
    }

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
