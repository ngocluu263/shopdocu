package com.swd2015.shopdocu.Controller.Service;

import android.support.v4.app.Fragment;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Customer;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONCustomerTask;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Minh.LoginFragment;

/**
 * Created by Minh on 11/28/2015.
 */
public class CustomerService {
    Fragment fragment;

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
        switch (fragment.getClass().getSimpleName()) {
            case "RequestSellCustomerFragment":
                if (customer != null) {
                    RequestSellCustomerFragment requestSellCustomerFragment =
                                                            (RequestSellCustomerFragment) fragment;

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
}
