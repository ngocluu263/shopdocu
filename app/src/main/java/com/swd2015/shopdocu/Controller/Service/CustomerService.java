package com.swd2015.shopdocu.Controller.Service;

import android.app.Fragment;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Customer;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONCustomerTask;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Minh.LoginFragment;

/**
 * Created by Minh on 11/28/2015.
 */
public class CustomerService {
    Fragment fragment;

    public CustomerService(Fragment fragment){
        this.fragment=fragment;
    }

    public void checkLogin(String email, String password){
        JSONCustomerTask jsonTask = new JSONCustomerTask(this, JSONTask.GET_ALL_BANNER);
        jsonTask.execute();
    }

    public void setCheckLogin(JSON_Customer customer){
        LoginFragment loginFragment=(LoginFragment) fragment;
        loginFragment.user=customer;
    }

}
