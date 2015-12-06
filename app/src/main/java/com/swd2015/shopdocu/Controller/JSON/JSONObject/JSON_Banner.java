package com.swd2015.shopdocu.Controller.JSON.JSONObject;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Minh on 11/28/2015.
 */
public class JSON_Banner {
    @SerializedName("ImageURL")
    private String image;

    public JSON_Banner(){

    }

    public JSON_Banner(String image){
        this.image=image;
    }

    public String getImage(){
        return this.image;
    }

    public void setImage(String image){
        this.image=image;
    }
}
