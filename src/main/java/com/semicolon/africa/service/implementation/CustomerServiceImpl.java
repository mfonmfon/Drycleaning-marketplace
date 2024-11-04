package com.semicolon.africa.service.implementation;

import com.semicolon.africa.DTOs.request.CustomerSendOrderRequest;
import com.semicolon.africa.DTOs.request.CustomerUpdateOrderRequest;
import com.semicolon.africa.DTOs.response.CustomerSendOrderResponse;
import com.semicolon.africa.DTOs.response.CustomerUpdateOrderResponse;
import com.semicolon.africa.DTOs.response.DeleteOrderResponse;
import com.semicolon.africa.data.model.Customer;
import com.semicolon.africa.data.repository.CustomerRepository;
import com.semicolon.africa.exception.CustomerNotFoundException;
import com.semicolon.africa.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public CustomerSendOrderResponse sendOrder(CustomerSendOrderRequest customerSendOrderRequest) {



        return null;
    }

    @Override
    public CustomerUpdateOrderResponse updateOrder(CustomerUpdateOrderRequest cuatomerUpdateOrderRequest) {
        return null;
    }

    @Override
    public DeleteOrderResponse deleteOrder(Long id) {
        return null;
    }

    @Override
    public Long countAllOrders() {
        return customerRepository.count();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findCustomersById(id)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
    }

    @Override
    public List<Customer> findCustomersByName(String fullName) {
        return customerRepository.findCustomersByName(fullName);
    }
}
