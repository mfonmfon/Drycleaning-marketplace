package com.semicolon.africa.service.implementation;

import com.semicolon.africa.DTOs.request.DryCleanerAddOrderRequest;
import com.semicolon.africa.DTOs.request.DryCleanerLoginRequest;
import com.semicolon.africa.DTOs.request.DryCleanerRegisterRequest;
import com.semicolon.africa.DTOs.request.DryCleanerUpdateOrderRequest;
import com.semicolon.africa.DTOs.response.*;
import com.semicolon.africa.data.model.DryCleaner;
import com.semicolon.africa.data.repository.DryCleanerRepository;
import com.semicolon.africa.exception.DryCleanerDoesNotExist;
import com.semicolon.africa.exception.InvalidOrEmptyFieldsException;
import com.semicolon.africa.service.interfaces.DryCleanerService;
import org.springframework.stereotype.Service;

@Service
public class DryCleanerServiceImpl implements DryCleanerService {


    private final DryCleanerRepository dryCleanerRepository;

    public DryCleanerServiceImpl(DryCleanerRepository dryCleanerRepository) {
        this.dryCleanerRepository = dryCleanerRepository;
    }

    @Override
    public DryCleanerAddOrderResponse sendOrder(DryCleanerAddOrderRequest dryCleanerAddOrderRequest) {
        validateDryCleanerEmail(dryCleanerAddOrderRequest.getEmail());
        DryCleaner dryCleaner = new DryCleaner();
        dryCleaner.setFirstName(validateDryCleanerFirstName(dryCleanerAddOrderRequest.getFirstName()));
        dryCleaner.setLastName(validateDryCleanerLastName(dryCleanerAddOrderRequest.getLastName()));
        dryCleaner.setEmail(validateDryCleanerEmailInput((dryCleanerAddOrderRequest.getEmail())));
        dryCleaner.setPhoneNumber(validateDryCleanerPhoneNumber(dryCleanerAddOrderRequest.getPhoneNumber()));
        dryCleanerRepository.save(dryCleaner);
        DryCleanerAddOrderResponse dryCleanerAddOrderResponse = new DryCleanerAddOrderResponse();
        dryCleanerAddOrderResponse.setId(dryCleaner.getId());
        dryCleanerAddOrderResponse.setMessage("Ordered sent successfully");
        return dryCleanerAddOrderResponse;
    }

    private String validateDryCleanerEmailInput(String email) {
        if (email.isEmpty()|| email == null) throw new InvalidOrEmptyFieldsException("Must fills this email");
        return email;
    }

    private String validateDryCleanerPhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty() || phoneNumber == null)throw new InvalidOrEmptyFieldsException("must fill this phone number");
        return phoneNumber;
    }


    private String validateDryCleanerLastName(String lastName) {
        if (lastName.isEmpty()|| lastName == null)throw new InvalidOrEmptyFieldsException("Must fill this last name");
        return lastName;
    }

    private String validateDryCleanerFirstName(String firstName) {
        if (firstName.isEmpty()|| firstName == null)throw new InvalidOrEmptyFieldsException("must fill the first name");
        return firstName;

    }
    private void validateDryCleanerEmail(String email) {
        boolean isDryCleanerEmailExist = dryCleanerRepository.existsByEmail(email);
        if (isDryCleanerEmailExist){
            throw new DryCleanerDoesNotExist("Dry cleaner does not exist");
        }
    }

    @Override
    public DryCleanerUpdateOrderResponse updateOrder(DryCleanerUpdateOrderRequest dryCleanerUpdateOrderRequest) {
        return null;
    }

    @Override
    public DryCleanerDeleteOrderResponse deleteOrder(String id) {
        return null;
    }

    @Override
    public DryCleanerRegisterResponse register(DryCleanerRegisterRequest dryCleanerRegisterRequest) {
        DryCleaner dryCleaner = new DryCleaner();
        dryCleaner.setEmail(dryCleanerRegisterRequest.getEmail());
        dryCleaner.setPhoneNumber(dryCleanerRegisterRequest.getPhoneNumber());
        dryCleaner.setPassword(dryCleanerRegisterRequest.getPassword());
        DryCleanerRegisterResponse  dryCleanerRegisterResponse = new DryCleanerRegisterResponse();
        dryCleanerRegisterResponse.setPhoneNumber(dryCleaner.getPhoneNumber());
        dryCleanerRegisterResponse.setEmail(dryCleaner.getEmail());
        return null;
    }

    @Override
    public DryCleanerLoginResponse login(DryCleanerLoginRequest dryCleanerLoginRequest) {
        return null;
    }

    @Override
    public CheckForRiderAvailabilty isAvailable() {
        return null;
    }
}
