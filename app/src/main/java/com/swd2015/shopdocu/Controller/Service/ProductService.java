package com.swd2015.shopdocu.Controller.Service;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.BaseAdapter;

import com.swd2015.shopdocu.Controller.Activity.MainActivity;
import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.JSON.Task.JSONProductTask;
import com.swd2015.shopdocu.Controller.JSON.Util.JSONTask;
import com.swd2015.shopdocu.Ga.ShowSearchedResultAdapter;
import com.swd2015.shopdocu.Minh.BanGheCapheFragment;
import com.swd2015.shopdocu.Minh.DienTuFragment;
import com.swd2015.shopdocu.Minh.GiaDinhFragment;
import com.swd2015.shopdocu.Minh.HomePageMainContentFragment;
import com.swd2015.shopdocu.Minh.KhacFragment;
import com.swd2015.shopdocu.Minh.KhachSanFragment;
import com.swd2015.shopdocu.Minh.ProductAdapter;
import com.swd2015.shopdocu.Minh.ProductForAdapter;
import com.swd2015.shopdocu.Minh.QuanAnFragment;
import com.swd2015.shopdocu.Minh.VanPhongFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Quang on 20/11/2015.
 */
public class ProductService {
    Activity activity;
    BaseAdapter baseAdapter;
    Fragment fragment;
    android.support.v4.app.Fragment supportv4Fragment;

    public ProductService(Activity activity, Fragment fragment){
        this.fragment=fragment;
        this.activity = activity;
    }

    public ProductService(Activity activity){
        this.activity=activity;
    }

    public ProductService( Fragment fragment){
        this.fragment=fragment;
    }
    public ProductService(  android.support.v4.app.Fragment supportv4Fragment){
        this.supportv4Fragment=supportv4Fragment;
    }

    public ProductService(BaseAdapter baseAdapter){
        this.baseAdapter = baseAdapter;
    }

    public void getAllProduct(){
        JSONProductTask jsonTask = new JSONProductTask(this, JSONTask.GET_ALL_PRODUCT);
        jsonTask.execute();
    }

    public void setAllProduct(ArrayList<JSON_Product> productList){
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

    public void getSearchedProducts( int categoryID){
        JSONProductTask jsonTask = new JSONProductTask(this,
                JSONTask.GET_SEARCHED_PRODUCTS, String.valueOf(categoryID));
        jsonTask.execute();
    }


    public void setSearchedProducts(ArrayList<JSON_Product> productList){

        if (supportv4Fragment!=null){
            switch (supportv4Fragment.getClass().getSimpleName()){
                case "BanGheCapheFragment":{
                    BanGheCapheFragment banGheCapheFragment= (BanGheCapheFragment) supportv4Fragment;
                    ShowSearchedResultAdapter adapter=new ShowSearchedResultAdapter(
                            banGheCapheFragment.getActivity().getApplicationContext(),productList);
                    banGheCapheFragment.gridResult.setAdapter(adapter);
                    break;
                }
                case "DienTuFragment":{
                    DienTuFragment dienTuFragment= (DienTuFragment) supportv4Fragment;
                    ShowSearchedResultAdapter adapter=new ShowSearchedResultAdapter(
                            dienTuFragment.getActivity().getApplicationContext(),productList);
                    dienTuFragment.gridResult.setAdapter(adapter);
                    break;
                }
                case "GiaDinhFragment":{
                    GiaDinhFragment giaDinhFragment= (GiaDinhFragment) supportv4Fragment;
                    ShowSearchedResultAdapter adapter=new ShowSearchedResultAdapter(
                            giaDinhFragment.getActivity().getApplicationContext(),productList);
                    giaDinhFragment.gridResult.setAdapter(adapter);
                    break;
                }
                case "KhachSanFragment":{
                    KhachSanFragment khachSanFragment= (KhachSanFragment) supportv4Fragment;
                    ShowSearchedResultAdapter adapter=new ShowSearchedResultAdapter(
                            khachSanFragment.getActivity().getApplicationContext(),productList);
                    khachSanFragment.gridResult.setAdapter(adapter);
                    break;
                }
                case "QuanAnFragment":{
                    QuanAnFragment quanAnFragment= (QuanAnFragment) supportv4Fragment;
                    ShowSearchedResultAdapter adapter=new ShowSearchedResultAdapter(
                            quanAnFragment.getActivity().getApplicationContext(),productList);
                    quanAnFragment.gridResult.setAdapter(adapter);
                    break;
                }
                case "VanPhongFragment":{
                    VanPhongFragment vanPhongFragment= (VanPhongFragment) supportv4Fragment;
                    ShowSearchedResultAdapter adapter=new ShowSearchedResultAdapter(
                            vanPhongFragment.getActivity().getApplicationContext(),productList);
                    vanPhongFragment.gridResult.setAdapter(adapter);
                    break;
                }
                case "KhacFragment":{
                    KhacFragment khacFragment= (KhacFragment) supportv4Fragment;
                    ShowSearchedResultAdapter adapter=new ShowSearchedResultAdapter(
                            khacFragment.getActivity().getApplicationContext(),productList);
                    khacFragment.gridResult.setAdapter(adapter);
                    break;
                }
            }
        }//end if supportV4Fragment !=null
    }

    public void getNewProducts(){
        JSONProductTask jsonTask = new JSONProductTask(this, JSONTask.GET_NEW_PRODUCTS);
        jsonTask.execute();
    }

    public void setNewProducts(ArrayList<JSON_Product> productList){
        HomePageMainContentFragment homePageFragment=(HomePageMainContentFragment) supportv4Fragment;
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

    public void getHotProduct(){
        JSONProductTask jsonTask = new JSONProductTask(this, JSONTask.GET_HOT_PRODUCTS);
        jsonTask.execute();
    }

    public void setHotProduct(ArrayList<JSON_Product> productList){
        HomePageMainContentFragment homePageFragment2=(HomePageMainContentFragment) supportv4Fragment;
        homePageFragment2.listDataHotProduct= new ArrayList<ProductForAdapter>();
        for(JSON_Product product:productList ){
            homePageFragment2.hotProductForAdapter
                    =new ProductForAdapter(formatName(product.getName())
                    , formatPrice(product.getPrice())
                    ,product.getImage().get(0).toString());
            homePageFragment2.listDataHotProduct.add(homePageFragment2.hotProductForAdapter);
        }
        homePageFragment2.hotProductAdapter=
                new ProductAdapter(homePageFragment2.getActivity().getApplicationContext()
                        ,homePageFragment2.listDataHotProduct);
        homePageFragment2.layoutManager2=new LinearLayoutManager(homePageFragment2.getActivity());
        homePageFragment2.layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        homePageFragment2.recyclerViewHotProduct.setLayoutManager(homePageFragment2.layoutManager2);
        homePageFragment2.recyclerViewHotProduct.setAdapter(homePageFragment2.hotProductAdapter);
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


