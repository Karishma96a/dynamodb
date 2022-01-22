package com.sample.subscription.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DynamoDBDocument
public class Identifier {

    @DynamoDBAttribute
    private String pan;

    @DynamoDBAttribute
    private String ein;
}