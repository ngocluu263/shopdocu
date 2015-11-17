package com.swd2015.shopdocu.Minh;

/**
 * Created by Minh on 11/17/2015.
 */
public class NavigationItem {
    private String title;
    private String subtitle;
    private int resIcon;

    public NavigationItem(String title,String subtitle, int resIcon){
        this.title=title;
        this.subtitle=subtitle;
        this.resIcon=resIcon;
    };

    public String getTitle(){
        return this.title;
    }

    public String getSubtitle(){
        return this.subtitle;
    }

    public int getResIcon(){
        return this.resIcon;
    }

    public void setTitle(String title){
        this.title=title;
    }
    public void setSubtitle(String subtitle){
        this.subtitle=subtitle;
    }

    public void setResIcon(int resIcon){
        this.resIcon=resIcon;
    }


}
