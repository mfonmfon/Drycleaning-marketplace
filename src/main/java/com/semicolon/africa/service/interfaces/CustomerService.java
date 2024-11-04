package com.semicolon.africa.service.interfaces;

import com.semicolon.africa.DTOs.request.CustomerLoginRequest;
import com.semicolon.africa.DTOs.request.CustomerRegistrationRequest;
import com.semicolon.africa.DTOs.request.CustomerSendOrderRequest;
import com.semicolon.africa.DTOs.request.CustomerUpdateOrderRequest;
import com.semicolon.africa.DTOs.response.*;
import com.semicolon.africa.data.model.Customer;

import java.util.List;

public interface CustomerService {
    CustomerLoginResponse login(CustomerLoginRequest customerLoginRequest);

    CustomerRegistrationResponse register(CustomerRegistrationRequest customerRegistrationRequest);

    CustomerSendOrderResponse sendOrder(CustomerSendOrderRequest cuatomerSendOrderRequest);

    CustomerUpdateOrderResponse updateOrder(CustomerUpdateOrderRequest cuatomerUpdateOrderRequest);

    DeleteOrderResponse deleteOrder(Long id);

    Long countAllOrders();

    List<Customer> getAllCustomers();

    Customer findCustomerById(Long id);

    List<Customer> findCustomersByName(String fullName);


}
