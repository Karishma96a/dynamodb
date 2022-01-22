package com.sample.subscription.repository;

import com.sample.subscription.model.PrimaryKey;
import com.sample.subscription.model.Product;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface ProductRepository extends CrudRepository<Product, PrimaryKey> {
    Product findBySkAndProductTypeNotNull(String sk);
}