package com.swd2015.shopdocu.Controller.Service;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Customer;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONPostTask;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Minh.LoginFragment;
import com.swd2015.shopdocu.Minh.SignupFragment;
import com.swd2015.shopdocu.R;

/**
 * Created by Minh on 11/28/2015.
 */
public class CustomerService {
    Fragment fragment;

    public CustomerService(Fragment fragment){
        this.fragment=fragment;
    }

    public void checkLogin(String email, String password){
        JSONPostTask jsonTask = new JSONPostTask(this, JSONTask.CHECK_LOGIN,email,password);
        jsonTask.execute();
    }

    public void setCheckLogin(JSON_Customer customer){
        LoginFragment loginFragment=(LoginFragment) fragment;
        loginFragment.user=customer;
        if (customer!=null){

            //System.out.println("Post thanh cong");
            //System.out.println(customer.getGender());
        }
        else
        {
            //System.out.println("Khong thanh cong. Fail");
        }
    }

    public void createAccount(String email, String password, String fullname){
        JSONPostTask jsonTask = new JSONPostTask(this, JSONTask.CREATE_ACCOUNT,email,password,fullname);
        jsonTask.execute();


    }

    public void setCreateAccount(JSON_Customer customer){
        SignupFragment signupFragment= (SignupFragment) fragment;
        signupFragment.customer=customer;
        if (customer!=null){
            FragmentManager fragmentManager= fragment.getActivity().getFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            LoginFragment loginFragment = new LoginFragment();
            Bundle bundle=new Bundle();
            bundle.putString("email",customer.getEmail());
            loginFragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.main,loginFragment).commit();

            //System.out.println("Post thanh cong");
            //System.out.println(customer.getGender());
        }



    }



}
