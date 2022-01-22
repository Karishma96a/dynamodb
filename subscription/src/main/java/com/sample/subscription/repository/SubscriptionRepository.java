package com.sample.subscription.repository;

import com.sample.subscription.model.PrimaryKey;
import com.sample.subscription.model.Subscription;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, PrimaryKey> {
    List<Subscription> findByCustomerIdAndSkStartingWith(String customerId, String sk);
}