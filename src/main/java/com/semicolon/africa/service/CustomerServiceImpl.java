package com.semicolon.africa.service;

import com.semicolon.africa.data.model.Customer;
import com.semicolon.africa.data.repository.CustomerRepository;
import com.semicolon.africa.dto.request.LoginCustomerRequest;
import com.semicolon.africa.dto.request.SignupCustomerRequest;
import com.semicolon.africa.dto.response.LoginCustomerResponse;
import com.semicolon.africa.dto.response.SignupCustomerResponse;
import com.semicolon.africa.exception.CustomerDoesNotExistException;
import com.semicolon.africa.exception.EmailAlreadyExistException;
import com.semicolon.africa.exception.EmptyFieldsInputException;
import com.semicolon.africa.exception.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.semicolon.africa.utils.Mapper.map;

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
        if (isValueNullOrEmpty(signupCustomerRequest.getFullName())||
                isValueNullOrEmpty(signupCustomerRequest.getEmail()) ||
                isValueNullOrEmpty(signupCustomerRequest.getPassword())) {
            throw new EmptyFieldsInputException("Please enter all fields ");
        }
        map(signupCustomerRequest, customer);
        customer.setPassword(passwordEncoder.encode(signupCustomerRequest.getPassword()));
        customerRepository.save(customer);
        SignupCustomerResponse signupCustomerResponse = new SignupCustomerResponse();
        signupCustomerResponse.setFullName(customer.getFullName());
        signupCustomerResponse.setGender(customer.getGender().MALE);
        signupCustomerResponse.setPassword(customer.getPassword());
        signupCustomerResponse.setMessage("Successfully registered");
        return signupCustomerResponse;
    }

    private boolean isValueNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
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
        validateCustomerPassword(loginCustomerRequest.getPassword());
        customerRepository.save(customer);
        LoginCustomerResponse loginCustomerResponse = new LoginCustomerResponse();
        loginCustomerResponse.setEmail(customer.getEmail());
        loginCustomerResponse.setPassword(customer.getPassword());
        loginCustomerResponse.setLoggedIn(true);
        loginCustomerResponse.setMessage("You're logged in");
        return loginCustomerResponse;
    }

    private void validateCustomerPassword(String password) {
        boolean  isValidPassword = customerRepository
                .findCustomerByPassword(String.valueOf(password.matches(password)));
        if (!isValidPassword){
            throw new InvalidPasswordException("Wrong password or email");
        }
    }

    private Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email)
                .orElseThrow(()-> new CustomerDoesNotExistException("Customer does not exist"));
    }
}


