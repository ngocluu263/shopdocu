package com.swd2015.shopdocu.Controller.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.swd2015.shopdocu.R;

import java.util.List;

/**
 * Created by SherHolmes
 */
public class UserPurchaseAdapterSon extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public List<String> ProductName;
    static int pos;

    public UserPurchaseAdapterSon(Context context, List<String> productName){
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
            v = layoutInflater.inflate(R.layout.adapter_purchase_productname, null);

        } else {
            v = convertView;
        }

        TextView name = (TextView) v.findViewById(R.id.purchaseproductname);
//        if ( pos < ProductName.size()){
//
//        name.setText(ProductName.get(pos++));
//            ProductName.remove(pos);
//        }
        name.setText(ProductName.get(position));
//        ProductName.remove(position);
        return v;

    }
}
