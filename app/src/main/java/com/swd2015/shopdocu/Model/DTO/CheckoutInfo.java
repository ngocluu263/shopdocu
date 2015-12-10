/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swd2015.shopdocu.Model.DTO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Quang
 */
public class CheckoutInfo implements Serializable {
    @SerializedName("Customer")
    private Customer customer;
    @SerializedName("ListOrderDetail")
    private List<SoldOrderDetail> soldOrders;

    public CheckoutInfo() {
    }

    public CheckoutInfo(Customer customer, List<SoldOrderDetail> soldOrders) {
        this.customer = customer;
        this.soldOrders = soldOrders;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the soldOrders
     */
    public List<SoldOrderDetail> getSoldOrders() {
        return soldOrders;
    }

    /**
     * @param soldOrders the soldOrders to set
     */
    public void setSoldOrders(List<SoldOrderDetail> soldOrders) {
        this.soldOrders = soldOrders;
    }
    
    
}
