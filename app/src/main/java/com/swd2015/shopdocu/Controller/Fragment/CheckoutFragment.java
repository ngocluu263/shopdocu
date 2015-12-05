package com.swd2015.shopdocu.Controller.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.swd2015.shopdocu.Model.DTO.Customer;
import com.swd2015.shopdocu.R;

/**
 * Created by quangphuong on 12/2/15.
 */
public class CheckoutFragment extends Fragment {

    public static Button checkout1Button;
    public static TextView customerName;
    public static TextView customerAddress;
    public static TextView customerMail;
    public static TextView customerPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View checkoutView = inflater.inflate(R.layout.fragment_checkout, container, false);

        checkout1Button = (Button)checkoutView.findViewById(R.id.checkout_button);
        customerName = (TextView)checkoutView.findViewById(R.id.inputCustomerName);
        customerAddress = (TextView)checkoutView.findViewById(R.id.inputCustomerAddress);
        customerMail = (TextView)checkoutView.findViewById(R.id.inputCustomerEmail);
        customerPhone = (TextView)checkoutView.findViewById(R.id.inputCustomerPhone);

        checkout1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = customerName.getText().toString();
                String address = customerAddress.getText().toString();
                String mail = customerMail.getText().toString();
                String phone = customerPhone.getText().toString();

                Customer customer = new Customer(name, address, mail, phone);

                Confirmation1Fragment confirmation1Fragment = new Confirmation1Fragment();
                confirmation1Fragment.customer = customer;

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main, confirmation1Fragment).addToBackStack(null).commit();
            }
        });


        return checkoutView;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
