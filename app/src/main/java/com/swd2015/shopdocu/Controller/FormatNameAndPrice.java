package com.swd2015.shopdocu.Controller;

import java.text.DecimalFormat;

/**
 * Created by Minh on 12/3/2015.
 */
public class FormatNameAndPrice {

    public static String  FormatName(String name, int maxLength){
        if (name.length()>=maxLength){
            name = name.substring(0,maxLength-2)+"...";
        }
        return name;

    }

    public static String FormatPrice(double price){
        DecimalFormat formatter = new DecimalFormat("#,###");
        String productPrice = formatter.format(price);
        productPrice=productPrice.replace(',','.');
        return productPrice +" VND";
    }

}
