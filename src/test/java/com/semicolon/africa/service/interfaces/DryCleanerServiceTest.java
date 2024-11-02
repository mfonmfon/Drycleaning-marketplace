package com.semicolon.africa.service.interfaces;

import com.semicolon.africa.DTOs.request.DryCleanerAddOrderRequest;
import com.semicolon.africa.DTOs.request.DryCleanerLoginRequest;
import com.semicolon.africa.DTOs.request.DryCleanerRegisterRequest;
import com.semicolon.africa.DTOs.response.DryCleanerAddOrderResponse;
import com.semicolon.africa.DTOs.response.DryCleanerLoginResponse;
import com.semicolon.africa.DTOs.response.DryCleanerRegisterResponse;
import com.semicolon.africa.data.repository.DryCleanerRepository;
import com.semicolon.africa.exception.InvalidOrEmptyFieldsException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DryCleanerServiceTest {

    @Autowired
    private DryCleanerRepository dryCleanerRepository;

    @Autowired
    private  DryCleanerService dryCleanerService;


    @BeforeEach
    void setUp() {
        dryCleanerRepository.deleteAll();
    }

    @Test
    public void testThatDryCleanerCanSendOrder(){
        DryCleanerAddOrderRequest dryCleanerAddOrderRequest = SendOrder();
        dryCleanerAddOrderRequest.setRider(dryCleanerAddOrderRequest.getRider());
        DryCleanerAddOrderResponse dryCleanerAddOrderResponse = dryCleanerService.sendOrder(dryCleanerAddOrderRequest);
        assertEquals(1,dryCleanerRepository.count());
        assertThat(dryCleanerAddOrderResponse).isNotNull();
        assertThat(dryCleanerAddOrderResponse.getMessage()).contains("Ordered sent successfully");
    }

    private static DryCleanerAddOrderRequest SendOrder() {
        DryCleanerAddOrderRequest dryCleanerAddOrderRequest = new DryCleanerAddOrderRequest();
        dryCleanerAddOrderRequest.setFirstName("Mfon Mfon");
        dryCleanerAddOrderRequest.setLastName("Mfon");
        dryCleanerAddOrderRequest.setEmail("mfonm579@gmail.com");
        dryCleanerAddOrderRequest.setPhoneNumber("081231156");
        dryCleanerAddOrderRequest.setCompanyName("FonDryer");
        return dryCleanerAddOrderRequest;
    }

    @Test
    public void testThatWhenDryCleanerLeavesInputUnfilled_throwEmptyFilledException(){
       DryCleanerAddOrderRequest dryCleanerAddOrderRequest = new DryCleanerAddOrderRequest();
       dryCleanerAddOrderRequest.setFirstName("me");
       dryCleanerAddOrderRequest.setLastName("last name");
       dryCleanerAddOrderRequest.setEmail("name@gmail.com");
       dryCleanerAddOrderRequest.setPhoneNumber("phone number");
       dryCleanerAddOrderRequest.setCompanyName("company");
       assertThrows(InvalidOrEmptyFieldsException.class,
                ()-> dryCleanerService.sendOrder(dryCleanerAddOrderRequest));
    }

    @Test
    public void testThatDryCleanerCanCantRegisterAtTheSameTime_throwDryCleanerAlreadyExistException(){
        DryCleanerRegisterRequest dryCleanerRegisterRequest = registerDryCleaner();
        DryCleanerRegisterResponse dryCleanerRegisterResponse = dryCleanerService.register(dryCleanerRegisterRequest);
        assertEquals(1,dryCleanerRepository.count());
        assertThat(dryCleanerRegisterResponse.getMessage()).contains("Hello Registered successfully");
        assertThat(dryCleanerRegisterResponse).isNotNull();
    }

    @Test
    public void testThatTwoUsersCanNotRegisterWithTheSameEmail() {
        DryCleanerRegisterRequest dryCleanerRegisterRequest = registerDryCleaner();
        DryCleanerRegisterResponse dryCleanerRegisterResponse = dryCleanerService.register(dryCleanerRegisterRequest);
        assertThat(dryCleanerRegisterResponse.getMessage()).contains("Hello Registered successfully");
        DryCleanerRegisterRequest dryCleanerRegisterRequest1 = new DryCleanerRegisterRequest();
        dryCleanerRegisterRequest1.setFirstName("Williams");
        dryCleanerRegisterRequest1.setEmail("william5179@gmail.com");
        dryCleanerRegisterRequest1.setPhoneNumber("0914323323");
        dryCleanerRegisterRequest1.setPassword("121212");
        DryCleanerRegisterResponse dryCleanerRegisterResponse1 = dryCleanerService.register(dryCleanerRegisterRequest1);
        assertThat(dryCleanerRegisterResponse1.getMessage()).isEqualTo("Hello Registered successfully");
    }

    private static DryCleanerRegisterRequest registerDryCleaner() {
        DryCleanerRegisterRequest dryCleanerRegisterRequest = new DryCleanerRegisterRequest();
        dryCleanerRegisterRequest.setFirstName("Mfon");
        dryCleanerRegisterRequest.setEmail("mfonm579@gmail.com");
        dryCleanerRegisterRequest.setPhoneNumber("08123115688");
        dryCleanerRegisterRequest.setPassword("mfonm579");
        return dryCleanerRegisterRequest;
    }

    @Test
    public void testThatDryCleanerCanRegisterAndLogin(){
        registerDryCleaner();
        DryCleanerRegisterResponse dryCleanerRegisterResponse = dryCleanerService.register(registerDryCleaner());
        assertEquals(1, dryCleanerRepository.count());
        assertThat(dryCleanerRegisterResponse.getMessage()).contains("Hello Registered successfully");
        DryCleanerLoginRequest loginRequest = new DryCleanerLoginRequest();
        loginRequest.setEmail("mfonm579@gmail.com");
        loginRequest.setPassword("mfonm579");
        DryCleanerLoginResponse loginResponse = dryCleanerService.login(loginRequest);
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getMessage()).contains("Login Successfully");
    }


}

