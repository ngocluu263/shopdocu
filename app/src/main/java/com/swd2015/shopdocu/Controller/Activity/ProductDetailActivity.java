package com.swd2015.shopdocu.Controller.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.R;

import java.util.List;

/**
 * Created by Quang on 21-Nov-15.
 */
public class ProductDetailActivity extends Activity {
    ProductService productService = new ProductService(this);
    public static int productID;
    public static TextView productTitle;
    public static ImageView productLargeImage;
    public static ImageView productSmallImage1;
    public static ImageView productSmallImage2;
    public static ImageView productSmallImage3;


    public JSON_Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productTitle = (TextView) findViewById(R.id.product_name);
        productLargeImage = (ImageView) findViewById(R.id.product_large_image);
        productSmallImage1 = (ImageView) findViewById(R.id.product_small_image_1);
        productSmallImage2 = (ImageView) findViewById(R.id.product_small_image_2);
        productSmallImage3 = (ImageView) findViewById(R.id.product_small_image_3);

        productSmallImage1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Glide.with(getBaseContext())
                        .load(product.getImage().get(0))
                        .into(productLargeImage);
            }
        });

        productSmallImage2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Glide.with(getBaseContext())
                        .load(product.getImage().get(1))
                        .into(productLargeImage);
            }
        });

        productSmallImage3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Glide.with(getBaseContext())
                        .load(product.getImage().get(2))
                        .into(productLargeImage);
            }
        });

        productService.getProductByID(6);
    }
}
