package com.swd2015.shopdocu.Controller.Activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.swd2015.shopdocu.Controller.Fragment.ShoppingCartFragment;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.Model.DAO.OrderedProductDAO;
import com.swd2015.shopdocu.Model.DTO.OrderedProduct;
import com.swd2015.shopdocu.Model.DTO.Product;
import com.swd2015.shopdocu.R;

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
    public static TextView productDescription;
    public static RelativeLayout productDetailContainer;
    public static RelativeLayout buyButtonSection;
    public static Button buyButton;
    public static Button addToCartButton;

    public static int productDetailContainerHeight;
    public static int buyButtonSectionHeight;
    public static int buyButtonWidth;
    public static int addToCartButtonWidth;

    public JSON_Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
//        final Fragment newFragment = new ShoppingCartFragment();
//        final FragmentTransaction ft = getFragmentManager().beginTransaction();

//        if (findViewById(R.id.fragment_shopping_cart) != null) {
//
//            // However, if we're being restored from a previous state,
//            // then we don't need to do anything and should return or else
//            // we could end up with overlapping fragments.
//            if (savedInstanceState != null) {
//                return;
//            }
//
//            // Create an instance of ExampleFragment
//            ShoppingCartFragment firstFragment = new ShoppingCartFragment();
//
//            // In case this activity was started with special instructions from an Intent,
//            // pass the Intent's extras to the fragment as arguments
//            firstFragment.setArguments(getIntent().getExtras());
//
//            // Add the fragment to the 'fragment_container' FrameLayout
//            ft.add(R.id.fragment_shopping_cart, firstFragment).commit();
//        }

        productTitle = (TextView) findViewById(R.id.product_name);
        productPrice = (TextView) findViewById(R.id.productPrice);
        productStatus = (TextView) findViewById(R.id.productStatus);
        productDescription = (TextView) findViewById(R.id.product_description);
        productDetailContainer = (RelativeLayout) findViewById(R.id.product_detail_container);
        buyButtonSection = (RelativeLayout) findViewById(R.id.buy_button_section);
        buyButton = (Button) findViewById(R.id.buy_button);
        addToCartButton = (Button) findViewById(R.id.add_to_cart_button);

        productLargeImage = (ImageView) findViewById(R.id.product_large_image);

        smallImageListView = (GridView) findViewById(R.id.product_small_image_list);

        productService.getProductByID(6);

        adjustViewSize();

        buyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                ft.replace(R.id.product_detail_container, newFragment).commit();
                OrderedProduct orderedProduct = new OrderedProduct(new Product(product),product.getStatus());
                OrderedProductDAO orderedProductDAO = new OrderedProductDAO(getBaseContext());
                orderedProductDAO.addOrderedProduct(orderedProduct);
                Intent intent = new Intent(getBaseContext(),CartActivity.class);
                intent.putExtra("GoToCart", "ProductDetailActivity");
                startActivity(intent);
            }
        });
    }

    public int convertPxToDp(float px){
        Context context = getBaseContext();
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return (int) dp;
    }

    public void adjustViewSize(){

        if(productDetailContainerHeight == 0 || buyButtonSectionHeight == 0) {
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            productDetailContainerHeight = convertPxToDp(height) * 90 / 100;
            buyButtonSectionHeight = convertPxToDp(height) * 5 / 100;
            buyButtonWidth = convertPxToDp(width) * 70 / 100;
            addToCartButtonWidth = convertPxToDp(width) * 15 / 100;
        }

        productDetailContainer.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, productDetailContainerHeight));

        LinearLayout.LayoutParams buyButtonSectionParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,buyButtonSectionHeight);
        buyButtonSectionParam.setMargins(10, 0, 10, 0);
        buyButtonSection.setLayoutParams(buyButtonSectionParam);

        RelativeLayout.LayoutParams buyButtonParam = new RelativeLayout.LayoutParams(
                buyButtonWidth, RelativeLayout.LayoutParams.MATCH_PARENT);
        buyButtonParam.setMargins(0, 0, 5, 0);
        buyButton.setLayoutParams(buyButtonParam);

//        RelativeLayout.LayoutParams addToCartButtonParam = new RelativeLayout.LayoutParams(
//                addToCartButtonWidth, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        addToCartButtonParam.setMargins(0, 0, 10, 0);
//        addToCartButtonParam.
//        addToCartButton.setLayoutParams(addToCartButtonParam);

        Drawable buyButtonImg = getApplicationContext().getResources().getDrawable( R.drawable.ic_shopping_cart );
        buyButtonImg.setBounds(0, 0, 60, 60);
        buyButton.setCompoundDrawables(buyButtonImg, null, null, null);

        Drawable addToCartImg = getApplicationContext().getResources().getDrawable( R.drawable.ic_shopping_cart_add );
        addToCartImg.setBounds(0, 0, 60, 60);
        addToCartButton.setCompoundDrawables(addToCartImg, null, null, null);

    }

}