package com.swd2015.shopdocu.Minh;

/**
 * Created by Minh on 11/17/2015.
 */
public class NavigationItem {
    private String title;
    private int resIcon;

    public NavigationItem(String title, int resIcon){
        this.title=title;
        this.resIcon=resIcon;
    };

    public String getTitle(){
        return this.title;
    }


    public int getResIcon(){
        return this.resIcon;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setResIcon(int resIcon){
        this.resIcon=resIcon;
    }


}
