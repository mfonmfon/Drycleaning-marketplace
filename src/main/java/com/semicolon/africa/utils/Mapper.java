package com.semicolon.africa.utils;

import com.semicolon.africa.DTOs.request.DryCleanerAddOrderRequest;
import com.semicolon.africa.DTOs.response.DryCleanerUpdateOrderResponse;
import com.semicolon.africa.DTOs.response.PlaceOrderResponse;
import com.semicolon.africa.data.model.DryCleaner;
import com.semicolon.africa.data.model.OrderPlacement;

public class Mapper {

    public static void map(DryCleanerAddOrderRequest dryCleanerAddOrderRequest, DryCleaner dryCleaner) {
        dryCleaner.setFirstName(dryCleanerAddOrderRequest.getFirstName());
        dryCleaner.setLastName(dryCleanerAddOrderRequest.getLastName());
        dryCleaner.setEmail((dryCleanerAddOrderRequest.getEmail()));
        dryCleaner.setPhoneNumber(dryCleanerAddOrderRequest.getPhoneNumber());
        dryCleaner.setCompanyName(dryCleanerAddOrderRequest.getCompanyName());
    }

    public static DryCleanerUpdateOrderResponse getDryCleanerUpdateOrderResponse() {
        DryCleanerUpdateOrderResponse dryCleanerUpdateOrderResponse = new DryCleanerUpdateOrderResponse();
        dryCleanerUpdateOrderResponse.setFirstName(dryCleanerUpdateOrderResponse.getFirstName());
        dryCleanerUpdateOrderResponse.setLastName(dryCleanerUpdateOrderResponse.getLastName());
        dryCleanerUpdateOrderResponse.setEmail(dryCleanerUpdateOrderResponse.getEmail());
        dryCleanerUpdateOrderResponse.setCompanyName(dryCleanerUpdateOrderResponse.getCompanyName());
        dryCleanerUpdateOrderResponse.setPhoneNumber(dryCleanerUpdateOrderResponse.getPhoneNumber());
        return dryCleanerUpdateOrderResponse;
    }


    public static PlaceOrderResponse placeOrderResponseMapper(OrderPlacement orderPlacement) {
        PlaceOrderResponse placeOrderResponse = new PlaceOrderResponse();
        placeOrderResponse.setOrderId(orderPlacement.getId());
        placeOrderResponse.setServiceType(orderPlacement.getServiceType());
        placeOrderResponse.setItems(orderPlacement.getItems());
        placeOrderResponse.setPrice(orderPlacement.getPrice());
        placeOrderResponse.setQuantity(orderPlacement.getQuantity());
        placeOrderResponse.setDateOrdered(orderPlacement.getDateOrdered());
        placeOrderResponse.setMessage("Order placed successfully");
        return placeOrderResponse;
    }

}
