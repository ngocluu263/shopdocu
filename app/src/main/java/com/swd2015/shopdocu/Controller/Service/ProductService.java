package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;
import android.view.View;
import android.widget.BaseAdapter;

import com.swd2015.shopdocu.Controller.Activity.MainActivity;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONProductTask;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Ga.SearchActivity;
import com.swd2015.shopdocu.Ga.ShowSearchedResultAdapter;

import java.util.ArrayList;

/**
 * Created by Quang on 20/11/2015.
 */
public class ProductService {
    Activity activity;
    BaseAdapter baseAdapter;

    public ProductService(Activity activity){
        this.activity = activity;
    }

    public ProductService(BaseAdapter baseAdapter){
        this.baseAdapter = baseAdapter;
    }

    public void getAllProduct(){
        JSONProductTask jsonTask = new JSONProductTask(this, JSONTask.GET_ALL_PRODUCT);
        jsonTask.execute();
    }

//    public void setAllProduct(ArrayList<JSON_Product> productList){
//        switch (activity.getClass().getSimpleName()){
//            case "MainActivity":
//                MainActivity mainActivity = (MainActivity) activity;
//
//                // Ví dụ: get product đầu tiên và set Description của nó vào MainActivity
//                mainActivity.example = productList.get(0).getDescription();
//                break;
//        }
//    }
    public void setAllProduct(ArrayList<JSON_Product> productList){
        switch (activity.getClass().getSimpleName()){
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
        switch (activity.getClass().getSimpleName()){
            case "SearchActivity":
                SearchActivity searchActivity = (SearchActivity) activity;
                searchActivity.searchResultGridView.setVisibility(View.VISIBLE);
                System.out.println("QWERT " + productList.size());
                searchActivity.searchResultGridView.setAdapter(new ShowSearchedResultAdapter(searchActivity, productList));
//                ShowSearchedResultAdapter showSearchedResultAdapter =
//                                                            (ShowSearchedResultAdapter) baseAdapter;
//
//                showSearchedResultAdapter.searchedProductList = productList;
//                System.out.println("PDLS: " + productList.size());
//                System.out.println("SPRLS: " + showSearchedResultAdapter.searchedProductList.size());
//                for (int i = 0; i < productList.size(); ++i) {
//                    showSearchedResultAdapter.searchedProductName = productList.get(i).getName();
//                    System.out.println("aaaaaSAFASFa = " + productList.get(i).getName());
//
//                    showSearchedResultAdapter.searchedProductImageURL =
//                                                productList.get(i).getImage().get(i).toString();
//                    System.out.println("ADFHG = " + productList.get(i).getImage().get(i).toString());
//
//                    showSearchedResultAdapter.searchedProductPrice =
//                             String.valueOf(productList.get(i).getPrice());
//                    System.out.println("Price = " + String.valueOf(productList.get(i).getPrice()));
//                }

                break;
        }
    }
}
