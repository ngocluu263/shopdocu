package com.swd2015.shopdocu.Ga;

import android.app.Fragment;
import android.os.Bundle;
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
import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.R;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    private String                      searchedKey;
    private int                         searchedCategoryID;
    private ImageView                   buttonSearchView;
    public GridView                     searchResultGridView;
    private ProgressBar                 searchResultProgressBar;
    public ArrayList<String>            listProductsName;
    public AutoCompleteTextView         searchTextView;
    private Spinner                     categorySpinner;
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

        //Initialization Category Spinner, OrderBySpinner,Grid View, Show Search Results
        categorySpinner = (Spinner) view.findViewById(R.id.category_spinner);
        orderBySpinner = (Spinner) view.findViewById(R.id.orderBy_spinner);
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
