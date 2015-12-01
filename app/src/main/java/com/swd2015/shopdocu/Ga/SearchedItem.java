package com.swd2015.shopdocu.Ga;

/**
 * Created by PhucLHSE61219 on 23/11/2015.
 */
public class SearchedItem {
    private String productImage;
    private String productName;
    private String productPrice;

    public SearchedItem() {
        super();
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice(){
        return productPrice;
    }

    public void setProductPrice(String productPrice){
        this.productPrice = productPrice;
    }
}
