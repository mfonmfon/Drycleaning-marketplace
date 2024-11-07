package com.semicolon.africa.service.interfaces;

import com.semicolon.africa.DTOs.request.CustomerLoginRequest;
import com.semicolon.africa.DTOs.request.CustomerRegistrationRequest;
import com.semicolon.africa.DTOs.request.PlaceOrderRequest;
import com.semicolon.africa.DTOs.response.CustomerLoginResponse;
import com.semicolon.africa.DTOs.response.CustomerRegistrationResponse;
import com.semicolon.africa.DTOs.response.PlaceOrderResponse;
import com.semicolon.africa.data.enums.ItemType;
import com.semicolon.africa.data.enums.ServiceType;
import com.semicolon.africa.data.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Test
    public void testThatCustomerCanSendOrder(){}

    @Test
    public void testThatCustomerCanRegister(){
        CustomerRegistrationRequest customerRegistrationRequest = customerRegistrationRequest();
        CustomerRegistrationResponse customerRegistrationResponse = customerService.register(customerRegistrationRequest);
        assertThat(customerRegistrationResponse).isNotNull();
        assertThat(customerRegistrationResponse.getMessage()).contains("Successfully registered");
        assertEquals(1,customerRepository.count());
    }

    private static CustomerRegistrationRequest customerRegistrationRequest(){
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest();
        customerRegistrationRequest.setFirstName("John");
        customerRegistrationRequest.setLastName("Smith");
        customerRegistrationRequest.setEmail("john@gmail.com");
        customerRegistrationRequest.setPhoneNumber("0812311568");
        customerRegistrationRequest.setPassword("password");
        return customerRegistrationRequest;
    }

    @Test
    public void testThatCustomerCanNotRegisterWithTheSameEmailAddress(){
        customerRegistrationRequest();
        CustomerRegistrationResponse customerRegistrationResponse = customerService.register(customerRegistrationRequest());
        assertThat(customerRegistrationResponse.getMessage()).contains("Successfully registered");
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest();
        customerRegistrationRequest.setFirstName("Patrick");
        customerRegistrationRequest.setLastName("Chris");
        customerRegistrationRequest.setEmail("patrick@gmail.com");
        customerRegistrationRequest.setPhoneNumber("0903456123");
        customerRegistrationRequest.setPassword("12321");
        CustomerRegistrationResponse customerRegistrationResponse1 = customerService.register(customerRegistrationRequest);
        assertThat(customerRegistrationResponse1.getMessage()).contains("Successfully registered");
    }
    @Test
    public void testThatCustomerFieldsCanNotBeNullOrEmpty(){
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest();
        customerRegistrationRequest.setFirstName("Mfon");
        customerRegistrationRequest.setLastName("Mfon");
        customerRegistrationRequest.setEmail("mfon@gmail.com");
        customerRegistrationRequest.setPhoneNumber("0812311568");
        customerRegistrationRequest.setPassword("password");
        CustomerRegistrationResponse customerRegistrationResponse1 = customerService.register(customerRegistrationRequest);
        assertThat(customerRegistrationResponse1.getMessage()).contains("Successfully registered");
    }

    @Test
    public void testThatCustomerCanLogin(){
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest();
        customerRegistrationRequest.setFirstName("Mfon");
        customerRegistrationRequest.setLastName("Mfon");
        customerRegistrationRequest.setEmail("mfon@gmail.com");
        customerRegistrationRequest.setPhoneNumber("0812311568");
        customerRegistrationRequest.setPassword("password");
        CustomerRegistrationResponse customerRegistrationResponse1 = customerService.register(customerRegistrationRequest);
        assertThat(customerRegistrationResponse1.getMessage()).contains("Successfully registered");
        CustomerLoginRequest customerLoginRequest = new CustomerLoginRequest();
        customerLoginRequest.setEmail("mfon@gmail.com");
        customerLoginRequest.setPassword("password");
        CustomerLoginResponse customerLoginResponse = customerService.login(customerLoginRequest);
        assertThat(customerLoginResponse.getMessage()).contains("Successfully login");
    }

    @Test
    public void testThatCustomerCanSendOrders(){
        CustomerRegistrationRequest customerRegistrationRequest = new CustomerRegistrationRequest();
        customerRegistrationRequest.setFirstName("Mfon");
        customerRegistrationRequest.setLastName("Mfon");
        customerRegistrationRequest.setEmail("mfon@gmail.com");
        customerRegistrationRequest.setPhoneNumber("0812311568");
        customerRegistrationRequest.setPassword("password");
        CustomerRegistrationResponse customerRegistrationResponse1 = customerService.register(customerRegistrationRequest);
        assertThat(customerRegistrationResponse1.getMessage()).contains("Successfully registered");
        CustomerLoginRequest customerLoginRequest = new CustomerLoginRequest();
        customerLoginRequest.setEmail("mfon@gmail.com");
        customerLoginRequest.setPassword("password");
        CustomerLoginResponse customerLoginResponse = customerService.login(customerLoginRequest);
        assertThat(customerLoginResponse.getMessage()).contains("Successfully login");
        PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest();
        placeOrderRequest.setServiceType(ServiceType.WASH_AND_IRON);
        placeOrderRequest.setItems(ItemType.TROUSERS);
        placeOrderRequest.setPrice(BigDecimal.valueOf(100000));
        placeOrderRequest.setQuantity(20);
        placeOrderRequest.setDateOrdered(LocalDateTime.now());
        PlaceOrderResponse placeOrderResponse = customerService.sendOrder(placeOrderRequest);
        assertThat(placeOrderResponse.getMessage()).contains("Order sent");
    }
}