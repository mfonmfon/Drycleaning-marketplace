package com.semicolon.africa.service;

import com.semicolon.africa.dto.request.LoginCustomerRequest;
import com.semicolon.africa.dto.request.SignupCustomerRequest;
import com.semicolon.africa.dto.response.LoginCustomerResponse;
import com.semicolon.africa.dto.response.SignupCustomerResponse;

public interface CustomerService {

    SignupCustomerResponse signupCustomer(SignupCustomerRequest signupCustomerRequest);

    LoginCustomerResponse loginCustomer(LoginCustomerRequest loginCustomerRequest);

}
