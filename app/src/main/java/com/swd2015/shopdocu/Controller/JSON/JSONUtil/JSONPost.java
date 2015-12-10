package com.swd2015.shopdocu.Controller.JSON.JSONUtil;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSONRequestSellObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
//import com.swd2015.shopdocu.Model.DTO.CheckoutInfo;
//import com.swd2015.shopdocu.Model.DTO.Customer;

import com.swd2015.shopdocu.Controller.Service.PurchasedOrderService;
import com.swd2015.shopdocu.Model.DTO.CheckoutInfo;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by quangphuong on 12/2/15.
 */
public class JSONPost extends AsyncTask<String, String, String> {
    Gson gson = new Gson();
    String json;
    public JSONTask API;
    CheckoutInfo checkoutInfo;
    JSONRequestSellObject requestSellObject;
    PurchasedOrderService purchasedOrderService;

    public JSONPost(PurchasedOrderService purchasedOrderService,JSONTask task, JSONRequestSellObject requestSellObject) {
        this.purchasedOrderService = purchasedOrderService;
        this.API = task;
        this.requestSellObject = requestSellObject;
        json = gson.toJson(this.requestSellObject, JSONRequestSellObject.class);
    }

    public JSONPost(JSONTask task, CheckoutInfo checkoutInfo) {
        this.API = task;
        this.checkoutInfo = checkoutInfo;
        this.json = gson.toJson(checkoutInfo, CheckoutInfo.class);
    }

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        String response = "";
        try {
            url = new URL(API.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setChunkedStreamingMode(0);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream outputStream = new BufferedOutputStream(conn.getOutputStream());
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(outputStream, "UTF-8"));
            System.out.println("JSON Sent Object: " + json);

            writer.write(json);
            writer.flush();
            writer.close();

            int responseCode = conn.getResponseCode();

            System.out.println("POST Response Code: " + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            System.out.println("POST Response Message: " + response);
            outputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        return gson.toJson(response);
    }

    @Override
    protected void onPostExecute(String json) {
        switch (this.API){
            case POST_INSERT_PURCHASED_ORDER:
                purchasedOrderService.httpPostResponse(json);
                break;
        }
    }
}
