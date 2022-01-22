package com.sample.subscription.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBDocument
@EqualsAndHashCode
public class PrimaryKey implements Serializable {

    @DynamoDBHashKey
    @DynamoDBAttribute(attributeName = "PK")
    private String pk;

    @DynamoDBRangeKey
    @DynamoDBAttribute(attributeName = "SK")
    private String sk;
}