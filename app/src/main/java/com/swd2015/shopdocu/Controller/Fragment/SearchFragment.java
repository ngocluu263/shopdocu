package com.swd2015.shopdocu.Controller.Fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.swd2015.shopdocu.Controller.Activity.HomePageActivity;
import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.Controller.Util.ProgressDialogTask;
import com.swd2015.shopdocu.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    private     String                      searchedKey;
    private     int                         searchedCategoryID;
    private     ImageView                   buttonSearchView;
    public      GridView                    searchResultGridView;
    private     ProgressBar                 searchResultProgressBar;
    public      ArrayList<String>           listProductsName;
    public      AutoCompleteTextView        searchTextView;
    private     Spinner                     categorySpinner;
    public      Spinner                     orderBySpinner;
    public      TextView                    productFoundResult;
    public      TextView                    productNotFoundTV_1;
    public      TextView                    productNotFoundTV_2;
    private     ProgressDialog              progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                                        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        //Initialization
        listProductsName = new ArrayList<String>();

        //Initialization TextView and Spinner to set data for Search function
        searchTextView = (AutoCompleteTextView) view.findViewById(R.id.input_key_search_bar);

        //regionchange title,add back button
        HomePageActivity activity=(HomePageActivity)getActivity();
        android.support.v7.app.ActionBar actionBar=activity.actionBar;
        ActionBarDrawerToggle toggle=activity.actionBarDrawerToggle;
        toggle.setDrawerIndicatorEnabled(false);
        actionBar.setTitle("Tìm kiếm");
        actionBar.setHomeButtonEnabled(true);
        //endregion

        //Initialization ProductService
        final ProductService productService = new ProductService(this);

        //Call getAllProduct function to get list product
        // and set ProductName to String[] Products (for SEARCH SUGGESTION)
        productService.getAllProduct();

        //Initialization Category Spinner, OrderBySpinner,Grid View, Show Search Results
        categorySpinner = (Spinner) view.findViewById(R.id.category_spinner);
        orderBySpinner = (Spinner) view.findViewById(R.id.orderBy_spinner);
        searchResultGridView = (GridView) view.findViewById(R.id.search_results_grid_view);
        //searchResultProgressBar = (ProgressBar) view.findViewById(R.id.search_results_progress_bar);

        //Initialization Text view notify that product not found
        productFoundResult = (TextView) view.findViewById(R.id.product_found_result_text_view);
        productNotFoundTV_1 = (TextView) view.findViewById(R.id.product_not_found_text_view_1);
        productNotFoundTV_2 = (TextView) view.findViewById(R.id.product_not_found_text_view_2);

        //Initialization button Search and set OnClick() function
        buttonSearchView = (ImageView) view.findViewById(R.id.button_search_view);
        buttonSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set searched key and category
                searchedKey = searchTextView.getText().toString();
                searchedCategoryID = categorySpinner.getSelectedItemPosition();

                //Check searched key if not blank -> call search function
                if (!searchedKey.equals("")) {
                    for (int i = 0; i < searchedKey.length(); i++){
                        searchedKey = searchedKey.replace(" ", "%20");
                    }

                    progressDialog = ProgressDialog.show(getActivity(), "Thông tin", " Vui lòng chờ!");
                    new Thread(new Runnable() {
                        @Override
                        public void run()
                        {
                            try{
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        searchResultGridView.setVisibility(View.VISIBLE);
                                        productNotFoundTV_1.setVisibility(View.INVISIBLE);
                                        productNotFoundTV_2.setVisibility(View.INVISIBLE);

                                        //Call search function
                                        productService.getSearchedProducts(searchedKey, searchedCategoryID);
                                    }
                                });
                                Thread.sleep(1000);
                                progressDialog.dismiss();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });

        return view;
    }
}
