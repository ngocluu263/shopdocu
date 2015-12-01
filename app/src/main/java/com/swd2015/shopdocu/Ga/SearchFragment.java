package com.swd2015.shopdocu.Ga;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.R;

public class SearchFragment extends Fragment {
    private static String               searchedKey;
    private static int                  searchedCategoryID;
    private ImageView                   buttonSearchView;
    private Spinner                     categorySpinner;
    private Spinner                     orderBySpinner;
    public GridView                     searchResultGridView;
    private ProgressBar                 searchResultProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                                        Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);

        categorySpinner = (Spinner) v.findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                getActivity().getApplicationContext(),
                R.array.category_spinner,
                android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        orderBySpinner = (Spinner) v.findViewById(R.id.orderBy_spinner);
        ArrayAdapter<CharSequence> orderByAdapter = ArrayAdapter.createFromResource(
                                                            getActivity().getApplicationContext(),
                                                            R.array.orderBy_spinner,
                                                            android.R.layout.simple_spinner_item);
        orderByAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderBySpinner.setAdapter(orderByAdapter);
        final SearchFragment that = this;
        buttonSearchView = (ImageView) v.findViewById(R.id.button_search_view);
        buttonSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView searchTextView = (TextView) v.findViewById(R.id.input_key_search_bar);
                searchedKey = searchTextView.getText().toString();

                Spinner categorySpinner = (Spinner) v.findViewById(R.id.category_spinner);
                searchedCategoryID = categorySpinner.getSelectedItemPosition();


                if (!searchedKey.equals("")) {
                    searchResultGridView = (GridView) v.findViewById(R.id.search_results_grid_view);
                    searchResultProgressBar = (ProgressBar)
                            v.findViewById(R.id.search_results_progress_bar);
                    searchResultGridView.setVisibility(View.VISIBLE);
                    searchResultProgressBar.setVisibility(View.VISIBLE);

                    //Call service
                    ProductService productService = new ProductService(getActivity());
                    productService.getSearchedProducts(searchedKey, searchedCategoryID);
                } else {
                    TextView productNotFoundTextView_1 = (TextView) v.findViewById(
                                                                R.id.product_not_found_text_view_1);
                    TextView productNotFoundTextView_2 = (TextView) v.findViewById(
                                                                R.id.product_not_found_text_view_2);
                    productNotFoundTextView_1.setText(R.string.product_not_found);
                    productNotFoundTextView_2.setText(searchedKey);
                    productNotFoundTextView_1.setVisibility(View.VISIBLE);
                    productNotFoundTextView_2.setVisibility(View.VISIBLE);
                }
                searchResultProgressBar.setVisibility(View.GONE);
            }
        });

        return v;
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_search);
//
//        categorySpinner = (Spinner) findViewById(R.id.category_spinner);
//        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
//                this, R.array.category_spinner, android.R.layout.simple_spinner_item);
//        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        categorySpinner.setAdapter(categoryAdapter);
//
//        orderBySpinner = (Spinner) findViewById(R.id.orderBy_spinner);
//        ArrayAdapter<CharSequence> orderByAdapter = ArrayAdapter.createFromResource(
//                this, R.array.orderBy_spinner, android.R.layout.simple_spinner_item);
//        orderByAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        orderBySpinner.setAdapter(orderByAdapter);
//
//        final SearchFragment that = this;
//        buttonSearchView = (ImageView) findViewById(R.id.button_search_view);
//        buttonSearchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView searchTextView = (TextView) findViewById(R.id.input_key_search_bar);
//                searchedKey = searchTextView.getText().toString();
//
//                Spinner categorySpinner = (Spinner) findViewById(R.id.category_spinner);
//                searchedCategoryID = categorySpinner.getSelectedItemPosition();
//
//
//                if (!searchedKey.equals("")) {
//                    searchResultGridView = (GridView) findViewById(R.id.search_results_grid_view);
//                    searchResultProgressBar = (ProgressBar)
//                                                    findViewById(R.id.search_results_progress_bar);
//                    searchResultGridView.setVisibility(View.VISIBLE);
//                    searchResultProgressBar.setVisibility(View.VISIBLE);
//
//                    //Call service
//                    ProductService productService = new ProductService(that);
//                    productService.getSearchedProducts(searchedKey, searchedCategoryID);
//                } else {
//                    TextView productNotFoundTextView_1 = (TextView) findViewById(
//                                                                R.id.product_not_found_text_view_1);
//                    TextView productNotFoundTextView_2 = (TextView) findViewById(
//                                                                R.id.product_not_found_text_view_2);
//                    productNotFoundTextView_1.setText(R.string.product_not_found);
//                    productNotFoundTextView_2.setText(searchedKey);
//                    productNotFoundTextView_1.setVisibility(View.VISIBLE);
//                    productNotFoundTextView_2.setVisibility(View.VISIBLE);
//                }
//                searchResultProgressBar.setVisibility(View.GONE);
//            }
//        });
//    }
}
