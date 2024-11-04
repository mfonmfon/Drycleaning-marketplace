package com.semicolon.africa.utils;

import com.semicolon.africa.DTOs.request.CustomerRegistrationRequest;
import com.semicolon.africa.DTOs.response.CustomerRegistrationResponse;
import com.semicolon.africa.DTOs.response.PostServiceResponse;
import com.semicolon.africa.DTOs.response.DryCleanerUpdateOrderResponse;
import com.semicolon.africa.DTOs.response.PlaceOrderResponse;
import com.semicolon.africa.data.model.Customer;
import com.semicolon.africa.data.model.DryCleaner;
import com.semicolon.africa.data.model.OrderPlacement;

public class Mapper {

//    public static void map(PostServiceResponse dryCleanerAddOrderRequest, DryCleaner dryCleaner) {
//        dryCleaner.setFirstName(dryCleanerAddOrderRequest.getFirstName());
//        dryCleaner.setLastName(dryCleanerAddOrderRequest.getLastName());
//        dryCleaner.setEmail((dryCleanerAddOrderRequest.getEmail()));
//        dryCleaner.setPhoneNumber(dryCleanerAddOrderRequest.getPhoneNumber());
//        dryCleaner.setCompanyName(dryCleanerAddOrderRequest.getCompanyName());
//    }

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

    public static CustomerRegistrationResponse customerRegistrationResponseMapper(Customer customer) {
        CustomerRegistrationResponse customerRegistrationResponse = new CustomerRegistrationResponse();
        customerRegistrationResponse.setFirstName(customer.getFirstName());
        customerRegistrationResponse.setLastName(customer.getLastName());
        customerRegistrationResponse.setEmail(customer.getEmail());
        customerRegistrationResponse.setPhoneNumber(customer.getPhoneNumber());
        customerRegistrationResponse.setPassword(customer.getPassword());
        customerRegistrationResponse.setMessage("Successfully registered");
        return customerRegistrationResponse;
    }

    public static Customer mapRegistrationRequest(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = new Customer();
        customer.setFirstName(customerRegistrationRequest.getFirstName());
        customer.setLastName(customerRegistrationRequest.getLastName());
        customer.setPhoneNumber(customerRegistrationRequest.getPhoneNumber());
        customer.setEmail(customerRegistrationRequest.getEmail());
        customer.setPassword(customerRegistrationRequest.getPassword());
        return customer;
    }

}
