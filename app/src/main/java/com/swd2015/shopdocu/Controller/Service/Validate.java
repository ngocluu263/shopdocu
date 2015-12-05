package com.swd2015.shopdocu.Controller.Service;

/**
 * Created by khiem on 12/5/2015.
 * name
 * email
 * password
 * address
 * dob
 * phone
 */

public class Validate {

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
        String pattern = "";
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
        String pattern = "";
        if (dob == null || dob.length() == 0 || dob.length() > 6){

        }
        return "Date of birth is invalid";
    }
}
