package com.semicolon.africa.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.africa.DTOs.request.DryCleanerAddOrderRequest;
import com.semicolon.africa.DTOs.request.DryCleanerLoginRequest;
import com.semicolon.africa.DTOs.request.DryCleanerRegisterRequest;
import com.semicolon.africa.DTOs.request.DryCleanerUpdateOrderRequest;
import com.semicolon.africa.DTOs.response.*;
import com.semicolon.africa.config.PasswordConfiguration;
import com.semicolon.africa.data.model.DryCleaner;
import com.semicolon.africa.data.model.Rider;
import com.semicolon.africa.data.repository.DryCleanerRepository;
import com.semicolon.africa.data.repository.RiderRepository;
import com.semicolon.africa.exception.*;
import com.semicolon.africa.service.interfaces.DryCleanerService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.semicolon.africa.utils.Mapper.getDryCleanerUpdateOrderResponse;
import static com.semicolon.africa.utils.Mapper.map;

@Service
@RequiredArgsConstructor
public class DryCleanerServiceImpl implements DryCleanerService {

    private final DryCleanerRepository dryCleanerRepository;
    private final ObjectMapper mapper;
    private final RiderRepository riderRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DryCleanerAddOrderResponse sendOrder(DryCleanerAddOrderRequest dryCleanerAddOrderRequest) {
        validateDryCleanerEmailAddress(dryCleanerAddOrderRequest.getEmail());
        DryCleaner dryCleaner = new DryCleaner();
        if(dryCleaner == null){
            throw new InvalidOrEmptyFieldsException("Field must not be null");
        }
        map(dryCleanerAddOrderRequest, dryCleaner);
        validateDryCleanerLogin(dryCleaner);
        dryCleanerRepository.save(dryCleaner);
        DryCleanerAddOrderResponse dryCleanerAddOrderResponse = new DryCleanerAddOrderResponse();
        dryCleanerAddOrderResponse.setDryCleanerId(dryCleaner.getId());
        dryCleanerAddOrderResponse.setMessage("Ordered sent successfully");
        return dryCleanerAddOrderResponse;
    }
    private void validateDryCleanerLogin(DryCleaner  dryCleaner) {
        if(!dryCleaner.isLoggedIn())throw new DryCleanerNotLoggedInException("Login first");
    }

    @Override
    public DryCleanerUpdateOrderResponse updateOrder(DryCleanerUpdateOrderRequest dryCleanerUpdateOrderRequest) {
        DryCleaner dryCleaner = findDryCleanerById(dryCleanerUpdateOrderRequest.getDryCleanerId());
        if(dryCleaner == null){
            throw new InvalidOrEmptyFieldsException("Field must not be null");
        }
        dryCleaner.setFirstName(dryCleanerUpdateOrderRequest.getFirstName());
        dryCleaner.setLastName(dryCleanerUpdateOrderRequest.getLastName());
        dryCleaner.setCompanyName(dryCleanerUpdateOrderRequest.getCompanyName());
        dryCleaner.setPhoneNumber(dryCleanerUpdateOrderRequest.getPhoneNumber());
        dryCleaner.setEmail(dryCleanerUpdateOrderRequest.getEmail());
        dryCleanerRepository.save(dryCleaner);
        DryCleanerUpdateOrderResponse dryCleanerUpdateOrderResponse = getDryCleanerUpdateOrderResponse();
        return dryCleanerUpdateOrderResponse;
    }
    
    private DryCleaner findDryCleanerById(Long dryCleanerId) {
        return dryCleanerRepository.findDryCleanerById(dryCleanerId).
                orElseThrow(()-> new DryCleanerIdNotFoundException("Dry cleaner not found id"));
    }

    @Override
    public DryCleanerDeleteOrderResponse deleteOrder(String id) {
        return null;
    }

    @Override
    public DryCleanerRegisterResponse register(DryCleanerRegisterRequest dryCleanerRegisterRequest) {
        validateDryCleanerEmailAddress(dryCleanerRegisterRequest.getEmail());
        DryCleaner dryCleaner = new DryCleaner();
        dryCleaner.setFirstName(dryCleanerRegisterRequest.getFirstName());
        dryCleaner.setEmail(dryCleanerRegisterRequest.getEmail());
        dryCleaner.setPhoneNumber(dryCleanerRegisterRequest.getPhoneNumber());
        dryCleaner.setPassword(passwordEncoder.encode(dryCleanerRegisterRequest.getPassword()));
        dryCleanerRepository.save(dryCleaner);
        DryCleanerRegisterResponse  dryCleanerRegisterResponse = new DryCleanerRegisterResponse();
        return dryCleanerRegisterResponseMapper(dryCleanerRegisterResponse, dryCleaner);
    }

    private static DryCleanerRegisterResponse dryCleanerRegisterResponseMapper(DryCleanerRegisterResponse dryCleanerRegisterResponse, DryCleaner dryCleaner) {
        dryCleanerRegisterResponse.setDryCleanerId(dryCleaner.getId());
        dryCleanerRegisterResponse.setEmail(dryCleaner.getEmail());
        dryCleanerRegisterResponse.setPhoneNumber(dryCleaner.getPhoneNumber());
        dryCleanerRegisterResponse.setPassword(dryCleaner.getPassword());
        dryCleanerRegisterResponse.setMessage("Hello Registered successfully");
        return dryCleanerRegisterResponse;
    }
    private void validateDryCleanerEmailAddress(String email) {
        boolean isDryCleanerExist = dryCleanerRepository.existsByEmail(email);
        if(isDryCleanerExist){
            throw new DryCleanerAlreadyExistException("Dry Cleaner already exist");
        }
    }
    @Override
    public DryCleanerLoginResponse login(DryCleanerLoginRequest dryCleanerLoginRequest) {
        DryCleaner dryCleaner = findDryCleanerByEmail(dryCleanerLoginRequest.getEmail());
        dryCleaner.setEmail(dryCleanerLoginRequest.getEmail());
        validateDryCleanerPassword(dryCleaner,dryCleanerLoginRequest.getPassword());
        dryCleaner.setPassword((dryCleanerLoginRequest.getPassword()));
        dryCleaner.setLoggedIn(true);
        dryCleanerRepository.save(dryCleaner);
        DryCleanerLoginResponse loginDryCleanerResponse = new DryCleanerLoginResponse();
        loginDryCleanerResponse.setEmail(dryCleaner.getEmail());
        loginDryCleanerResponse.setPassword(loginDryCleanerResponse.getPassword());
        loginDryCleanerResponse.setMessage("Login Successfully");
        return loginDryCleanerResponse;
    }


    private void validateDryCleanerPassword(DryCleaner dryCleaner, String password) {
        if(passwordEncoder.matches(dryCleaner.getPassword(), password)) throw new InCorrectPassword("Invalid dry-cleaner password");
    }

    private DryCleaner findDryCleanerByEmail(String dryCleanerEmail) {
        return dryCleanerRepository.findDryCleanerByEmail(dryCleanerEmail)
                .orElseThrow(()-> new DryCleanerNotFoundException("Dry Cleaner not found"));
    }

    @Override
    public CheckForRiderAvailabilty isAvailable() {
        return null;
    }
}
