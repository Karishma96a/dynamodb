package com.intuit.QBAccounting.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Identifier {

    private String pan;

    private String ein;
}