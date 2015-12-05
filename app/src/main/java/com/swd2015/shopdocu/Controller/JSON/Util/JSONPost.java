package com.swd2015.shopdocu.Controller.JSON.Util;

import android.os.AsyncTask;

import com.google.gson.Gson;
<<<<<<< HEAD
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_PurchasedOrder;
import com.swd2015.shopdocu.Ga.RequestSellCustomerFragment;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
=======
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;
//import com.swd2015.shopdocu.Model.DTO.CheckoutInfo;
//import com.swd2015.shopdocu.Model.DTO.Customer;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
>>>>>>> origin/Khiem_2
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
<<<<<<< HEAD
=======
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
>>>>>>> origin/Khiem_2

import javax.net.ssl.HttpsURLConnection;

/**
<<<<<<< HEAD

 * Created by quangphuong on 12/2/15.
 * Created by Minh on 12/3/2015.

 */
public class JSONPost extends AsyncTask<String, String, String> {

    public JSONTask API;
    String json;
    Gson gson = new Gson();
    JSON_PurchasedOrder purchasedOrder;

    public JSONPost(){

    }

    public JSONPost(JSONTask task, JSON_PurchasedOrder purchasedOrder) {
        this.API = task;
        this.purchasedOrder = purchasedOrder;
        json = gson.toJson(this.purchasedOrder, JSON_PurchasedOrder.class);
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
        return "";
    }
}
