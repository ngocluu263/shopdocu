package com.swd2015.shopdocu.Controller.Service;

/**
 * Created by Quang on 16-Nov-15.
 */
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.swd2015.shopdocu.Controller.Activity.MainActivity;
import com.swd2015.shopdocu.Controller.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Modal.DTO.Image;
import com.swd2015.shopdocu.Modal.DTO.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class JSONParser extends AsyncTask<String, String, String> {

    MainActivity mainActivity;
    private static String API = "http://swd2015-001-site1.1tempurl.com/api/product";
    HttpURLConnection urlConnection;

    public JSONParser(MainActivity activity){
        mainActivity = activity;
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream is = null;
        try {
            URL url = new URL(API);
            urlConnection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String json) {
        Gson gson = new Gson();
        JSON_Product[] products = gson.fromJson(json.toString(), JSON_Product[].class);
        JSON_Product product = products[0];
        System.out.println("-- Image URL: " + product.getImage());
        mainActivity.example = product.getImage();
    }
    
}
