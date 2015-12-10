package com.swd2015.shopdocu.Controller.JSON.JSONObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by quangphuong on 12/9/15.
 */
public class JSONRequestSellObject {

    @SerializedName("PurchasedOrder")
    private JSON_PurchasedOrder json_purchasedOrder;

    @SerializedName("ListImage")
    private List<String> imageList;

    public JSONRequestSellObject(){

    }

    public JSONRequestSellObject(JSON_PurchasedOrder json_purchasedOrder, List<String> imageList) {
        this.setJson_purchasedOrder(json_purchasedOrder);
        this.setImageList(imageList);
    }


    public JSON_PurchasedOrder getJson_purchasedOrder() {
        return json_purchasedOrder;
    }

    public void setJson_purchasedOrder(JSON_PurchasedOrder json_purchasedOrder) {
        this.json_purchasedOrder = json_purchasedOrder;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
