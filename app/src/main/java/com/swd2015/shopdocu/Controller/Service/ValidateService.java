package com.swd2015.shopdocu.Controller.Service;

/**
 * Created by SherHolmes
 * name
 * email
 * password
 * address
 * dob
 * phone
 */

public class ValidateService {

    //validate Name
    public String checkName(String name){
        String pattern = "^([^\\s[^[a-zA-Z0-9]]]([a-zA-Z0-9]*)\\s*)*([^\\s])$";
        if (name == null || name.length() > 50 || name.length() == 0){
            return "Name is invalid";
        } else if (name.matches(pattern)){
            return "Name is valid";
        } else return "Name is invalid";
    }

    //validate Email
    public String checkEmail(String email){
//        String pattern = "^[A-Za-z0-9]+@[A-Za-z0-9]+\\\\.[A-Za-z0-9]+(\\\\.[A-Za-z0-9])*$";
        String pattern = "^[^\\s]*([0-9a-zA-Z_])*[^.](@{1})[^\\s]*[^.]([a-z0-9A-Z])*[^\\s]*[.{1}][^\\s]*[^.]([a-z0-9A-Z])*[^\\s]$";
        if (email == null || email.length() == 0 || email.length() > 50){
            return "Email is invalid";
        } else if (email.matches(pattern)){
            return "Email is valid";
        } else return "Email is invalid";
    }

    //validate Password
    public String checkPassword(String password){
        String pattern = "^[^\\s]*$";
        if (password == null || password.length() == 0){
            return "Password is invalid";
        } else if (password.matches(pattern) && password.length() > 7 && password.length() < 17){
            return "Password is valid";
        } else return "Password is invalid";
    }

    //validate Address
    public String checkAddress(String address){
//        String pattern = "^([^\\s[^[a-zA-Z0-9]]])* ((\\s [a-zA-Z0-9]/,)*)* ([^\\s])$";
        if (address == null || address.length() == 0){
            return  "Address is invalid";
        } else if (address.length() < 250){
            return "Address is valid";
        } else return "Address is invalid";
    }

    //validate dob
    public String checkDateOfBirth(String dob){
        String pattern = "^([0-9]{1,2}[/-]{1}){2}[0-9]{4}$";
        if (dob == null || dob.length() == 0 || dob.length() > 10){
            return "Date of birth is invalid";
        } else if (dob.matches(pattern)){
            return "Date of birth is valid";
        } else
            return "Date of birth is invalid";
    }

    //validate phone
    public String checkPhone(String phone){
        String pattern = "^[0-9]*$";
        if (phone == null || phone.length() == 0 || phone.length() > 20){
            return  "Phone number is invalid";
        } else if (phone.matches(pattern)){
            return "Phone number is valid";
        } else return "Phone number is invalid";
    }

    //validate input dob
    public String formatDOB(String dob){


        return dob;
    }
}
