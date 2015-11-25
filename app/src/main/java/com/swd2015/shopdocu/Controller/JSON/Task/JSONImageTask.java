package com.swd2015.shopdocu.Controller.JSON.Task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.List;

/**
 * Created by quangphuong on 11/21/15.
 */
public class JSONImageTask extends AsyncTask<String, Void, Bitmap> {

    List<ImageView> imageList;
    ImageView bmImage;

    public JSONImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    public JSONImageTask(List<ImageView> imageList) {
        this.imageList = imageList;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (bmImage == null) {
            for (ImageView image : imageList) {
                image.setImageBitmap(result);
            }
        } else {
            bmImage.setImageBitmap(result);
        }
    }
}
