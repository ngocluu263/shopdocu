package com.swd2015.shopdocu.Controller.JSON.Task;

import com.google.gson.Gson;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Banner;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONParser;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
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
        Gson gson = new Gson();
        switch (this.API){
            case GET_ALL_BANNER:
                JSON_Banner[] banners = gson.fromJson(json, JSON_Banner[].class);
                bannerService.setAllBanner(new ArrayList<>(Arrays.asList(banners)));



                break;

        }
    }


}
