package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;

import com.bumptech.glide.Glide;
import com.swd2015.shopdocu.Controller.Activity.FavoriteProductActivity;
import com.swd2015.shopdocu.Controller.Activity.MainActivity;
import com.swd2015.shopdocu.Controller.Activity.ProductDetailActivity;
import com.swd2015.shopdocu.Controller.Activity.SeenProductActivity;
import com.swd2015.shopdocu.Controller.Adapter.ProductDetailAdapter;
import com.swd2015.shopdocu.Controller.Adapter.ProductGridViewAdapter;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.JSON.JSONTask.JSONProductTask;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;

import android.view.View;
import android.widget.BaseAdapter;

import com.swd2015.shopdocu.Controller.Task.FavoriteTask;
import com.swd2015.shopdocu.Controller.Task.SeenTask;
import com.swd2015.shopdocu.Controller.Util.DBTask;
import com.swd2015.shopdocu.Ga.SearchActivity;
import com.swd2015.shopdocu.Ga.ShowSearchedResultAdapter;
import com.swd2015.shopdocu.Model.DTO.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang on 20/11/2015.
 */
public class ProductService {
    public Activity activity;
    BaseAdapter baseAdapter;

    public ProductService(Activity activity) {
        this.activity = activity;
    }

    public ProductService(BaseAdapter baseAdapter) {
        this.baseAdapter = baseAdapter;
    }

    public void getAllProduct() {
        JSONProductTask jsonTask = new JSONProductTask(this, JSONTask.GET_ALL_PRODUCT);
        jsonTask.execute();
    }

    public void setAllProduct(ArrayList<JSON_Product> productList) {
        switch (activity.getClass().getSimpleName()) {
            case "ShowSearchedResultAdapter":
                System.out.println("GA GAM GU");
                ShowSearchedResultAdapter showSearchedResultAdapter =
                        (ShowSearchedResultAdapter) baseAdapter;
                //showSearchResultAdapter.searchedProductList = productList;
                for (int i = 0; i < productList.size(); ++i) {
                    showSearchedResultAdapter.searchedProductName = productList.get(i).getName();
                    System.out.println("aaaaaSAFASFa = " + productList.get(i).getName());

                    showSearchedResultAdapter.searchedProductImageURL =
                            productList.get(0).getImage().get(0).toString();
                    System.out.println("ADFHG = " + productList.get(0).getImage().get(0).toString());

                    showSearchedResultAdapter.searchedProductPrice =
                            String.valueOf(productList.get(i).getPrice());
                    System.out.println("Price = " + String.valueOf(productList.get(i).getPrice()));
                }
                break;
        }
    }

    public void getProductByID(int ID) {
        JSONProductTask jsonTask = new JSONProductTask(this, JSONTask.GET_PRODUCT_BY_ID,
                String.valueOf(ID));
        jsonTask.execute();
    }

    public void setProduct(JSON_Product product) {
        switch (activity.getClass().getSimpleName()) {
            case "ProductDetailActivity":
                ProductDetailActivity productDetailActivity = (ProductDetailActivity) activity;

                productDetailActivity.product = product;
                productDetailActivity.productTitle.setText(product.getName());
                productDetailActivity.productPrice.setText(String.valueOf(product.getPrice()));
                productDetailActivity.productStatus.setText(product.getStatus());
                productDetailActivity.productDescription.setText(product.getDescription());

                Glide.with(productDetailActivity)
                        .load(product.getImage().get(0))
                        .into(productDetailActivity.productLargeImage);

                productDetailActivity.smallImageListView.setVisibility(View.VISIBLE);
                productDetailActivity.smallImageListView.setAdapter(new ProductDetailAdapter(productDetailActivity, product.getImage()));
                saveSeenProduct(product);
                break;
            case "MainActivity":
                MainActivity mainActivity = (MainActivity) activity;
                mainActivity.product = product;
                break;
        }
    }

    public void getSearchedProducts(String productName, int categoryID) {
        JSONProductTask jsonTask = new JSONProductTask(this,
                JSONTask.GET_SEARCHED_PRODUCTS, productName, String.valueOf(categoryID));
        jsonTask.execute();
    }

    public void setSearchedProducts(ArrayList<JSON_Product> productList) {
        switch (activity.getClass().getSimpleName()) {
            case "SearchActivity":
                SearchActivity searchActivity = (SearchActivity) activity;
                searchActivity.searchResultGridView.setVisibility(View.VISIBLE);
                System.out.println("QWERT " + productList.size());
                searchActivity.searchResultGridView.setAdapter(new ShowSearchedResultAdapter(searchActivity, productList));
                break;
        }
    }

    public void saveSeenProduct(JSON_Product jsonProduct) {
        SeenTask seenTask = new SeenTask(this, new Product(jsonProduct), DBTask.ADD_SEEN_PRODUCT);
        seenTask.execute();
    }

    public void getAllSeenProduct() {
        SeenTask seenTask = new SeenTask(this, DBTask.GET_SEEN_PRODUCT);
        seenTask.execute();
    }

    public void setAllSeenProduct(List<Product> productList) {
        switch (activity.getClass().getSimpleName()) {
            case "SeenProductActivity":
                SeenProductActivity seenProductActivity = (SeenProductActivity)activity;
                seenProductActivity.gridView.setAdapter(new ProductGridViewAdapter(seenProductActivity,productList));
                break;
        }
    }

    public void toggleFavorite(JSON_Product jsonProduct) {
        FavoriteTask favoriteTask = new FavoriteTask(this, new Product(jsonProduct), DBTask.TOGGLE_FAVORITE);
        favoriteTask.execute();
    }

    public void getAllFavoriteProduct() {
        FavoriteTask favoriteTask = new FavoriteTask(this, DBTask.GET_FAVORITE_PRODUCT);
        favoriteTask.execute();
    }

    public void setAllFavoriteProduct(List<Product> productList) {
        switch (activity.getClass().getSimpleName()) {
            case "FavoriteProductActivity":
                FavoriteProductActivity favoriteProductActivity = (FavoriteProductActivity)activity;
                favoriteProductActivity.gridView.setAdapter(new ProductGridViewAdapter(favoriteProductActivity,productList));
                break;
        }
    }
}
