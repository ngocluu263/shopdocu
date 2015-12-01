package com.swd2015.shopdocu.Ga;

import android.app.Activity;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private String                      searchedKey;
    private int                         searchedCategoryID;
    private ImageView                   buttonSearchView;
    public GridView                     searchResultGridView;
    private ProgressBar                 searchResultProgressBar;
    public ArrayList<String>            listProductsName;
    public AutoCompleteTextView         searchTextView;
    public Spinner                      orderBySpinner;
    public TextView                     productNotFoundTV_1;
    public TextView                     productNotFoundTV_2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                                        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        //Initialization
        listProductsName = new ArrayList<String>();

        //Initialization TextView and Spinner to set data for Search function
        searchTextView = (AutoCompleteTextView) view.findViewById(R.id.input_key_search_bar);

        //Initialization ProductService
        final ProductService productService = new ProductService(this);

        //Call getAllProduct function to get list product
        // and set ProductName to String[] Products (for SEARCH SUGGESTION)
        productService.getAllProduct();

        //Initialization and add item to Category Spinner
        final Spinner categorySpinner = (Spinner) view.findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                                                            getActivity(),
                                                            R.array.category_spinner,
                                                            android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        //Initialization and add item to OrderBy Spinner
        orderBySpinner = (Spinner) view.findViewById(R.id.orderBy_spinner);
        ArrayAdapter<CharSequence> orderByAdapter = ArrayAdapter.createFromResource(
                                                            getActivity(),
                                                            R.array.orderBy_spinner,
                                                            android.R.layout.simple_spinner_item);
        orderByAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderBySpinner.setAdapter(orderByAdapter);

        //Initialization Grid view for Show Search Results
        searchResultGridView = (GridView) view.findViewById(R.id.search_results_grid_view);
        searchResultProgressBar = (ProgressBar) view.findViewById(R.id.search_results_progress_bar);

        //Initialization Text view notify that product not found
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
                        searchResultGridView.setVisibility(View.VISIBLE);
                        searchResultProgressBar.setVisibility(View.VISIBLE);
                        productNotFoundTV_1.setVisibility(View.INVISIBLE);
                        productNotFoundTV_2.setVisibility(View.INVISIBLE);

                        //Call search function
                        productService.getSearchedProducts(searchedKey, searchedCategoryID);
                }
                searchResultProgressBar.setVisibility(View.GONE);
            }
        });

        return view;
    }
}
