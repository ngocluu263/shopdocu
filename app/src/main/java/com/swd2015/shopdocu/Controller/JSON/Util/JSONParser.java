package com.swd2015.shopdocu.Controller.JSON.Util;

/**
 * Created by Quang on 16-Nov-15.
 */
import android.os.AsyncTask;
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

public class JSONParser extends AsyncTask<String, String, String> {

    public JSONTask API;
    public String ID = "";
    HttpURLConnection urlConnection;

    public JSONParser(){

    }

    public String readAll(Reader rd) throws IOException {
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
        String jsonText = "";
        JSONObject jsonObject;
        try {
            URL url = new URL(API.toString() + ID);
            urlConnection = (HttpURLConnection) url.openConnection();

            is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            jsonText = readAll(rd);
            JSONArray json = new JSONArray(jsonText);
            return json.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException ex){
            try {
                jsonObject = new JSONObject(jsonText);
                return jsonObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
