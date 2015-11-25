package com.swd2015.shopdocu.Ga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.swd2015.shopdocu.Controller.JSON.JSONObject.JSON_Product;
import com.swd2015.shopdocu.Controller.Service.ProductService;
import com.swd2015.shopdocu.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    public static String            searchedKey;
    public static int               searchedcategoryID;
    public ImageView                buttonSearchView;
    public Spinner                  categorySpinner;
    public Spinner                  orderBySpinner;
    public GridView                 searchResultGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        categorySpinner = (Spinner) findViewById(R.id.category_spinner);
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(
                this, R.array.category_spinner, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        orderBySpinner = (Spinner) findViewById(R.id.orderBy_spinner);
        ArrayAdapter<CharSequence> orderByAdapter = ArrayAdapter.createFromResource(
                this, R.array.orderBy_spinner, android.R.layout.simple_spinner_item);
        orderByAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderBySpinner.setAdapter(orderByAdapter);

        buttonSearchView = (ImageView) findViewById(R.id.button_search_view);
        final SearchActivity that = this;
        buttonSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchResultGridView = (GridView) findViewById(R.id.search_result_gridview);

//                TextView searchTextview = (TextView) findViewById(R.id.input_key_search_bar);
//                searchedKey = searchTextview.getText().toString();
//
//                Spinner categorySpinner = (Spinner) findViewById(R.id.category_spinner);
//                searchedcategoryID = categorySpinner.getSelectedItemPosition();
                ProductService productService = new ProductService(that);
                productService.getSearchedProducts("a", 27);
//                searchResultGridView.setVisibility(View.VISIBLE);
//                searchResultGridView.setAdapter(new ShowSearchedResultAdapter(getApplication()));
                    }
        });
    }
}
