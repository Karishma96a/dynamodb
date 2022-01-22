package com.intuit.TSheets.controller;

import com.intuit.TSheets.model.BusinessProfile;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validate")
public class TSheetsController {

    @PostMapping(value = "/business-profile", headers = "Accept=application/json")
    public Boolean validate(@RequestBody BusinessProfile businessProfile) {
        System.out.println(businessProfile);
        return true;
    }
}