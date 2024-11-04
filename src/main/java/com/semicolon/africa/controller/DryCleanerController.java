package com.semicolon.africa.controller;

import com.semicolon.africa.DTOs.request.*;
import com.semicolon.africa.DTOs.response.*;
import com.semicolon.africa.data.model.DryCleaner;
import com.semicolon.africa.service.interfaces.DryCleanerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/dry_cleaner")
@RequiredArgsConstructor
public class DryCleanerController {

    private final DryCleanerService dryCleanerService;

    @PostMapping("/registerDryCleaner")
    public ResponseEntity<?> registerDryCleaner(@RequestBody DryCleanerRegisterRequest dryCleanerRegisterRequest){
        try{
            DryCleanerRegisterResponse dryCleanerRegisterResponse = dryCleanerService.register(dryCleanerRegisterRequest);
            return new ResponseEntity<>(new ApiResponse (true, dryCleanerRegisterResponse), HttpStatus.CREATED);
        }catch(Exception exception){
          return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DryCleanerLoginRequest dryCleanerLoginRequest){
        try{
            DryCleanerLoginResponse dryCleanerLoginResponse = dryCleanerService.login(dryCleanerLoginRequest);
            return new ResponseEntity<>(new ApiResponse(true, dryCleanerLoginResponse), HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/sendOrder")
//    public ResponseEntity<?> sendOrder(@RequestBody PlaceOrderRequest dryCleanerAddOrderRequest){
//        try{
////            PlaceOrderResponse dryCleanerAddOrderResponse = dryCleanerService.sendOrder(dryCleanerAddOrderRequest);
//            return new ResponseEntity<>(new ApiResponse(true, dryCleanerAddOrderResponse), HttpStatus.OK);
//        }
//        catch(Exception exception){
//            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }

//    @PutMapping("/retrieveOrders")
//    public ResponseEntity<?> retrieveOrders(@RequestBody DryCleanerUpdateOrderRequest dryCleanerUpdateOrderRequest){
//        try{
//            DryCleanerUpdateOrderResponse dryCleanerUpdateOrderResponse = dryCleanerService.updateOrder(dryCleanerUpdateOrderRequest);
//            return new ResponseEntity<>(new ApiResponse(true, dryCleanerUpdateOrderResponse), HttpStatus.OK);
//        }
//        catch(Exception exception){
//            return new ResponseEntity<>(new ApiResponse(false, exception), HttpStatus.BAD_REQUEST);
//        }
//    }
//    @DeleteMapping("deleteOrder/{id}")
//    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
//        try{
//            DryCleanerDeleteOrderResponse dryCleanerDeleteOrderResponse = dryCleanerService.deleteOrder(id);
//            return new ResponseEntity<>(new ApiResponse(true, dryCleanerDeleteOrderResponse), HttpStatus.OK);
//        }
//        catch(Exception exception){
//            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
    @GetMapping()
    public ResponseEntity<?> findAllDryCleaner(){
       try{
           List<DryCleaner> getAllDryCleaner = dryCleanerService.findAllDryCleaners();
           return new ResponseEntity<>(new ApiResponse(true, getAllDryCleaner), HttpStatus.OK);
       }
       catch(Exception exceptions) {
           return new ResponseEntity<>(new ApiResponse(false, exceptions.getMessage()), HttpStatus.BAD_REQUEST);
       }
    }
//    @GetMapping("/firstName/")
//    public ResponseEntity<?> searchForDryCleanerByFirstName(@PathVariable String firstName){
//        try{
//            List<DryCleaner> findByFirstName = dryCleanerService.findDryCleanerByFirstName(firstName);
//            return new ResponseEntity<>(new ApiResponse(true, findByFirstName), HttpStatus.OK);
//        }
//        catch(Exception exception){
//            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()),HttpStatus.BAD_REQUEST);
//        }
//    }
//    @GetMapping("/lastName/")
//    public ResponseEntity<?> findDryCleanerByLastName(@PathVariable String lastName){
//        try{
//            List<DryCleaner> searchDryCleanerByLastName = dryCleanerService.findDryCleanerByLastName(lastName);
//            return new ResponseEntity<>(new ApiResponse(true, searchDryCleanerByLastName), HttpStatus.OK);
//        }catch(Exception exception){
//            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
//    @GetMapping("/firstName/lastName/")
//    public ResponseEntity<?> findDryCleanerByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName){
//        try{
//            List<DryCleaner> searchDryCleanerByFirstNameAndLastName = dryCleanerService.findDryCleanerByFirstNameAndLastName(firstName,lastName);
//            return new ResponseEntity<>(new ApiResponse(true, searchDryCleanerByFirstNameAndLastName),HttpStatus.OK);
//        }
//        catch(Exception exception){
//            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
    @GetMapping("/{phoneNumber}/")
    public ResponseEntity<?> findDryCleanerByPhoneNumber(@PathVariable String phoneNumber){
        try{
            List<DryCleaner> searchDryCleanerByPhoneNumber = dryCleanerService.findDyrCleanerByPhoneNumber(phoneNumber);
            return new ResponseEntity<>(new ApiResponse(true, searchDryCleanerByPhoneNumber), HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{companyName}/")
    public ResponseEntity<?> findDryCleanerByCompanyName(@PathVariable String companyName){
        try{
            List<DryCleaner> searchDryCleanerByCompanyName = dryCleanerService.findDryCleanerByCompanyName(companyName);
            return new ResponseEntity<>(new ApiResponse(true, searchDryCleanerByCompanyName),HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}