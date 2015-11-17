package com.swd2015.shopdocu.Minh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.swd2015.shopdocu.R;

import java.util.List;

/**
 * Created by Minh on 11/17/2015.
 */
public class NavListAdapter extends ArrayAdapter<NavigationItem>{
    Context context;
    int resLayout;
    List<NavigationItem> listNavItems;

    public NavListAdapter(Context context, int resLayout, List<NavigationItem> listNavItems) {
        super(context, resLayout, listNavItems);

        this.context=context;
        this.resLayout=resLayout;
        this.listNavItems=listNavItems;
    }

    @SuppressLint("ViewHolder")@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(context,resLayout,null);

        TextView txtTitle=(TextView) v.findViewById(R.id.title);
        TextView txtSubtitle=(TextView) v.findViewById(R.id.subtitle);
        ImageView icon= (ImageView) v.findViewById(R.id.nav_icon);

        NavigationItem navItem=listNavItems.get(position);
        txtTitle.setText(navItem.getTitle());
        txtSubtitle.setText(navItem.getSubtitle());
        icon.setImageResource(navItem.getResIcon());

        return v;
    }
}
