package com.semicolon.africa.service;
import com.semicolon.africa.data.model.Customer;
import com.semicolon.africa.data.model.DryCleaner;
import com.semicolon.africa.data.model.Order;
import com.semicolon.africa.data.repository.CustomerRepository;
import com.semicolon.africa.data.repository.DryCleanerRepository;
import com.semicolon.africa.data.repository.OrderRepository;
import com.semicolon.africa.dto.request.CustomerSendsAnOrderRequest;
import com.semicolon.africa.dto.request.LoginCustomerRequest;
import com.semicolon.africa.dto.request.SignupCustomerRequest;
import com.semicolon.africa.dto.request.UpdateCustomerOrderRequest;
import com.semicolon.africa.dto.response.*;
import com.semicolon.africa.exception.*;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements  CustomerService{

    private final CustomerRepository customerRepository;


    private final PasswordEncoder passwordEncoder;

    private final OrderRepository orderRepository;

    private final OrderService orderService;
    private final DryCleanerRepository dryCleanerRepository;
    private final DryCleanerService dryCleanerService;

    @Override
    public SignupCustomerResponse signupCustomer(SignupCustomerRequest signupCustomerRequest) {
        validateCustomerPassword(signupCustomerRequest.getPassword());
        Customer customer = customerSignupRequest(signupCustomerRequest);
        if (isValueEmptyOrNull(signupCustomerRequest.getFullName())||
                isValueEmptyOrNull(signupCustomerRequest.getEmail())||
                isValueEmptyOrNull(signupCustomerRequest.getPhoneNumber())||
                isValueEmptyOrNull(signupCustomerRequest.getHomeAddress())||
                isValueEmptyOrNull(signupCustomerRequest.getPassword())){
            throw new EmptyFieldsInputException(" Must fill this field ");
        }
        if (!(signupCustomerRequest.getEmail().contains("@")|| signupCustomerRequest.getEmail().contains("."))){
            throw new InvalidEmailException("Invalid email format, email must include @ and .");
        }
        validateCustomerEmail(signupCustomerRequest.getEmail());
        customerRepository.save(customer);
        return getSignupCustomerResponse(customer);
    }

    private void validateCustomerEmailAddress(String email) {
        boolean isEmailExist = customerRepository.existsByEmail(email);
        if (isEmailExist){
            throw new EmailAlreadyExistException("Email already exist");
        }
    }

    private static SignupCustomerResponse getSignupCustomerResponse(Customer customer) {
        SignupCustomerResponse signupCustomerResponse = new SignupCustomerResponse();
        signupCustomerResponse.setFullName(customer.getFullName());
        signupCustomerResponse.setEmail(customer.getEmail());
        signupCustomerResponse.setPhoneNumber(customer.getPhoneNumber());
        signupCustomerResponse.setHomeAddress(customer.getHomeAddress());
        signupCustomerResponse.setPassword(customer.getPassword());
        signupCustomerResponse.setMessage("Successfully registered");
        return signupCustomerResponse;
    }

    private Customer customerSignupRequest(SignupCustomerRequest signupCustomerRequest) {
        Customer customer = new Customer();
        customer.setFullName(signupCustomerRequest.getFullName());
        customer.setEmail(signupCustomerRequest.getEmail());
        customer.setPhoneNumber(signupCustomerRequest.getPhoneNumber());
        customer.setHomeAddress(signupCustomerRequest.getHomeAddress());
        customer.setPassword(passwordEncoder.encode(signupCustomerRequest.getPassword()));
        return customer;
    }

    private boolean isValueEmptyOrNull(String value) {
        return value == null || value.trim().isEmpty();
    }
    @Override
    public LoginCustomerResponse loginCustomer(LoginCustomerRequest loginCustomerRequest) {

        Customer customer = new Customer();
        customer.setEmail(validateCustomerEmail(loginCustomerRequest.getEmail()));
        customer.setPassword(loginCustomerRequest.getPassword());
        if (!(loginCustomerRequest.getEmail().contains("@")|| loginCustomerRequest.getEmail().contains("."))){
            throw new InvalidEmailException("Invalid email");

        }
        customerRepository.save(customer);
        LoginCustomerResponse loginCustomerResponse = new LoginCustomerResponse();
        loginCustomerResponse.setEmail(customer.getEmail());
        loginCustomerResponse.setPassword((loginCustomerResponse.getPassword()));
        loginCustomerResponse.setLoggedIn(true);
        loginCustomerResponse.setMessage("Logged in successfully");
        return loginCustomerResponse;
    }

    private String validateCustomerEmail(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer == null){
            throw new CustomerNotFoundException("Customer not found ");
        }
        if (email == null || email.isEmpty()){
            throw new EmptyFieldsInputException("Email can not be empty");
        }
        return customer.getEmail();
    }

    private Customer validateCustomerPassword(String password) {
       return customerRepository.findCustomerByPassword(password)
               .orElseThrow(()-> new InvalidPasswordException("Password does not exist"));
    }

    @Override
    public CustomerSendsAnOrderResponse sendOrder(CustomerSendsAnOrderRequest sendAnOrderRequest) {
        Order order  =  orderService.findOrderBy(sendAnOrderRequest.getOrderId());
        if (order == null){
            throw new EmptyOrderException("No order was found ");
        }
        DryCleaner dryCleaner = dryCleanerService.findDryCleanerBy(sendAnOrderRequest.getDryCleanerId());
        if (dryCleaner == null){
            throw new NoDryCleanerWasFound("No dry cleaner was found ");
        }
        Customer customer = new Customer();
        customer.setEmail(sendAnOrderRequest.getEmail());
        customer.setPhoneNumber(sendAnOrderRequest.getPhoneNumber());
        customer.setHomeAddress(sendAnOrderRequest.getHomeAddress());
        List<Order> orderList = customer.getOrders();
        orderList.add(order);
        customerRepository.save(customer);
        CustomerSendsAnOrderResponse sendsAnOrderResponse = new CustomerSendsAnOrderResponse();
        sendsAnOrderResponse.setEmail(customer.getEmail());
        sendsAnOrderResponse.setPhoneNumber(customer.getPhoneNumber());
        sendsAnOrderResponse.setHomeAddress(customer.getHomeAddress());
        sendsAnOrderResponse.setMessage("Order sent ");
        return  sendsAnOrderResponse;
    }

    @Override
    public UpdateCustomersOrderResponse updateOrder(UpdateCustomerOrderRequest updateCustomerOrderRequest) {
        Customer customer = customerRepository.findCustomerById(updateCustomerOrderRequest.getId());
        customer.setEmail(updateCustomerOrderRequest.getEmail());
        customer.setPhoneNumber(updateCustomerOrderRequest.getPhoneNumber());
        customer.setHomeAddress(updateCustomerOrderRequest.getHomeAddress());
        customerRepository.save(customer);
        UpdateCustomersOrderResponse updateCustomersOrderResponse = new UpdateCustomersOrderResponse();
        updateCustomersOrderResponse.setMessage("Customer order updated");
        return updateCustomersOrderResponse;
    }


/**
 * Docker, Kubernates, k9s
 *
 * */
}


