package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.BaseAdapter;

import com.swd2015.shopdocu.Controller.Activity.MainActivity;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Customer;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONCustomerTask;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONProductTask;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Ga.RequestSellFragment;
import com.swd2015.shopdocu.Ga.SearchFragment;
import com.swd2015.shopdocu.Ga.ShowSearchedResultAdapter;
import com.swd2015.shopdocu.R;
import com.swd2015.shopdocu.Ga.SearchFragment;
import com.swd2015.shopdocu.Minh.HomePage_Fragment;
import com.swd2015.shopdocu.Minh.ProductAdapter;
import com.swd2015.shopdocu.Minh.ProductForAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Quang on 20/11/2015.
 */
public class ProductService{
    Activity activity;
    Context mContext;
    BaseAdapter baseAdapter;
    Fragment fragment;

    public ProductService(Activity activity, Fragment fragment){
        this.fragment=fragment;
        this.activity = activity;
    }
    public ProductService(Fragment fragment) {
        this.fragment = fragment;
    }
    public ProductService(Activity activity) {
            this.activity = activity;
    }

    public ProductService(BaseAdapter baseAdapter){
        this.baseAdapter = baseAdapter;
    }

    public void getAllProduct(){
        JSONProductTask jsonTask = new JSONProductTask(this, JSONTask.GET_ALL_PRODUCT);
        jsonTask.execute();
    }

    public void setAllProduct(ArrayList<JSON_Product> productList){
        switch (fragment.getClass().getSimpleName()){
            case "SearchFragment":
                SearchFragment searchFragment = (SearchFragment) fragment;

                //Set all ProductName to arrayList for search suggestion
                for (int i = 0; i < productList.size(); ++i) {
                    searchFragment.listProductsName.add(productList.get(i).getName());
                }
                searchFragment.searchTextView.setAdapter(new ArrayAdapter<String>(
                                            searchFragment.getActivity().getApplicationContext(),
                                            R.layout.list_searched_product_suggestion,
                                            searchFragment.listProductsName));

        switch (activity.getClass().getSimpleName()){
            case "MainActivity":
                MainActivity mainActivity = (MainActivity) activity;

                 //Ví dụ: get product đầu tiên và set Description của nó vào MainActivity
                mainActivity.example = productList.get(0).getDescription();
                break;
        }
        //HomePage_Fragment homePage_fragment=(HomePage_Fragment) fragment;
       // homePage_fragment.listProduct=productList;

    }

    public void getProductByID(int ID){
        JSONProductTask jsonTask = new JSONProductTask(this, JSONTask.GET_PRODUCT_BY_ID,
                                                                                String.valueOf(ID));
        jsonTask.execute();
    }

    public void setProduct(JSON_Product product){
        switch (activity.getClass().getSimpleName()){
            case "MainActivity":
                MainActivity mainActivity = (MainActivity) activity;
                mainActivity.product = product;
                break;
        }
    }

    public void getSearchedProducts(String productName, int categoryID){
        JSONProductTask jsonTask = new JSONProductTask(this,
                        JSONTask.GET_SEARCHED_PRODUCTS, productName, String.valueOf(categoryID));
        jsonTask.execute();
    }

    public void setSearchedProducts(ArrayList<JSON_Product> productList){
        switch (fragment.getClass().getSimpleName()){
            case "SearchFragment":
                SearchFragment searchFragment = (SearchFragment) fragment;

                if (productList.size() != 0) {
                    int orderByID = searchFragment.orderBySpinner.getSelectedItemPosition();
                    if (orderByID == 0) {
                        Collections.sort(productList, new Comparator<JSON_Product>() {
                            @Override
                            public int compare(JSON_Product p1, JSON_Product p2) {
                                return p1.getPrice() - p2.getPrice(); // Ascending
                            }
                        });
                    } else if (orderByID == 1) {
                        Collections.sort(productList, new Comparator<JSON_Product>() {
                            @Override
                            public int compare(JSON_Product p1, JSON_Product p2) {
                                return p2.getPrice() - p1.getPrice(); // Descending
                            }
                        });
                    }

                    //Call adapter to show searched result to Grid View
                    searchFragment.searchResultGridView.setAdapter(
                                    new ShowSearchedResultAdapter(searchFragment.getActivity()
                                                                        .getApplicationContext(),
                                                                        productList));
                } else {
                    searchFragment.productNotFoundTV_1.setText(R.string.product_not_found_1);
                    searchFragment.productNotFoundTV_2.setText(R.string.product_not_found_2);
                    searchFragment.productNotFoundTV_1.setVisibility(View.VISIBLE);
                    searchFragment.productNotFoundTV_2.setVisibility(View.VISIBLE);
                    searchFragment.searchResultGridView.setVisibility(View.INVISIBLE);
                }
                //searchFragment.searchResultGridView.setAdapter(
                                      //  new ShowSearchedResultAdapter(searchFragment.getContext(), productList));
                break;
        }
    }

}
    public void getNewProducts(){
        JSONProductTask jsonTask = new JSONProductTask(this, JSONTask.GET_NEW_PRODUCTS);
        jsonTask.execute();
    }

    public void setNewProducts(ArrayList<JSON_Product> productList){
        HomePage_Fragment homePageFragment=(HomePage_Fragment) fragment;
        homePageFragment.listProduct=productList;

        homePageFragment.listDataNewProduct= new ArrayList<ProductForAdapter>();
        for(JSON_Product product:homePageFragment.listProduct ){
            homePageFragment.productForAdapter
                    =new ProductForAdapter(formatName(product.getName())
                    , formatPrice(product.getPrice())
                    ,product.getImage().get(0).toString());
            homePageFragment.listDataNewProduct.add(homePageFragment.productForAdapter);
        }

        //New product
        homePageFragment.newProductAdapter=
                new ProductAdapter(homePageFragment.getActivity().getApplicationContext()
                        ,homePageFragment.listDataNewProduct);
        homePageFragment.layoutManager=new LinearLayoutManager(homePageFragment.getActivity());
        homePageFragment.layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homePageFragment.recyclerView.setLayoutManager(homePageFragment.layoutManager);
        homePageFragment.recyclerView.setAdapter(homePageFragment.newProductAdapter);

        }

    private String formatName(String name) {
        if (name.length()>=15){
            name = name.substring(0,13)+"...";
        }
        return name;
    }

    private String formatPrice(int price) {
        double amount =price;
        DecimalFormat formatter = new DecimalFormat("#,###");
        String sPrice=formatter.format(amount);
        sPrice = sPrice.replace(',','.');
        return sPrice + " VND";
    }
    }
