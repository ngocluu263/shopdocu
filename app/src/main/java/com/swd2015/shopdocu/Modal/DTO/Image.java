package com.swd2015.shopdocu.Modal.DTO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quang on 18-Nov-15.
 */
public class Image {
    @SerializedName("")
    private String imageURL;


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
