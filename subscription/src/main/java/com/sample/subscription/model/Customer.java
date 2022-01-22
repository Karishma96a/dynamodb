package com.sample.subscription.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.sample.subscription.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Data
@NoArgsConstructor
@DynamoDBDocument
@DynamoDBTable(tableName = Constants.TABLE_NAME)
public class Customer {

    @Id
    @DynamoDBIgnore
    private PrimaryKey primaryKey;

    @DynamoDBAttribute(attributeName = "CustomerId")
    private String custId;

    @DynamoDBAttribute(attributeName = "BusinessProfile")
    private BusinessProfile businessProfile;

    @DynamoDBHashKey(attributeName = "PK")
    public String getPk() {
        return Constants.CUSTOMER_PREFIX + custId;
    }

    public void setPk(final String pk) {
        if (primaryKey == null) {
            primaryKey = new PrimaryKey();
        }
        primaryKey.setPk(pk);
    }

    @DynamoDBRangeKey(attributeName = "SK")
    public String getSk() {
        return Constants.CUSTOMER_PREFIX + custId;
    }

    public void setSk(final String sk) {
        if (primaryKey == null) {
            primaryKey = new PrimaryKey();
        }
        primaryKey.setSk(sk);
    }
}
