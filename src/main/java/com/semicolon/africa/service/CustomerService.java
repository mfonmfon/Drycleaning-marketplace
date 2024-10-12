package com.semicolon.africa.service;

import com.semicolon.africa.dto.request.CustomerSendsAnOrderRequest;
import com.semicolon.africa.dto.request.LoginCustomerRequest;
import com.semicolon.africa.dto.request.SignupCustomerRequest;
import com.semicolon.africa.dto.request.UpdateCustomerOrderRequest;
import com.semicolon.africa.dto.response.CustomerSendsAnOrderResponse;
import com.semicolon.africa.dto.response.LoginCustomerResponse;
import com.semicolon.africa.dto.response.SignupCustomerResponse;
import com.semicolon.africa.dto.response.UpdateCustomersOrderResponse;

public interface CustomerService {

    SignupCustomerResponse signupCustomer(SignupCustomerRequest signupCustomerRequest);

    LoginCustomerResponse loginCustomer(LoginCustomerRequest loginCustomerRequest);

    CustomerSendsAnOrderResponse sendOrder(CustomerSendsAnOrderRequest sendAnOrderRequest);

    UpdateCustomersOrderResponse updateOrder(UpdateCustomerOrderRequest updateCustomerOrderRequest);

}
