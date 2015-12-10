package com.swd2015.shopdocu.Controller.JSON.JSONObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by quangphuong on 12/9/15.
 */
public class JSONUserObject {

    @SerializedName("Customer")
    private JSON_UserDetail userDetail;

    @SerializedName("ImageURL")
    private String imageURL;

    public JSONUserObject(){

    }

    public JSONUserObject(JSON_UserDetail userDetail, String imageURL) {
        this.setUserDetail(userDetail);
        this.setImageURL(imageURL);
    }


    public JSON_UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(JSON_UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
