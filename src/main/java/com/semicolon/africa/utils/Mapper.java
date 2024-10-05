package com.semicolon.africa.utils;

import com.semicolon.africa.data.model.Customer;
import com.semicolon.africa.dto.request.SignupCustomerRequest;

public class Mapper {
    public static void map(SignupCustomerRequest signupCustomerRequest, Customer customer) {
        customer.setFullName(signupCustomerRequest.getFullName());
        customer.setGender(signupCustomerRequest.getGender().MALE);
        customer.setEmail(signupCustomerRequest.getEmail());
    }


}
