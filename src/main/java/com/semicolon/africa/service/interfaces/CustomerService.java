package com.semicolon.africa.service.interfaces;

import com.semicolon.africa.DTOs.request.*;
import com.semicolon.africa.DTOs.response.*;
import com.semicolon.africa.data.model.Customer;

import java.util.List;

public interface CustomerService {
    CustomerLoginResponse login(CustomerLoginRequest customerLoginRequest);

    CustomerRegistrationResponse register(CustomerRegistrationRequest customerRegistrationRequest);

    PlaceOrderResponse sendOrder(PlaceOrderRequest customerSendOrderRequest);

    CustomerUpdateOrderResponse updateOrder(CustomerUpdateOrderRequest customerUpdateOrderRequest);

    DeleteOrderResponse deleteOrder(Long id);

    Long countAllOrders();

    List<Customer> getAllCustomers();

    Customer findCustomerById(Long id);

    List<Customer> findCustomersByName(String fullName);


}
