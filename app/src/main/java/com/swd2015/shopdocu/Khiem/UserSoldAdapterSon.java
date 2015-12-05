package com.swd2015.shopdocu.Khiem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.swd2015.shopdocu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khiem on 12/3/2015.
 */
public class UserSoldAdapterSon extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public List<String> ProductName;
    static int pos;

    public UserSoldAdapterSon(Context context, List<String> productName){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.ProductName = productName;
        pos=0;
    }

    @Override
    public int getCount() {
        if (ProductName.size() != 0){
            return ProductName.size();
        }
        return 0;
    }
    @Override
    public Object getItem(int position) {
        return ProductName.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;

        if (convertView == null){
            v = layoutInflater.inflate(R.layout.adapter_sold_productname, null);

        } else {
            v = convertView;
        }

        TextView name = (TextView) v.findViewById(R.id.soldproductname);
//        if ( pos < ProductName.size()){
//
//        name.setText(ProductName.get(pos++));
////            ProductName.remove(pos);
//        }
        name.setText(ProductName.get(position));
//        System.out.println(ProductName.get(position));
        return v;
    }
}
