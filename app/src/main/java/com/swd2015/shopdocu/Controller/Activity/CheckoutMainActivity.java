package com.swd2015.shopdocu.Controller.Activity;

import android.app.FragmentManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.swd2015.shopdocu.Controller.Fragment.CheckoutFragment;
import com.swd2015.shopdocu.R;

/**
 * Created by quangphuong on 12/1/15.
 */
public class CheckoutMainActivity extends AppCompatActivity {
//    public static Button checkout1Button;
//    public static TextView customerName;
//    public static TextView customerAddress;
//    public static TextView customerMail;
//    public static TextView customerPhone;
    public final FragmentManager fragmentManager = getFragmentManager();

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0){
            fragmentManager.popBackStack();
        } else {
            onBackPressed();
            finish();
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));

        CheckoutFragment checkoutFragment = new CheckoutFragment();
        fragmentManager.beginTransaction().replace(R.id.main, checkoutFragment).commit();

//        checkout1Button = (Button)findViewById(R.id.checkout_button);
//        customerName = (TextView)findViewById(R.id.inputCustomerName);
//        customerAddress = (TextView)findViewById(R.id.inputCustomerAddress);
//        customerMail = (TextView)findViewById(R.id.inputCustomerEmail);
//        customerPhone = (TextView)findViewById(R.id.inputCustomerPhone);
//
//        checkout1Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = customerName.getText().toString();
//                String address = customerAddress.getText().toString();
//                String mail = customerMail.getText().toString();
//                String phone = customerPhone.getText().toString();
//
//                Customer customer = new Customer(name, address, mail, phone);
//
//                Confirmation1Fragment confirmation1Fragment = new Confirmation1Fragment();
//
//                fragmentManager.beginTransaction().replace(R.id.main, confirmation1Fragment).commit();
//            }
//        });
    }

}
