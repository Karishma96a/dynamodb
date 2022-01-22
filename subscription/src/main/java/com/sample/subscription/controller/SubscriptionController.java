package com.sample.subscription.controller;

import com.sample.subscription.model.Subscription;
import com.sample.subscription.service.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "Operations pertaining to subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @ApiOperation(value = "Create a subscription")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created subscription"),
            @ApiResponse(code = 406, message = "Invalid data")
    })
    @PostMapping("/subscription")
    public ResponseEntity<?> createSubscription(@RequestBody final Subscription subscriptionRequest) {
        subscriptionService.createSubscription(subscriptionRequest);
        return ResponseEntity.ok("Subscription was created");
    }
}