package com.semicolon.africa.utils;

import com.semicolon.africa.data.model.Customer;
import com.semicolon.africa.dto.request.CustomerSendsAnOrderRequest;
import com.semicolon.africa.dto.request.SignupCustomerRequest;
import com.semicolon.africa.dto.request.UpdateCustomerOrderRequest;
import com.semicolon.africa.dto.response.CustomerSendsAnOrderResponse;
import com.semicolon.africa.dto.response.UpdateCustomersOrderResponse;

public class Mapper {
    public static void map(SignupCustomerRequest signupCustomerRequest, Customer customer) {
        customer.setFullName(signupCustomerRequest.getFullName());
        customer.setGender(signupCustomerRequest.getGender().MALE);
        customer.setEmail(signupCustomerRequest.getEmail());
    }

    public static Customer updateRequestMapping(UpdateCustomerOrderRequest updateCustomerOrderRequest, Customer customer) {
        customer.setFullName(updateCustomerOrderRequest.getFullName());
        customer.setPhoneNumber(updateCustomerOrderRequest.getPhoneNumber());
        customer.setStreet(updateCustomerOrderRequest.getStreet());
        customer.setCity(updateCustomerOrderRequest.getCity());
        customer.setCountry(updateCustomerOrderRequest.getCountry());
        customer.setDetailedInstructions(updateCustomerOrderRequest.getDetailedInstructions());
        customer.setServiceType(updateCustomerOrderRequest.getServiceType().WASH_AND_IRON);
        customer.setItemsType(updateCustomerOrderRequest.getItemsType().SWEAT_SHIRT);
        customer.setDateUpdated(updateCustomerOrderRequest.getUpdatedDated());
        return customer;
    }

    public static UpdateCustomersOrderResponse mapUpdateOrderResponse(UpdateCustomerOrderRequest updateCustomerOrderRequest, Customer customer) {
        UpdateCustomersOrderResponse updateCustomersOrderResponse = new UpdateCustomersOrderResponse();
        updateCustomersOrderResponse.setCustomerId(customer.getId());
        updateCustomersOrderResponse.setOrderId(customer.getOrderId());
        updateCustomersOrderResponse.setFullName(updateCustomerOrderRequest.getFullName());
        updateCustomersOrderResponse.setStreet(customer.getStreet());
        updateCustomersOrderResponse.setCountry(customer.getCountry());
        updateCustomersOrderResponse.setDetailedInstructions(customer.getDetailedInstructions());
        updateCustomersOrderResponse.setServiceType(customer.getServiceType().WASH_AND_IRON);
        updateCustomersOrderResponse.setItemsType(customer.getItemsType().SWEAT_SHIRT);
        updateCustomersOrderResponse.setUpdatedDate(customer.getDateUpdated());
        return updateCustomersOrderResponse;
    }

    public static CustomerSendsAnOrderResponse mapSendOrderResponse(CustomerSendsAnOrderRequest sendAnOrderRequest, Customer customer) {
        CustomerSendsAnOrderResponse sendsAnOrderResponse = new CustomerSendsAnOrderResponse();
        sendsAnOrderResponse.setFullName(customer.getFullName());
        sendsAnOrderResponse.setPhoneNumber(customer.getPhoneNumber());
        sendsAnOrderResponse.setStreet(customer.getStreet());
        sendsAnOrderResponse.setCity(customer.getCity());
        sendsAnOrderResponse.setServiceType(customer.getServiceType().WASH_AND_FOLD);
        sendsAnOrderResponse.setItemsType(customer.getItemsType().SWEAT_SHIRT);
        sendsAnOrderResponse.setDetailedInstructions(customer.getDetailedInstructions());
        sendsAnOrderResponse.setDateSent(sendAnOrderRequest.getDateSent());
        sendsAnOrderResponse.setMessage("You've sent your order, a rider will be assign to you shortly");
        return sendsAnOrderResponse;
    }

    public static void customerSendsOrderRequestMap(CustomerSendsAnOrderRequest sendAnOrderRequest, Customer customer) {
        customer.setFullName(sendAnOrderRequest.getFullName());
        customer.setPhoneNumber(sendAnOrderRequest.getPhoneNumber());
        customer.setStreet(sendAnOrderRequest.getStreet());
        customer.setCity(sendAnOrderRequest.getCity());
        customer.setCountry(sendAnOrderRequest.getCountry());
        customer.setServiceType(sendAnOrderRequest.getServiceType().WASH_AND_FOLD);
        customer.setItemsType(sendAnOrderRequest.getItemsType().SWEAT_SHIRT);
        customer.setDetailedInstructions(sendAnOrderRequest.getDetailedInstructions());
        customer.setDateSent(sendAnOrderRequest.getDateSent());
    }


}
