package com.intuit.TSheets.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Address {

    private String line1;

    private String line2;

    private String city;

    private String state;

    private String zip;

    private String country;
}
