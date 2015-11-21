package com.swd2015.shopdocu.Minh;

/**
 * Created by Minh on 11/21/2015.
 */
public class ProductForAdapter {
    private String name;
    private String price;
    private int image;
    public ProductForAdapter(String name,String price,int image){
        this.name=name;
        this.price=price;
        this.image=image;
    }

    public int getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
