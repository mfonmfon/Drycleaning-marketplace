package com.semicolon.africa.service.interfaces;

import com.semicolon.africa.DTOs.request.CustomerRegistrationRequest;
import com.semicolon.africa.DTOs.response.CustomerRegistrationResponse;
import com.semicolon.africa.data.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RequiredArgsConstructor
class CustomerServiceTest {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    public void testThatCustomerCanSendOrder(){}

    @Test
    public void testThatCustomerCanRegister(){
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest();
        customerRegistrationRequest.setFirstName("John");
        customerRegistrationRequest.setLastName("Smith");
        customerRegistrationRequest.setEmail("john@gmail.com");
        customerRegistrationRequest.setPhoneNumber("08123115688");
        CustomerRegistrationResponse customerRegistrationResponse = customerService.register(customerRegistrationRequest);
        assertThat(customerRegistrationResponse).isNotNull();
        assertThat(customerRegistrationResponse.getMessage()).contains("Successfully registered");
        assertEquals(1,customerRepository.count());
    }

}