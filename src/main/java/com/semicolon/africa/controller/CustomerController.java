package com.semicolon.africa.controller;

import com.semicolon.africa.DTOs.request.CustomerRegistrationRequest;
import com.semicolon.africa.DTOs.response.ApiResponse;
import com.semicolon.africa.DTOs.response.CustomerRegistrationResponse;
import com.semicolon.africa.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@Controller
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


}
