package com.semicolon.africa.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.africa.DTOs.request.*;
import com.semicolon.africa.DTOs.response.*;
import com.semicolon.africa.data.model.DryCleaner;
import com.semicolon.africa.data.model.OrderPlacement;
import com.semicolon.africa.data.repository.DryCleanerRepository;
import com.semicolon.africa.data.repository.OrderRepository;
import com.semicolon.africa.exception.*;
import com.semicolon.africa.service.interfaces.DryCleanerService;
import com.semicolon.africa.service.interfaces.OrderPlacementService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DryCleanerServiceImpl implements DryCleanerService {

    private final DryCleanerRepository dryCleanerRepository;
    private final ObjectMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final OrderPlacementService orderPlacementService;
    private final OrderRepository orderRepository;

//    @Override
//    public PlaceOrderResponse sendOrder(PlaceOrderRequest placeOrderRequest) {
//        PlaceOrderResponse placeOrderResponse = orderPlacementService.placeOrder(placeOrderRequest);
//        DryCleaner dryCleaner = findDryCleanerById(placeOrderRequest.getDryCleanerId());
//        validateDryCleanerEmailAddress(dryCleaner.getEmail());
//        OrderPlacement orderPlacement = findOrderPlacementById(placeOrderRequest.getOrderPlacementId());
//        List<OrderPlacement> orderPlacementList = dryCleaner.getOrderPlacement();
//        orderPlacementList.add(orderPlacement);
//        validateDryCleanerLogin(dryCleaner);
//        dryCleanerRepository.save(dryCleaner);
//        placeOrderResponse.setMessage("Ordered sent successfully");
//        return placeOrderResponse;
//    }
    private OrderPlacement findOrderPlacementById(Long orderPlacementId) {
        return orderRepository.findOrderById(orderPlacementId);
    }

    private void validateDryCleanerLogin(DryCleaner  dryCleaner) {
        if(!dryCleaner.isLoggedIn())throw new DryCleanerNotLoggedInException("Login first");
    }

    @Override
    public PostServiceResponse postService(PostServiceRequest postServiceRequest) {
        DryCleaner dryCleaner = new DryCleaner();
        dryCleaner.setServiceType(postServiceRequest.getServiceType());
        dryCleaner.setDescription(postServiceRequest.getDescription());
        dryCleaner.setPrice(postServiceRequest.getPrice());
        dryCleaner.setCompanyName(postServiceRequest.getCompanyName());
        dryCleaner.setPhoneNumber(postServiceRequest.getPhoneNumber());
        dryCleaner.setDatePosted(postServiceRequest.getDatePosted());
        dryCleaner = dryCleanerRepository.save(dryCleaner);
        return DryCleanerPostResponseMapper(dryCleaner);
    }

    private static PostServiceResponse DryCleanerPostResponseMapper(DryCleaner dryCleaner) {
        PostServiceResponse postServiceResponse = new PostServiceResponse();
        postServiceResponse.setServiceType(dryCleaner.getServiceType());
        postServiceResponse.setDescription(dryCleaner.getDescription());
        postServiceResponse.setPrice(dryCleaner.getPrice());
        postServiceResponse.setCompanyName(dryCleaner.getCompanyName());
        postServiceResponse.setPhoneNumber(dryCleaner.getPhoneNumber());
        postServiceResponse.setDatePosted(dryCleaner.getDatePosted());
        postServiceResponse.setMessage("Posted successfully");
        return postServiceResponse;
    }

    @Override
    public ReceiveOrderResponse receiveOrder(ReceiveOrderRequest receiveOrderRequest) {
        return null;
    }
    @Override
    public DryCleanerLogoutResponse logout() {

        return null;
    }

    private DryCleaner findDryCleanerById(Long dryCleanerId) {
        return dryCleanerRepository.findDryCleanerById(dryCleanerId).
                orElseThrow(()-> new DryCleanerIdNotFoundException("Dry cleaner not found id"));
    }

    @Override
    public DryCleanerRegisterResponse register(DryCleanerRegisterRequest dryCleanerRegisterRequest) {
        validateDryCleanerEmailAddress(dryCleanerRegisterRequest.getEmail());
        DryCleaner dryCleaner = new DryCleaner();
        dryCleaner.setFullName(dryCleanerRegisterRequest.getFirstName());
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
        boolean isDryCleanerExist = dryCleanerRepository.findDryCleanerByEmail(email).isPresent();;
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
        if(!passwordEncoder.matches(dryCleaner.getPassword(), password)) throw new InCorrectPassword("Invalid dry-cleaner password");
    }

    private DryCleaner findDryCleanerByEmail(String dryCleanerEmail) {
        return dryCleanerRepository.findDryCleanerByEmail(dryCleanerEmail)
                .orElseThrow(()-> new DryCleanerNotFoundException("Dry Cleaner not found"));
    }

    @Override
    public List<DryCleaner> findAllDryCleaners() {
        return dryCleanerRepository.findAllBy();
    }

    @Override
    public List<DryCleaner> findDryCleanerByFullName(String fullName) {
        return List.of();
    }


    @Override
    public List<DryCleaner> findDryCleanerByCompanyName(String companyName) {
        return dryCleanerRepository.findDryCleanerByCompanyName(companyName);
    }
    @Override
    public List<DryCleaner> findDyrCleanerByPhoneNumber(String phoneNumber) {
        return dryCleanerRepository.findDryCleanerByPhoneNumber(phoneNumber);
    }
    @Override
    public Long countAllDryCleaners() {
        return dryCleanerRepository.count();
    }
}
