package com.sample.subscription.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBDocument
public class Address {

    @DynamoDBAttribute
    private String line1;

    @DynamoDBAttribute
    private String line2;

    @DynamoDBAttribute
    private String city;

    @DynamoDBAttribute
    private String state;

    @DynamoDBAttribute
    private String zip;

    @DynamoDBAttribute
    private String country;
}
