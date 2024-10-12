package com.semicolon.africa.service;

import com.semicolon.africa.data.model.Gender;
import com.semicolon.africa.data.model.ItemsType;
import com.semicolon.africa.data.model.ServiceType;
import com.semicolon.africa.data.repository.CustomerRepository;
import com.semicolon.africa.dto.request.CustomerSendsAnOrderRequest;
import com.semicolon.africa.dto.request.LoginCustomerRequest;
import com.semicolon.africa.dto.request.SignupCustomerRequest;
import com.semicolon.africa.dto.request.UpdateCustomerOrderRequest;
import com.semicolon.africa.dto.response.CustomerSendsAnOrderResponse;
import com.semicolon.africa.dto.response.LoginCustomerResponse;
import com.semicolon.africa.dto.response.SignupCustomerResponse;
import com.semicolon.africa.dto.response.UpdateCustomersOrderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static com.semicolon.africa.data.model.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private  CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp(){
        customerRepository.deleteAll();
    }
    @Test
    public void testThatCustomerCanRegisterOnTheApp(){
        SignupCustomerRequest signupCustomerRequest = new SignupCustomerRequest();
        signupCustomerRequest.setFullName("Alexander Ogheneghu");
        signupCustomerRequest.setEmail("alex@gmail.com");
        signupCustomerRequest.setGender(MALE);
        signupCustomerRequest.setPassword("123212");
        SignupCustomerResponse signupCustomerResponse = customerService.signupCustomer(signupCustomerRequest);
        assertThat(signupCustomerResponse.getMessage()).contains("Successfully registered");
        assertEquals(customerRepository.findAll().size(), 1);
    }

    @Test
    public void testThatCustomerCanLoginAfterSigningUp(){
        SignupCustomerRequest signupCustomerRequest = new SignupCustomerRequest();
        signupCustomerRequest.setFullName("Alexander Ogheneghu");
        signupCustomerRequest.setEmail("alex@gmail.com");
        signupCustomerRequest.setGender(MALE);
        signupCustomerRequest.setPassword("123212");
        SignupCustomerResponse signupCustomerResponse = customerService.signupCustomer(signupCustomerRequest);
        assertThat(signupCustomerResponse.getMessage()).contains("Successfully registered");
        assertEquals(customerRepository.findAll().size(), 1);
        LoginCustomerRequest loginCustomerRequest = new LoginCustomerRequest();
        loginCustomerRequest.setEmail("alex@gmail.com");
        loginCustomerRequest.setPassword("123212");
        LoginCustomerResponse loginCustomerResponse = customerService.loginCustomer(loginCustomerRequest);
        assertThat(loginCustomerResponse.getMessage()).contains("You're logged in");
        assertTrue(loginCustomerResponse.isLoggedIn());
    }

    @Test
    public void testThatCustomerCanSendAnOrderToTheLaundry(){
        CustomerSendsAnOrderRequest sendsAnOrderRequest = new CustomerSendsAnOrderRequest();
        sendsAnOrderRequest.setFullName("Alex guda");
        sendsAnOrderRequest.setPhoneNumber("0812124433");
        sendsAnOrderRequest.setStreet("Ogudu");
        sendsAnOrderRequest.setCity("Lagos");
        sendsAnOrderRequest.setCountry("Nigeria");
        sendsAnOrderRequest.setServiceType(ServiceType.IRON_ONLY);
        sendsAnOrderRequest.setItemsType(ItemsType.SENATOR);
        sendsAnOrderRequest.setDetailedInstructions("Dont use detergent to wash my clothes");
        sendsAnOrderRequest.setDateSent(LocalDateTime.now());
        CustomerSendsAnOrderResponse sendsAnOrderResponse = customerService.sendOrder(sendsAnOrderRequest);
        assertThat(sendsAnOrderResponse.getMessage()).contains("You've sent your order," +
                " a rider will be assign to you shortly");
        assertEquals(customerRepository.findAll().size(), 1);
        assertThat(sendsAnOrderResponse).isNotNull();
    }

    @Test
    public void testThatCustomersOrdersCanBeUpdated(){
        UpdateCustomerOrderRequest updateCustomerOrderRequest = new UpdateCustomerOrderRequest();
        updateCustomerOrderRequest.setFullName("Victor Emaye");
        updateCustomerOrderRequest.setPhoneNumber("0903213332");
        updateCustomerOrderRequest.setStreet("sadilo");
        updateCustomerOrderRequest.setCity("Lagos");
        updateCustomerOrderRequest.setCountry("Nigeria");
        updateCustomerOrderRequest.setDetailedInstructions("");
        updateCustomerOrderRequest.setServiceType(ServiceType.IRON_ONLY);
        UpdateCustomersOrderResponse customersOrderResponse = customerService.updateOrder(updateCustomerOrderRequest);
        assertThat(customersOrderResponse).isNotNull();
        assertThat(customersOrderResponse.getMessage()).contains("Update customer order");
    }
}
