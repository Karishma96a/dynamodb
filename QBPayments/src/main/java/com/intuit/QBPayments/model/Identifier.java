package com.intuit.QBPayments.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Identifier {

    private String pan;

    private String ein;
}