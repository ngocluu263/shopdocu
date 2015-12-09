package com.swd2015.shopdocu.Controller.Fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cloudinary.Cloudinary;
import com.cloudinary.android.Utils;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_PurchasedOrder;
import com.swd2015.shopdocu.Controller.Service.CustomerService;
import com.swd2015.shopdocu.Controller.Service.PurchasedOrderService;
import com.swd2015.shopdocu.Controller.Task.UploadTask;
import com.swd2015.shopdocu.R;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by PhucLHSE61219 on 20/11/2015.
 */
public class RequestSellCustomerFragment extends Fragment {
    private     Button                      requestSellButton;
    private     String                      customerName;
    private     String                      customerAddress;
    private     String                      customerEmail;
    private     String                      customerPhone;
    private     int                         customerID;
    private     int                         employeeID;
    private     Timestamp                   createDate;
    private     final int                   rsOrderStatus = 0;
    public      EditText                    customerNameEditText;
    public      EditText                    customerAddressEditText;
    public      EditText                    customerEmailEditText;
    public      EditText                    customerPhoneNumberEditText;
    public      static String               placeExchange;
    public      static int                  productPrice;
    public      static String               productName;
    public      static String               productDescription;
    public      static int                  categoryID;
    public      static String               productImageURL;
    public      static int                  responseCode;
    public      static Uri                  imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                                        Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_request_sell_customer, container, false);

        customerNameEditText = (EditText) view.findViewById(R.id.input_customer_name);
        customerAddressEditText = (EditText) view.findViewById(R.id.input_customer_address);
        customerEmailEditText = (EditText) view.findViewById(R.id.input_customer_email);
        customerPhoneNumberEditText = (EditText) view.findViewById(R.id.input_customer_phone_number);

        //Call service and get customer information to set in view
        CustomerService customerService = new CustomerService(this);
        customerService.getCustomerById(3);

        final PurchasedOrderService purchasedOrderService = new PurchasedOrderService(this);
        requestSellButton = (Button) view.findViewById(R.id.request_sell_button);
        requestSellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate an AlertDialog.Builder with its constructor
                // comfirmRSBuilder - show message confirm request sell to customer
                AlertDialog.Builder comfirmRSBuilder = new AlertDialog.Builder(getActivity());

                // Set Confirm message when user click Request Sell button
                comfirmRSBuilder.setMessage(R.string.confirm_rs_msg);

                // Button Cancel request sell -> do nothing
                comfirmRSBuilder.setPositiveButton(R.string.cancel_rs_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do nothing
                    }
                });

                // Button Confirm request sell -> save Purchased Order to Database
                comfirmRSBuilder.setNegativeButton(R.string.confirm_rs_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        customerID = 4;
                        employeeID = 3;

                        // Get customer information from view
                        customerName = customerNameEditText.getText().toString();
                        customerAddress = customerAddressEditText.getText().toString();
                        customerEmail = customerEmailEditText.getText().toString();
                        customerPhone = customerPhoneNumberEditText.getText().toString();

                        // Validate customer information and create Purchased Order in Database
                        if (!validateInput(customerName, customerAddress, customerEmail, customerPhone)) {
                            // Get datetime now to set to Create Date of Purchase Order
                            createDate = new Timestamp(System.currentTimeMillis());


                            Cloudinary cloudinary = new Cloudinary(Utils.cloudinaryUrlFromContext(getContext()));
                            String imageName = cloudinary.randomPublicId();
                            try {
                                InputStream inputStream = getActivity().getContentResolver().openInputStream(imageUri);
                                UploadTask uploadTask = new UploadTask(getContext(),inputStream);
                                uploadTask.execute(imageName);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            List<String> imageList = new ArrayList<String>();
                            imageList.add(cloudinary.url().generate(imageName));

                            //Add information to Purchased O"http://swd2015api.azurewebsites.net/api/PurchasedOrder/AddPurchasedOrderrder
                            JSON_PurchasedOrder purchasedOrder = new JSON_PurchasedOrder(
                                    customerID,                             // Customer ID
                                    employeeID,                             // Employee ID
                                    createDate,                             // Create Date
                                    rsOrderStatus,                          // Order Status
                                    placeExchange,                          // Address - Place Exchange
                                    productPrice,                           // Product Price
                                    productName,                            // Product Name
                                    productDescription,                     // Product Description
                                    categoryID,                             // Category ID
                                    imageList    // Product Image
                            );

                            // Insert Purchased Order do Database
                            purchasedOrderService.insertPurchasedOrder(purchasedOrder);

                            // Instantiate an AlertDialog.Builder with its constructor
                            // showMsgBuilder - show message response to customer
                            AlertDialog.Builder showMsgBuilder = new AlertDialog.Builder(getActivity());
                            if ( responseCode == HttpsURLConnection.HTTP_OK) {
                                // Set Confirm message when user click Request Sell button
                                showMsgBuilder.setMessage(R.string.confirm_rs_msg_is_success);

                                // Button Cancel request sell -> do nothing
                                showMsgBuilder.setPositiveButton(R.string.cancel_rs_button, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // Do nothing
                                    }
                                });
                                showMsgBuilder.show();
                            } else {
                                // Set Confirm message when user click Request Sell button
                                showMsgBuilder.setMessage(R.string.confirm_rs_msg_is_fail);

                                // Button Cancel request sell -> do nothing
                                showMsgBuilder.setPositiveButton(R.string.cancel_rs_button, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // Do nothing
                                    }
                                });
                                showMsgBuilder.show();
                            }
                        }
                    }
                });

                // Create the AlertDialog
                AlertDialog dialog = comfirmRSBuilder.create();
                dialog.show();
            }
        });

        return view;
    }

    /**
     * Method: validateInput(String customerName, String customerAddress,
     *                                                  String customerEmail, String customerPhone)
     *
     * Validate product name
     *
     * ++PhucLHSE61219_20151203
     */
    private boolean validateInput(String customerName, String customerAddress,
                                                String customerEmail, String customerPhone){
        boolean error = false;
        String emailPattern = "^[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+(\\.[A-Za-z0-9])*$";

        //Validate Customer Name
        if (customerName.equals("")){
            customerNameEditText.setError(getResources().getString(R.string
                                                                    .customer_name_require));
            error = true;
        }

        //Validate Customer Address
        if (customerAddress.equals("")){
            customerAddressEditText.setError(getResources().getString(R.string
                                                                       .customer_address_require));
            error = true;
        }

        //Validate Customer Email
        if (customerEmail.equals("")){
            customerEmailEditText.setError(getResources().getString(R.string
                                                                     .customer_email_require));
            error = true;
        } else if (!customerEmail.matches(emailPattern)){
            customerEmailEditText.setError(getResources().getString(R.string
                                                                     .customer_email_wrong));
            error = true;
        }

        //Validate Customer Phone Number
        if (customerPhone.equals("")){
            customerPhoneNumberEditText.setError(getResources().getString(R.string
                                                                          .customer_phone_require));
            error = true;
        }

        return error;
    }
}
