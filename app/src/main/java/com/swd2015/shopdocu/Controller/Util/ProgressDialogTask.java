package com.swd2015.shopdocu.Controller.Util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.swd2015.shopdocu.Controller.Service.ProductService;

/**
 * Created by PhucLHSE61219 on 09/12/2015.
 */
public class ProgressDialogTask extends AsyncTask<String, String, String> {
    private Context context;
    private ProgressDialog progressDialog;

    public ProgressDialogTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute(){
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params){
        return "finish";
    }

    @Override
    protected void onPostExecute(String result){
        progressDialog.dismiss();
    }
}
