package com.sample.subscription.repository;

import com.sample.subscription.model.Customer;
import com.sample.subscription.model.PrimaryKey;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface CustomerRepository extends CrudRepository<Customer, PrimaryKey> {
    Customer findByCustIdAndSk(String customerId, String sk);
}