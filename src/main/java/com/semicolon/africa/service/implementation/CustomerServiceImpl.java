package com.semicolon.africa.service.implementation;

import com.semicolon.africa.DTOs.request.*;
import com.semicolon.africa.DTOs.response.*;
import com.semicolon.africa.data.model.Customer;
import com.semicolon.africa.data.repository.CustomerRepository;
import com.semicolon.africa.exception.*;
import com.semicolon.africa.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static com.semicolon.africa.utils.Mapper.customerRegistrationResponseMapper;
import static com.semicolon.africa.utils.Mapper.mapRegistrationRequest;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    private final Pattern VALIDATE_CUSTOMER_EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private final Pattern VALIDATE_CUSTOMER_PHONE_NUMBER = Pattern.compile("\\+?[0-9]{1,3}?[ -]?[0-9]{9}$");

    @Override
    public CustomerLoginResponse login(CustomerLoginRequest customerLoginRequest) {
        Customer customer = findCustomerByEmail(customerLoginRequest.getEmail());
        customer.setEmail(customerLoginRequest.getEmail());
        customer.setPassword(passwordEncoder.encode(customerLoginRequest.getPassword()));
        customer.setLoggedIn(true);
        customer = customerRepository.save(customer);
        CustomerLoginResponse customerLoginResponse = new CustomerLoginResponse();
        customerLoginResponse.setEmail(customer.getEmail());
        customerLoginResponse.setPassword(customer.getPassword());
        customerLoginResponse.setPassword(customer.getPassword());

        customerLoginResponse.setMessage("Successfully login");
        return customerLoginResponse;
    }
    private Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email)
                .orElseThrow(()-> new CustomerNotFoundException("Could not find customer"));
    }

    @Override
    public CustomerRegistrationResponse register(CustomerRegistrationRequest customerRegistrationRequest) {
        validateCustomerEmail(customerRegistrationRequest.getEmail());
        if(!isValidEmail(customerRegistrationRequest.getEmail()))throw new WrongEmailException("Wrong email");
        if(!isValidPhoneNumber(customerRegistrationRequest.getPhoneNumber()))throw new WrongPhoneNumberException("Wrong phone number");
        validateEmptyOrNullFields(customerRegistrationRequest.getFirstName(),
                customerRegistrationRequest.getLastName(), customerRegistrationRequest.getEmail(),
                customerRegistrationRequest.getPhoneNumber(), customerRegistrationRequest.getPassword());
        validateAndHashCustomersPassword(passwordEncoder.encode(customerRegistrationRequest.getPassword()));
        Customer customer = mapRegistrationRequest(customerRegistrationRequest);
        customer = customerRepository.save(customer);
        return customerRegistrationResponseMapper(customer);
    }

    private void validateEmptyOrNullFields(String firstName, String lastName, String email, String phoneNumber, String password) {
        if (firstName == null|| firstName.trim().isEmpty()) throw new FirstNameEmptyOrIsNullException("First name is required");
        if(lastName == null|| lastName.trim().isEmpty()) throw new LastNameEmptyOrIsNullException("Last name is required");
        if(email == null|| email.trim().isEmpty()) throw new EmailEmptyOrIsNullException("Email is required");
        if(phoneNumber == null|| phoneNumber.trim().isEmpty()) throw new PhoneNumberEmptyOrIsNullException("Phone number is required");
        if(password == null|| password.trim().isEmpty()) throw new PasswordEmptyOrIsNullException("Password is required");
    }

    private void validateAndHashCustomersPassword(String password) {
        if(passwordEncoder.encode(password).matches(password)) throw new InCorrectPassword("Invalid password");
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return VALIDATE_CUSTOMER_PHONE_NUMBER.matcher(phoneNumber).matches();
    }
    private boolean isValidEmail(String email) {
        return VALIDATE_CUSTOMER_EMAIL.matcher(email).matches();
    }

    private void validateCustomerEmail(String email) {
        boolean isCustomerEmailExist = customerRepository.existsByEmail(email);
        if(isCustomerEmailExist)throw new CustomerEmailAlreadyExistsException("Customer email already exists");
    }

    @Override
    public PlaceOrderResponse sendOrder(PlaceOrderRequest customerSendOrderRequest) {
        Customer customer = new Customer();
        validateCustomerIsLoggedIn(customer);
        return null;
    }

    private void validateCustomerIsLoggedIn(Customer customer) {
        if(!customer.isLoggedIn())throw new CustomerNotLoggedInException("You are not logged in");
    }

    @Override
    public CustomerUpdateOrderResponse updateOrder(CustomerUpdateOrderRequest customerUpdateOrderRequest) {
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
    public List<Customer> findCustomersByName(String firstName) {
        return customerRepository.findCustomersByFirstName(firstName);
    }
}
