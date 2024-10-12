package com.semicolon.africa.service;

import com.semicolon.africa.data.model.Customer;
import com.semicolon.africa.data.repository.CustomerRepository;
import com.semicolon.africa.dto.request.CustomerSendsAnOrderRequest;
import com.semicolon.africa.dto.request.LoginCustomerRequest;
import com.semicolon.africa.dto.request.SignupCustomerRequest;
import com.semicolon.africa.dto.request.UpdateCustomerOrderRequest;
import com.semicolon.africa.dto.response.CustomerSendsAnOrderResponse;
import com.semicolon.africa.dto.response.LoginCustomerResponse;
import com.semicolon.africa.dto.response.SignupCustomerResponse;
import com.semicolon.africa.dto.response.UpdateCustomersOrderResponse;
import com.semicolon.africa.exception.CustomerDoesNotExistException;
import com.semicolon.africa.exception.EmailAlreadyExistException;
import com.semicolon.africa.exception.EmptyFieldsInputException;
import com.semicolon.africa.exception.InvalidPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.semicolon.africa.utils.Mapper.*;

@Service
public class CustomerServiceImpl implements  CustomerService{

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
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

    @Override
    public CustomerSendsAnOrderResponse sendOrder(CustomerSendsAnOrderRequest sendAnOrderRequest) {
        Customer customer = new Customer();
        if (isValueNullOrEmpty(sendAnOrderRequest.getFullName())||
                isValueNullOrEmpty(sendAnOrderRequest.getPhoneNumber())||
                isValueNullOrEmpty(sendAnOrderRequest.getStreet())||
                isValueNullOrEmpty(sendAnOrderRequest.getCity())||
                isValueNullOrEmpty(sendAnOrderRequest.getCountry())||
                isValueNullOrEmpty(sendAnOrderRequest.getDetailedInstructions())){
            throw new EmptyFieldsInputException("please fill all the fields before proceeding to the next");

        }
        customerRepository.save(customer);
        customerSendsOrderRequestMap(sendAnOrderRequest, customer);
        return mapSendOrderResponse(sendAnOrderRequest, customer);
    }

    @Override
    public UpdateCustomersOrderResponse updateOrder(UpdateCustomerOrderRequest updateCustomerOrderRequest) {
        Customer customer = findCustomerById(updateCustomerOrderRequest.getId());
        updateRequestMapping(updateCustomerOrderRequest, customer);
        if (isValueNullOrEmpty(updateCustomerOrderRequest.getFullName())||
                isValueNullOrEmpty(updateCustomerOrderRequest.getPhoneNumber())||
                isValueNullOrEmpty(updateCustomerOrderRequest.getStreet())||
                isValueNullOrEmpty(updateCustomerOrderRequest.getCity())||
                isValueNullOrEmpty(updateCustomerOrderRequest.getCity())||
                isValueNullOrEmpty(updateCustomerOrderRequest.getDetailedInstructions())){
            throw new EmptyFieldsInputException("Please input all fields");
        }
        customerRepository.save(customer);
        return mapUpdateOrderResponse(updateCustomerOrderRequest, customer);
    }

    private Customer findCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(()->new CustomerDoesNotExistException("Customer id does not exist"));
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

/**
 * Docker, Kubernates, k9s
 *
 * */
}


