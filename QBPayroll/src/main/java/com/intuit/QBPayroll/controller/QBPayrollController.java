package com.intuit.QBPayroll.controller;

import com.intuit.QBPayroll.model.BusinessProfile;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validate")
public class QBPayrollController {

    @PostMapping(value = "/business-profile", headers = "Accept=application/json")
    public Boolean validate(@RequestBody BusinessProfile businessProfile) {
        System.out.println(businessProfile);
        return true;
    }
}