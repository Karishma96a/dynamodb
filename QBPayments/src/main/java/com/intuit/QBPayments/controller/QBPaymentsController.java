package com.intuit.QBPayments.controller;

import com.intuit.QBPayments.model.BusinessProfile;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/validate")
public class QBPaymentsController {

    @PostMapping(value = "/business-profile", headers = "Accept=application/json")
    public Boolean validate(@RequestBody BusinessProfile businessProfile) {
        System.out.println(businessProfile);
        return true;
    }
}