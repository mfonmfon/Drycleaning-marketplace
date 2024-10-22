package com.semicolon.africa.service;

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
import com.semicolon.africa.exception.EmailAlreadyExistException;
import com.semicolon.africa.exception.InvalidPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
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
        signupCustomerRequest.setHomeAddress("Sabo yaba");
        signupCustomerRequest.setPhoneNumber("0812392293");
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
        signupCustomerRequest.setHomeAddress("Sabo yaba");
        signupCustomerRequest.setPhoneNumber("0812392293");
        signupCustomerRequest.setPassword("123212");
        SignupCustomerResponse signupCustomerResponse = customerService.signupCustomer(signupCustomerRequest);
        assertThat(signupCustomerResponse.getMessage()).contains("Successfully registered");
        assertEquals(customerRepository.findAll().size(), 1);
        LoginCustomerRequest loginCustomerRequest = new LoginCustomerRequest();
        loginCustomerRequest.setEmail("alex@gmail.com");
        loginCustomerRequest.setPassword("1232  ");
        LoginCustomerResponse loginCustomerResponse = customerService.loginCustomer(loginCustomerRequest);
        assertThat(loginCustomerResponse.getMessage()).contains("Logged in successfully");
        assertTrue(loginCustomerResponse.isLoggedIn());
    }

    @Test
    public void testThatCustomerCanSendAnOrderToTheLaundry(){
        CustomerSendsAnOrderRequest sendsAnOrderRequest = new CustomerSendsAnOrderRequest();
        sendsAnOrderRequest.setFullName("Alex guda");
        sendsAnOrderRequest.setPhoneNumber("0812124433");
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
        updateCustomerOrderRequest.setDetailedInstructions("Must kini");
        updateCustomerOrderRequest.setServiceType(ServiceType.IRON_ONLY);
        UpdateCustomersOrderResponse customersOrderResponse = customerService.updateOrder(updateCustomerOrderRequest);
        assertThat(customersOrderResponse).isNotNull();
        assertThat(customersOrderResponse.getMessage()).contains("Update customer order");
    }

    @Test
    public void testThatWhenCustomerInputWrongPasswordItShould_throwInvalidPasswordException(){
        SignupCustomerRequest signupCustomerRequest = new SignupCustomerRequest();
        signupCustomerRequest.setFullName("Alexander Ogheneghu");
        signupCustomerRequest.setEmail("alex@gmail.com");
        signupCustomerRequest.setHomeAddress("Sabo yaba");
        signupCustomerRequest.setPhoneNumber("0812392293");
        signupCustomerRequest.setPassword("123212");
        assertThrows(InvalidPasswordException.class, ()-> customerService.signupCustomer(signupCustomerRequest));
    }

    @Test
    public void testThatWhenCustomerInputAnEmailThatAlreadyExist_throwEmailAlreadyExistException(){
        SignupCustomerRequest signupCustomerRequest = new SignupCustomerRequest();
        signupCustomerRequest.setFullName("Alexander Ogheneghu");
        signupCustomerRequest.setEmail("alex@gmail.com");
        signupCustomerRequest.setHomeAddress("Sabo yaba");
        signupCustomerRequest.setPhoneNumber("0812392293");
        signupCustomerRequest.setPassword("123212");
        assertThrows(EmailAlreadyExistException.class, ()-> customerService.signupCustomer(signupCustomerRequest));

    }
}
