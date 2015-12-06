package com.swd2015.shopdocu.Controller.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONPost;
import com.swd2015.shopdocu.Controller.JSON.JSONUtil.JSONTask;
import com.swd2015.shopdocu.Controller.Service.CartService;
import com.swd2015.shopdocu.Model.DTO.CartProduct;
import com.swd2015.shopdocu.Model.DTO.CheckoutInfo;
import com.swd2015.shopdocu.Model.DTO.Customer;
import com.swd2015.shopdocu.Model.DTO.SoldOrderDetail;
import com.swd2015.shopdocu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quangphuong on 12/2/15.
 */
public class Confirmation1Fragment extends Fragment {
    public Customer customer;
    public List<CartProduct> cartProducts;
    public List<SoldOrderDetail> soldOrderDetails;

    public static TextView totalPayment;
    public static Button confirmationButton1;
    public static Button confirmationButton2;
    public static TextView customerName;
    public static TextView customerAddress;
    public static TextView customerPhone;
    public static TextView customerEmail;
    public static ListView cartListPreview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View confirmation1View = inflater.inflate(R.layout.fragment_confirmation_1, container, false);
        totalPayment = (TextView)confirmation1View.findViewById(R.id.total);
        customerName = (TextView)confirmation1View.findViewById(R.id.customer_name);
        customerAddress = (TextView)confirmation1View.findViewById(R.id.customer_address);
        customerPhone = (TextView)confirmation1View.findViewById(R.id.customer_phone);
        customerEmail = (TextView)confirmation1View.findViewById(R.id.customer_email);
        confirmationButton1 = (Button)confirmation1View.findViewById(R.id.confirmation_button_1);
        confirmationButton2 = (Button)confirmation1View.findViewById(R.id.confirmation_button_2);
        cartListPreview = (ListView)confirmation1View.findViewById(R.id.list_cart_preview);

        customerName.setText(customer.getName());
        customerAddress.setText(customer.getAddress());
        customerPhone.setText(customer.getPhone());
        customerEmail.setText(customer.getEmail());

        CartService cartService = new CartService(this);
        cartService.getCartList();

        confirmationButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestConfirmation();

                Confirmation2Fragment confirmation2Fragment = new Confirmation2Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main, confirmation2Fragment).addToBackStack(null).commit();
            }
        });

        confirmationButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestConfirmation();

                Confirmation2Fragment confirmation2Fragment = new Confirmation2Fragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main, confirmation2Fragment).addToBackStack(null).commit();
            }
        });

        return confirmation1View;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void requestConfirmation(){
        soldOrderDetails = new ArrayList<SoldOrderDetail>();
        for (CartProduct cartProduct:cartProducts){
            SoldOrderDetail soldOrderDetail = new SoldOrderDetail(cartProduct);
            soldOrderDetails.add(soldOrderDetail);
        }
        CheckoutInfo checkoutInfo = new CheckoutInfo(customer,soldOrderDetails);
        JSONPost jsonPost = new JSONPost(JSONTask.ADD_SOLD_PRODUCT,checkoutInfo);
        jsonPost.execute();
    }
}
