package com.swd2015.shopdocu.Controller.Activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.swd2015.shopdocu.Controller.Service.CartService;
import com.swd2015.shopdocu.Model.DTO.CartProduct;
import com.swd2015.shopdocu.R;

import java.util.ArrayList;

/**
 * Created by quangphuong on 11/29/15.
 */
public class CartActivity extends AppCompatActivity {
    public CartService cartService;
    public static ListView cartListView;
    public static TextView totalQuantity;
    public static TextView totalPayment;
    public static Button orderButton;
    public ArrayList<CartProduct> cartList;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));

        cartListView = (ListView)findViewById(R.id.list_cart_view);
        totalQuantity = (TextView)findViewById(R.id.total_quantity);
        totalPayment = (TextView)findViewById(R.id.total_payment);
        orderButton = (Button)findViewById(R.id.order_button);
        cartService = new CartService(this);
        cartService.getCartList();

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),CheckoutMainActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        finish();
        return true;
    }



}
