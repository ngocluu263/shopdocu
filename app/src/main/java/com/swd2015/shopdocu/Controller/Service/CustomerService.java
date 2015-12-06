package com.swd2015.shopdocu.Controller.Service;



import android.app.Fragment;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Customer;
import com.swd2015.shopdocu.Controller.JSON.JSONTask.JSONCustomerTask;
import com.swd2015.shopdocu.Controller.JSON.JSONTask.JSONPostTask;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONTask;
import com.swd2015.shopdocu.Controller.Fragment.RequestSellCustomerFragment;
import com.swd2015.shopdocu.Controller.Fragment.LoginFragment;
import com.swd2015.shopdocu.Controller.Fragment.SignupFragment;

/**
 * Created by Minh on 11/28/2015.
 */
public class CustomerService {
    Fragment fragment;
    android.support.v4.app.Fragment fragmentv4;

    /**
     * Constructor: CustomerService(Fragment fragment)
     *
     * Constructor
     *
     * ++PhucLHSE61219_20151203
     */
    public CustomerService(Fragment fragment) {
        this.fragment = fragment;
    }

    public CustomerService(android.support.v4.app.Fragment fragmentv4) {
        this.fragmentv4 = fragmentv4;
    }

    /**
     * Method: getCustomerById(int ID)
     *
     * Get customer information by ID
     *
     * ++PhucLHSE61219_20151203
     */
    public void getCustomerById(int ID){
        JSONCustomerTask jsonCustomerTask = new JSONCustomerTask(this, JSONTask.GET_CUSTOMER_BY_ID,
                                                                                String.valueOf(ID));
        jsonCustomerTask.execute();
    }


    /**
     * Method: setCustomer()
     *
     * Set customer information to view (RequestSellCustomerFragment)
     *
     * ++PhucLHSE61219_20151203
     */
    public void setCustomer(JSON_Customer customer) {
        switch (fragmentv4.getClass().getSimpleName()) {
            case "RequestSellCustomerFragment":
                if (customer != null) {
                    RequestSellCustomerFragment requestSellCustomerFragment =
                                                            (RequestSellCustomerFragment) fragmentv4;

                    //Get customer information and set to view (RequestSellCustomerFragment)
                    requestSellCustomerFragment.customerNameEditText
                                                .setText(customer.getCustomerName());
                    requestSellCustomerFragment.customerAddressEditText
                                               .setText(customer.getCustomerAddress());
                    requestSellCustomerFragment.customerEmailEditText
                                               .setText(customer.getCustomerEmail());
                    requestSellCustomerFragment.customerPhoneNumberEditText
                                               .setText(customer.getCustomerPhoneNumber());
                }
        }
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
//            FragmentManager fragmentManager= fragment.getActivity().getFragmentManager();
//            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//            fragmentTransaction.remove(signupFragment);
//            signupFragment.show(fragmentManager,"dialog");

            signupFragment.getActivity().showDialog(1);

//            FragmentManager fragmentManager= fragment.getActivity().getFragmentManager();
//            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//            LoginFragment loginFragment = new LoginFragment();
//            Bundle bundle=new Bundle();
//            bundle.putString("email",customer.getEmail());
//            loginFragment.setArguments(bundle);
//
//            fragmentTransaction.replace(R.id.main,loginFragment).commit();

            //System.out.println("Post thanh cong");
            //System.out.println(customer.getGender());
        }



    }




}
