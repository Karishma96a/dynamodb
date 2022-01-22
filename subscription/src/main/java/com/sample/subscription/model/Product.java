package com.sample.subscription.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.sample.subscription.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamoDBDocument
@DynamoDBTable(tableName = Constants.TABLE_NAME)
@Builder
public class Product {

    @Id
    @DynamoDBIgnore
    private PrimaryKey primaryKey;

    @DynamoDBAttribute(attributeName = "ProductId")
    private String productId;

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "ProductType")
    private ProductType productType;

    @DynamoDBHashKey
    @DynamoDBAttribute(attributeName = "PK")
    public String getPk() {
        return Constants.PRODUCT_PREFIX + productId;
    }

    public void setPk(final String pk) {
        // intentionally left blank
        if (primaryKey == null) {
            primaryKey = new PrimaryKey();
        }
        primaryKey.setPk(pk);
    }

    @DynamoDBRangeKey
    @DynamoDBAttribute(attributeName = "SK")
    public String getSk() {
        return Constants.PRODUCT_PREFIX + productId;
    }

    public void setSk(final String sk) {
        // intentionally left blank
        if (primaryKey == null) {
            primaryKey = new PrimaryKey();
        }
        primaryKey.setSk(sk);
    }
}
