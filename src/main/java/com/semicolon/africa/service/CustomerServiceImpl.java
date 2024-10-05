package com.semicolon.africa.service;

import com.semicolon.africa.data.model.Customer;
import com.semicolon.africa.data.repository.CustomerRepository;
import com.semicolon.africa.dto.request.LoginCustomerRequest;
import com.semicolon.africa.dto.request.SignupCustomerRequest;
import com.semicolon.africa.dto.response.LoginCustomerResponse;
import com.semicolon.africa.dto.response.SignupCustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements  CustomerService{

    @Autowired
    private  CustomerRepository customerRepository;



    @Override
    public SignupCustomerResponse signupCustomer(SignupCustomerRequest signupCustomerRequest) {
        Customer customer = new Customer();
        customer.setFullName(signupCustomerRequest.getFullName());
        customer.setGender(signupCustomerRequest.getGender().MALE);
        customer.setEmail(signupCustomerRequest.getEmail());
        customer.setPassword(signupCustomerRequest.getPassword());
        customerRepository.save(customer);
        SignupCustomerResponse signupCustomerResponse = new SignupCustomerResponse();
        signupCustomerResponse.setFullName(customer.getFullName());
        signupCustomerResponse.setGender(customer.getGender().MALE);
        signupCustomerResponse.setPassword(customer.getPassword());
        signupCustomerResponse.setMessage("Successfully registered");
        return signupCustomerResponse;
    }

    @Override
    public LoginCustomerResponse loginCustomer(LoginCustomerRequest loginCustomerRequest) {
        return null;
    }
}
