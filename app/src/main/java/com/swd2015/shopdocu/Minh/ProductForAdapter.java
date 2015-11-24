package com.swd2015.shopdocu.Minh;

/**
 * Created by Minh on 11/21/2015.
 */
public class ProductForAdapter {
    private String name;
    private String price;
    private String image;
    public ProductForAdapter(String name,String price,String image){
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
}
