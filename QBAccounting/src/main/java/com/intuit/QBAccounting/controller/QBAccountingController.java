package com.intuit.QBAccounting.controller;

import com.intuit.QBAccounting.model.BusinessProfile;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validate")
public class QBAccountingController {

    @PostMapping(value = "/business-profile", headers = "Accept=application/json")
    public Boolean validate(@RequestBody BusinessProfile businessProfile) {
        System.out.println(businessProfile);
        return true;
    }
}