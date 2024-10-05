package com.semicolon.africa.service;

import com.semicolon.africa.data.model.Customer;
import com.semicolon.africa.data.repository.CustomerRepository;
import com.semicolon.africa.dto.request.LoginCustomerRequest;
import com.semicolon.africa.dto.request.SignupCustomerRequest;
import com.semicolon.africa.dto.response.LoginCustomerResponse;
import com.semicolon.africa.dto.response.SignupCustomerResponse;
import com.semicolon.africa.exception.CustomerDoesNotExistException;
import com.semicolon.africa.exception.EmailAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements  CustomerService{

    @Autowired
    private  CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public SignupCustomerResponse signupCustomer(SignupCustomerRequest signupCustomerRequest) {
        validateCustomerEmail(signupCustomerRequest.getEmail());
        Customer customer = new Customer();
        customer.setFullName(signupCustomerRequest.getFullName());
        customer.setGender(signupCustomerRequest.getGender().MALE);
        customer.setEmail(signupCustomerRequest.getEmail());
        customer.setPassword(passwordEncoder.encode(signupCustomerRequest.getPassword()));
        customerRepository.save(customer);
        SignupCustomerResponse signupCustomerResponse = new SignupCustomerResponse();
        signupCustomerResponse.setFullName(customer.getFullName());
        signupCustomerResponse.setGender(customer.getGender().MALE);
        signupCustomerResponse.setPassword(customer.getPassword());
        signupCustomerResponse.setMessage("Successfully registered");
        return signupCustomerResponse;
    }

    private void validateCustomerEmail(String email) {
        boolean isCustomerEmailExist = customerRepository.existsByEmail(email);
        if (isCustomerEmailExist){
            throw new EmailAlreadyExistException("Email already exists");
        }
    }

    @Override
    public LoginCustomerResponse loginCustomer(LoginCustomerRequest loginCustomerRequest) {
        Customer customer = findCustomerByEmail(loginCustomerRequest.getEmail());
        customer.setEmail(loginCustomerRequest.getEmail());
        customer.setPassword(loginCustomerRequest.getPassword());
        customerRepository.save(customer);
        LoginCustomerResponse loginCustomerResponse = new LoginCustomerResponse();
        loginCustomerResponse.setEmail(customer.getEmail());
        loginCustomerResponse.setPassword(customer.getPassword());
        loginCustomerResponse.setLoggedIn(true);
        loginCustomerResponse.setMessage("You're logged in");
        return loginCustomerResponse;
    }

    private Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email)
                .orElseThrow(()-> new CustomerDoesNotExistException("Customer does not exist"));
    }
}
