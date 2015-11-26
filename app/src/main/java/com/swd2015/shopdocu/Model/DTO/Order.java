package com.swd2015.shopdocu.Model.DTO;

import java.util.Date;

/**
 * Created by Quang on 14-Nov-15.
 */
public class Order {
    private int ID;
    private Integer customerID;
    private Integer employeeID;
    private Date createDate;
    private Integer status;
    private String address;
    private Integer total;

    public Order() {
    }

    public Order(int ID, Integer customerID, Integer employeeID, Date createDate, Integer status, String address, Integer total) {
        this.setID(ID);
        this.setCustomerID(customerID);
        this.setEmployeeID(employeeID);
        this.setCreateDate(createDate);
        this.setStatus(status);
        this.setAddress(address);
        this.setTotal(total);
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
