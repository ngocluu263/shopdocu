package com.swd2015.shopdocu.Controller.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
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
    public static GridView smallImageListView;
    public static TextView productPrice;
    public static TextView productStatus;

    public JSON_Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productTitle = (TextView) findViewById(R.id.product_name);
        productPrice = (TextView) findViewById(R.id.productPrice);
        productStatus = (TextView) findViewById(R.id.productStatus);

        productLargeImage = (ImageView) findViewById(R.id.product_large_image);

        smallImageListView = (GridView) findViewById(R.id.product_small_image_list);



        productService.getProductByID(6);

    }


}
