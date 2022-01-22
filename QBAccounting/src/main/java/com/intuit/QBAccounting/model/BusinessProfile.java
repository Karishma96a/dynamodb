package com.intuit.QBAccounting.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BusinessProfile {
    private String companyName;

    private String legalName;

    private Address businessAddress;

    private Address legalAddress;

    private Identifier taxIdentifier;

    private String email;

    private String website;
}
