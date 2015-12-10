package com.swd2015.shopdocu.Controller.JSON.JSONTask;

import com.google.gson.Gson;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Banner;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONParser;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONTask;
import com.swd2015.shopdocu.Controller.Service.BannerService;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Minh on 11/28/2015.
 */
public class JSONBannerTask extends JSONParser {
    BannerService bannerService;

    public JSONBannerTask(BannerService service, JSONTask task){
        this.bannerService = service;
        this.API = task;

    }

    @Override
    protected void onPostExecute(String json) {
        if(json == null || json.trim().length() == 0){
            bannerService.connectionError();
        }
        Gson gson = new Gson();
        switch (this.API){
            case GET_ALL_BANNER:
                JSON_Banner[] banners = gson.fromJson(json, JSON_Banner[].class);
                bannerService.setAllBanner(new ArrayList<>(Arrays.asList(banners)));



                break;

        }
    }


}
