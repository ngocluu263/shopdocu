package com.swd2015.shopdocu.Controller.Activity;

import android.app.Activity;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.Service.CustomerService;
import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.Controller.Util.Object.NavigationItem;
import com.swd2015.shopdocu.Model.DAO.CartProductDAO;
import com.swd2015.shopdocu.Model.DAO.UserDAO;
import com.swd2015.shopdocu.Model.DTO.CartProduct;
import com.swd2015.shopdocu.Model.DTO.Customer;
import com.swd2015.shopdocu.Model.DTO.Product;
import com.swd2015.shopdocu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang on 21-Nov-15.
 */
public class ProductDetailActivity extends NavigationActivity {
    ProductService productService = new ProductService(this);
    public int productID;
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
    public static ImageButton favoriteButton;

    public static int productDetailContainerHeight;
    public static int buyButtonSectionHeight;
    public static int buyButtonWidth;
    public static int addToCartButtonWidth;

    public static JSON_Product product;
    public static List<Integer> toggleFavoriteArray = new ArrayList<>();

    //for toolbar and navigation item



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        // toolbar and navigation item
        createNavigation();

        avatar= (ImageView)findViewById(R.id.avatar);
        userName= (TextView) findViewById((R.id.avartarName));

        //region main activity
        productID = getIntent().getExtras().getInt("productID");

        productTitle = (TextView) findViewById(R.id.product_name);
        productPrice = (TextView) findViewById(R.id.productPrice);
        productStatus = (TextView) findViewById(R.id.productStatus);
        productDescription = (TextView) findViewById(R.id.product_description);
        productDetailContainer = (RelativeLayout) findViewById(R.id.product_detail_container);
        buyButtonSection = (RelativeLayout) findViewById(R.id.buy_button_section);
        buyButton = (Button) findViewById(R.id.buy_button);
        addToCartButton = (Button) findViewById(R.id.add_to_cart_button);
        favoriteButton = (ImageButton) findViewById(R.id.favorite_button);
        productLargeImage = (ImageView) findViewById(R.id.product_large_image);
        smallImageListView = (GridView) findViewById(R.id.product_small_image_list);
        toggleFavoriteArray.add(R.drawable.ic_blank_heart);
        toggleFavoriteArray.add(R.drawable.ic_red_heart);

        productService.getProductByID(productID);

        buyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CartProduct cartProduct = new CartProduct(new Product(product), product.getStatus());
                CartProductDAO cartProductDAO = new CartProductDAO(getBaseContext());
                cartProductDAO.addOrderedProduct(cartProduct);
                Intent intent = new Intent(getBaseContext(), CartActivity.class);
                intent.putExtra("GoToCart", "ProductDetailActivity");
                startActivity(intent);
            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartProduct cartProduct = new CartProduct(new Product(product), product.getStatus());
                CartProductDAO cartProductDAO = new CartProductDAO(getBaseContext());
                cartProductDAO.addOrderedProduct(cartProduct);
                Toast.makeText(getApplicationContext(),getResources().
                        getString(R.string.add_to_cart_message),Toast.LENGTH_LONG).show();
            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (productService.alreadyFavorited(productID)) {
                    favoriteButton.setBackgroundResource(R.drawable.ic_blank_heart);
                } else {
                    favoriteButton.setBackgroundResource(R.drawable.ic_red_heart);
                }
                productService.toggleFavorite(product);
                Toast.makeText(getApplicationContext(),getResources().
                        getString(R.string.add_to_favorite_message),Toast.LENGTH_LONG).show();
            }
        });

        adjustViewSize();
        //endregion
        final Activity activity = this;
        final UserDAO userDAO=new UserDAO(getBaseContext());
        Customer customer=userDAO.getUser();
        if (customer!=null){
            if (listNavItems.size()<=5){
                listNavItems.add(new NavigationItem("Đăng xuất", R.drawable.ic_signout));
            }
            CustomerService customerService=new CustomerService(activity);
            customerService.getCustomerById(customer.getID());
        }
        //regionchange title,add back button
//        HomePageActivity activity=(HomePageActivity)getActivity();
//        android.support.v7.app.ActionBar actionBar=activity.actionBar;
//        ActionBarDrawerToggle toggle=activity.actionBarDrawerToggle;
//        toggle.setDrawerIndicatorEnabled(false);
//        actionBar.setTitle("Đăng kí tài khoản");
//        actionBar.setHomeButtonEnabled(true);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBar.setTitle("Thông tin sản phẩm");
        actionBar.setHomeButtonEnabled(true);
        //endregion

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

            productDetailContainerHeight = height * 80 / 100;
            buyButtonSectionHeight = height * 8 / 100;
            buyButtonWidth = width * 70 / 100;
            addToCartButtonWidth = width * 15 / 100;

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

        Drawable buyButtonImg = getApplicationContext().getResources().getDrawable(R.drawable.ic_shopping_cart);
        buyButtonImg.setBounds(0, 0, 60, 60);
        buyButton.setCompoundDrawables(buyButtonImg, null, null, null);

        Drawable addToCartImg = getApplicationContext().getResources().getDrawable( R.drawable.ic_shopping_cart_add );
        addToCartImg.setBounds(0, 0, 60, 60);
        addToCartButton.setCompoundDrawables(addToCartImg, null, null, null);

    }

    //set listenr for menu , search and cart icon



    //add menu item


}