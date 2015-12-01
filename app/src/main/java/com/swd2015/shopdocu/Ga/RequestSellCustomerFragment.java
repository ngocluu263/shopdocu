package com.swd2015.shopdocu.Ga;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swd2015.shopdocu.R;

/**
 * Created by PhucLHSE61219 on 20/11/2015.
 */
public class RequestSellCustomerFragment extends Fragment {
    private TextView addCustomerName;
    private TextView addCustomerAddress;
    private TextView addCustomerEmail;
    private TextView addCustomerPhoneNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                                        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_sell_customer, container, false);

        addCustomerName = (TextView) view.findViewById(R.id.input_customer_name);
        addCustomerAddress = (TextView) view.findViewById(R.id.input_customer_address);
        addCustomerEmail = (TextView) view.findViewById(R.id.input_customer_email);
        addCustomerPhoneNumber = (TextView) view.findViewById(R.id.input_customer_phone_number);

        addCustomerName.setText("Gà Gầ Gừ");
        addCustomerAddress.setText("Chjckenzzz android onWindowStartingActionMode");
        addCustomerEmail.setText("lolchjcken@ggg.ggg");
        addCustomerPhoneNumber.setText("01234567899");

        return view;
    }
}
