package com.semicolon.africa.service.interfaces;

import com.semicolon.africa.DTOs.request.*;
import com.semicolon.africa.DTOs.response.*;
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
        PlaceOrderRequest dryCleanerAddOrderRequest = SendOrder();
        PlaceOrderResponse dryCleanerAddOrderResponse = dryCleanerService.sendOrder(dryCleanerAddOrderRequest);
        assertEquals(1,dryCleanerRepository.count());
        assertThat(dryCleanerAddOrderResponse).isNotNull();
        assertThat(dryCleanerAddOrderResponse.getMessage()).contains("Ordered sent successfully");
    }

    private static PlaceOrderRequest SendOrder() {
        PlaceOrderRequest dryCleanerAddOrderRequest = new PlaceOrderRequest();
//        dryCleanerAddOrderRequest.setFirstName("Mfon");
//        dryCleanerAddOrderRequest.setLastName("Mfon");
//        dryCleanerAddOrderRequest.setEmail("mfonm579@gmail.com");
//        dryCleanerAddOrderRequest.setPhoneNumber("08123115688");
//        dryCleanerAddOrderRequest.setCompanyName("FonDryer");
        return dryCleanerAddOrderRequest;
    }

//    @Test
//    public void testThatWhenDryCleanerLeavesInputUnfilled_throwEmptyFilledException(){
//       DryCleanerAddOrderRequest dryCleanerAddOrderRequest = new DryCleanerAddOrderRequest();
//       dryCleanerAddOrderRequest.setFirstName("me");
//       dryCleanerAddOrderRequest.setLastName("last name");
//       dryCleanerAddOrderRequest.setEmail("name@gmail.com");
//       dryCleanerAddOrderRequest.setPhoneNumber("phone number");
//       dryCleanerAddOrderRequest.setCompanyName("company");
//       assertThrows(InvalidOrEmptyFieldsException.class,
//                ()-> dryCleanerService.sendOrder(dryCleanerAddOrderRequest));
//    }

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
        DryCleanerLoginRequest loginRequest = loginRequest();
        DryCleanerLoginResponse loginResponse = dryCleanerService.login(loginRequest);
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getMessage()).contains("Login Successfully");
    }

    private static DryCleanerLoginRequest loginRequest() {
        DryCleanerLoginRequest loginRequest = new DryCleanerLoginRequest();
        loginRequest.setEmail("mfonm579@gmail.com");
        loginRequest.setPassword("mfonm579");
        return loginRequest;
    }

    @Test
    public void testThatDryCleanerCanUpdateOrders(){
        DryCleanerRegisterRequest dryCleanerRegisterRequest = new DryCleanerRegisterRequest();
        dryCleanerRegisterRequest.setFirstName("Mfon");
        dryCleanerRegisterRequest.setEmail("mfonm3579@gmail.com");
        dryCleanerRegisterRequest.setPhoneNumber("08123115688");
        dryCleanerRegisterRequest.setPassword("mfonm579");
        DryCleanerRegisterResponse dryCleanerRegisterResponse = dryCleanerService.register(dryCleanerRegisterRequest);
        assertThat(dryCleanerRegisterResponse.getMessage()).contains("Hello Registered successfully");
        DryCleanerLoginRequest dryCleanerLoginRequest = new DryCleanerLoginRequest();
        dryCleanerLoginRequest.setEmail("mfonm3579@gmail.com");
        dryCleanerLoginRequest.setPassword("mfonm579");
        DryCleanerLoginResponse dryCleanerLoginResponse = dryCleanerService.login(dryCleanerLoginRequest);
        assertThat(dryCleanerLoginResponse.getMessage()).contains("Login Successfully");
        PlaceOrderResponse dryCleanerAddOrderRequest = new PlaceOrderResponse();
//        dryCleanerAddOrderRequest.setFirstName("Mfon");
//        dryCleanerAddOrderRequest.setLastName("Mfon");
//        dryCleanerAddOrderRequest.setEmail("mfonm3579@gmail.com");
//        dryCleanerAddOrderRequest.setPhoneNumber("08123115688");
//        dryCleanerAddOrderRequest.setCompanyName("FonDryer");
//        DryCleanerAddOrderResponse dryCleanerAddOrderResponse = dryCleanerService.sendOrder(dryCleanerAddOrderRequest);
//        assertThat(dryCleanerAddOrderResponse.getMessage()).contains("Order sent successfully");
        DryCleanerUpdateOrderRequest dryCleanerUpdateOrderRequest = new DryCleanerUpdateOrderRequest();
//        dryCleanerUpdateOrderRequest.setDryCleanerId(2L);
        dryCleanerUpdateOrderRequest.setEmail("mfon3579@gmail.com");
        dryCleanerUpdateOrderRequest.setFirstName("Mfon");
        dryCleanerUpdateOrderRequest .setLastName("Paul");
        dryCleanerUpdateOrderRequest .setPhoneNumber("08123115688");
        dryCleanerUpdateOrderRequest .setCompanyName("company");
        DryCleanerUpdateOrderResponse dryCleanerUpdateOrderResponse = dryCleanerService.updateOrder(dryCleanerUpdateOrderRequest);
        assertThat(dryCleanerUpdateOrderResponse.getMessage()).contains("order was successfully updated");
        assertThat(dryCleanerUpdateOrderResponse).isNotNull();
    }



    @Test
    public void testThatDryCleanerCanDeleteOrders(){
        registerDryCleaner();
        DryCleanerRegisterResponse dryCleanerRegisterResponse = dryCleanerService.register(registerDryCleaner());
        assertEquals(1, dryCleanerRepository.count());
        assertThat(dryCleanerRegisterResponse.getMessage()).contains("Hello Registered successfully");
       loginRequest();
        DryCleanerLoginResponse dryCleanerLoginResponse = dryCleanerService.login(loginRequest());
       assertThat(dryCleanerLoginResponse.getMessage()).contains("Login Successfully");
        SendOrder();
        PlaceOrderResponse dryCleanerAddOrderResponse = dryCleanerService.sendOrder(SendOrder());
        assertThat(dryCleanerAddOrderResponse.getMessage()).contains("Order sent successfully");
        Long id = dryCleanerAddOrderResponse.getDryCleanerId();
        DryCleanerDeleteOrderResponse dryCleanerDeleteOrderResponse = dryCleanerService.deleteOrder(id);
        assertThat(dryCleanerDeleteOrderResponse.getMessage()).contains("Order deleted successfully");
    }
}
