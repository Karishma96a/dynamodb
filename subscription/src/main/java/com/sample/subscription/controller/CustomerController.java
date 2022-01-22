package com.sample.subscription.controller;

import com.sample.subscription.model.BusinessProfile;
import com.sample.subscription.model.Customer;
import com.sample.subscription.repository.CustomerRepository;
import com.sample.subscription.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequiredArgsConstructor
@Api(tags = "Operations pertaining to customers")
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerRepository customerRepository;

    @ApiOperation(value = "Add a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added customer")
    })
    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody final Customer customer) {
        customerService.createCustomer(customer);
        return ResponseEntity.ok("Customer was created");
    }

    @ApiOperation(value = "Update a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated business profile")
    })
    @PutMapping("/customer/business-profile/{customer-id}")
    public ResponseEntity<?> updateBusinessProfile(@RequestBody final BusinessProfile businessProfile, @PathVariable("customer-id") final String custId) throws ExecutionException, InterruptedException, TimeoutException {
        customerService.updateBusinessProfile(businessProfile, custId);
        return ResponseEntity.ok("Business Profile was updated");
    }

    @ApiOperation(value = "Update a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated business profile"),
            @ApiResponse(code = 406, message = "Invalid business profile data"),
            @ApiResponse(code = 500, message = "Server is not responding")
    })
    @PatchMapping(value = "/customer/business-profile/{customer-id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> partialBusinessProfileUpdate(@RequestBody Map<String, Object> updates, @PathVariable("customer-id") String custId) throws ExecutionException, InterruptedException, TimeoutException {
        customerService.updatePartialBusinessProfile(updates, custId);
        return ResponseEntity.ok("Business profile was updated");
    }
}