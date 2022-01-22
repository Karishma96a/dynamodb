package com.sample.subscription.service;

import com.sample.subscription.Constants;
import com.sample.subscription.model.Customer;
import com.sample.subscription.model.Product;
import com.sample.subscription.model.Subscription;
import com.sample.subscription.repository.CustomerRepository;
import com.sample.subscription.repository.ProductRepository;
import com.sample.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    @Autowired
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final ProductRepository productRepository;

    public void createSubscription(final Subscription subscription) {
        final Customer customer = customerRepository.findByCustIdAndSk(subscription.getCustomerId(), Constants.CUSTOMER_PREFIX + subscription.getCustomerId());
        if (customer == null) {
            throw new IllegalArgumentException("Invalid customer input!");
        }

        final Product product = productRepository.findBySkAndProductTypeNotNull(Constants.PRODUCT_PREFIX + subscription.getProductId());
        if(product == null) {
            throw new IllegalArgumentException("Invalid product input!");
        }

        subscriptionRepository.save(subscription);
    }
}
