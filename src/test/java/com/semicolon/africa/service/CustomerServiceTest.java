package com.semicolon.africa.service;

import com.semicolon.africa.data.model.Gender;
import com.semicolon.africa.data.repository.CustomerRepository;
import com.semicolon.africa.dto.request.LoginCustomerRequest;
import com.semicolon.africa.dto.request.SignupCustomerRequest;
import com.semicolon.africa.dto.response.LoginCustomerResponse;
import com.semicolon.africa.dto.response.SignupCustomerResponse;
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
}