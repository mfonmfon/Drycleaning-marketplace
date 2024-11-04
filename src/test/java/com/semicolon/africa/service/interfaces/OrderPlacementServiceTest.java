package com.semicolon.africa.service.interfaces;

import com.semicolon.africa.DTOs.request.PlaceOrderRequest;
import com.semicolon.africa.DTOs.request.UpdatePlaceOrderRequest;
import com.semicolon.africa.DTOs.response.PlaceOrderResponse;
import com.semicolon.africa.DTOs.response.UpdatePlaceOrderResponse;
import com.semicolon.africa.data.enums.ItemType;
import com.semicolon.africa.data.enums.ServiceType;
import com.semicolon.africa.data.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderPlacementServiceTest {
    @Autowired
    private OrderPlacementService orderPlacementService;
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    public void testThatOrderCanBePlaced(){
        PlaceOrderRequest orderPlacement = new PlaceOrderRequest();
        orderPlacement.setServiceType(ServiceType.WASH_AND_DRY);
        orderPlacement.setItems(ItemType.SENATOR);
        orderPlacement.setQuantity(10);
        orderPlacement.setPrice(BigDecimal.valueOf(7000));
        orderPlacement.setDateOrdered(LocalDateTime.now());
        PlaceOrderResponse placeOrderResponse = orderPlacementService.placeOrder(orderPlacement);
        assertEquals("Order placed successfully", placeOrderResponse.getMessage());
        assertThat(placeOrderResponse).isNotNull();
    }
    @Test
    public void testThatOrderCanNotBePlacedWhenQuantityIsNegative(){}

    @Test
    public void testThatOrdersCanBeUpdatedAfterBeingPlaced(){
        PlaceOrderRequest orderPlacement = new PlaceOrderRequest();
        orderPlacement.setServiceType(ServiceType.WASH_AND_DRY);
        orderPlacement.setItems(ItemType.SENATOR);
        orderPlacement.setQuantity(10);
        orderPlacement.setPrice(BigDecimal.valueOf(7000));
        orderPlacement.setDateOrdered(LocalDateTime.now());
        PlaceOrderResponse placeOrderResponse = orderPlacementService.placeOrder(orderPlacement);
        assertEquals("Order placed successfully", placeOrderResponse.getMessage());
        assertThat(placeOrderResponse).isNotNull();
        UpdatePlaceOrderRequest updatePlaceOrderRequest = new UpdatePlaceOrderRequest();
        updatePlaceOrderRequest.setServiceType(ServiceType.WASH_AND_IRON);
        updatePlaceOrderRequest.setItems(ItemType.SHIRT);
        updatePlaceOrderRequest.setQuantity(14);
        updatePlaceOrderRequest.setPrice(BigDecimal.valueOf(7500));
        updatePlaceOrderRequest.setDateUpdated(LocalDateTime.now());
        UpdatePlaceOrderResponse updatePlaceOrderResponse = orderPlacementService.updateOrder(updatePlaceOrderRequest);
        assertEquals("Order updated successfully", updatePlaceOrderResponse.getMessage());
        assertThat(updatePlaceOrderResponse).isNotNull();
    }
}