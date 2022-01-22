package com.sample.subscription.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBDocument
public class BusinessProfile {

    @DynamoDBAttribute
    private String companyName;

    @DynamoDBAttribute
    private String legalName;

    @DynamoDBAttribute
    private Address businessAddress;

    @DynamoDBAttribute
    private Address legalAddress;

    @DynamoDBAttribute
    private Identifier taxIdentifier;

    @DynamoDBAttribute
    private String email;

    @DynamoDBAttribute
    private String website;

}
