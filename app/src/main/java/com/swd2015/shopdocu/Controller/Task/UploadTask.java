package com.swd2015.shopdocu.Controller.Task;

import android.content.Context;
import android.os.AsyncTask;

import com.cloudinary.Cloudinary;
import com.cloudinary.android.Utils;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by quangphuong on 12/9/15.
 */
public class UploadTask extends AsyncTask<String,String,String> {
    Cloudinary cloudinary;
    private InputStream inputStream;

    public UploadTask(Context context, InputStream inputStream){
        this.inputStream = inputStream;
        cloudinary = new Cloudinary(Utils.cloudinaryUrlFromContext(context));
    }

    @Override
    protected String doInBackground(String[] params) {
        try {
            for (String imageName:params) {
                cloudinary.uploader().upload(inputStream, ObjectUtils.asMap("public_id", imageName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
