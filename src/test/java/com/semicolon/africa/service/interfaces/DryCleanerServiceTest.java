package com.semicolon.africa.service.interfaces;

import com.semicolon.africa.DTOs.request.DryCleanerAddOrderRequest;
import com.semicolon.africa.DTOs.response.DryCleanerAddOrderResponse;
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
        DryCleanerAddOrderRequest dryCleanerAddOrderRequest = new DryCleanerAddOrderRequest();
        dryCleanerAddOrderRequest.setFirstName("Mfon Mfon");
        dryCleanerAddOrderRequest.setLastName("Mfon");
        dryCleanerAddOrderRequest.setEmail("mfonm579@gmail.com");
        dryCleanerAddOrderRequest.setPhoneNumber("081231156");
        dryCleanerAddOrderRequest.setRider(dryCleanerAddOrderRequest.getRider());
        DryCleanerAddOrderResponse dryCleanerAddOrderResponse = dryCleanerService.sendOrder(dryCleanerAddOrderRequest);
        assertEquals(0,dryCleanerRepository.count());
        assertThat(dryCleanerAddOrderResponse).isNotNull();
        assertThat(dryCleanerAddOrderResponse.getMessage()).contains("OrderPlacement sent successfully");
    }
    @Test
    public void testThatWhenDryCleanerLeaverFirstNameInputUnfilled_throwEmptyFilledException(){
        DryCleanerAddOrderRequest dryCleanerAddOrderRequest = new DryCleanerAddOrderRequest();
        dryCleanerAddOrderRequest.setFirstName("");
        try {
            dryCleanerService.sendOrder(dryCleanerAddOrderRequest);
        }catch (InvalidOrEmptyFieldsException emptyFieldsException){
            assertEquals(emptyFieldsException.getMessage(), "must fill the first name");
        }
    }
    @Test
    public void testThatWhenDryCleanerLeaversLastNameInputUnFilled_throwEmptyInputException(){
        DryCleanerAddOrderRequest dryCleanerAddOrderRequest = new DryCleanerAddOrderRequest();
        dryCleanerAddOrderRequest.setLastName("");
        try {
            dryCleanerService.sendOrder(dryCleanerAddOrderRequest);
        }catch (InvalidOrEmptyFieldsException exception){
            assertEquals(exception.getMessage(), "must fill the last name");
        }
    }
}