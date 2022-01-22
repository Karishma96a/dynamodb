package com.sample.subscription.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.subscription.Constants;
import com.sample.subscription.ProductServiceResolver;
import com.sample.subscription.exception.ValidationException;
import com.sample.subscription.external.service.IValidationService;
import com.sample.subscription.model.BusinessProfile;
import com.sample.subscription.model.Customer;
import com.sample.subscription.model.Product;
import com.sample.subscription.model.Subscription;
import com.sample.subscription.repository.CustomerRepository;
import com.sample.subscription.repository.ProductRepository;
import com.sample.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final ProductServiceResolver productServiceResolver;

    public void createCustomer(final Customer customer) {
        customerRepository.save(customer);
    }

    public void updateBusinessProfile(final BusinessProfile businessProfile, final String customerId) throws ExecutionException, InterruptedException, TimeoutException {
        final Customer customer = getCustomerFromDb(customerId);
        updateBusinessProfileForCustomer(businessProfile, customer);
    }

    public void updatePartialBusinessProfile(final Map<String, Object> updates, final String customerId) throws ExecutionException, InterruptedException, TimeoutException {
        final Customer customer = getCustomerFromDb(customerId);
        final BusinessProfile businessProfile = customer.getBusinessProfile();

        final BusinessProfile updatedBusinessProfile = patchBusinessProfile(businessProfile, updates);
        updateBusinessProfileForCustomer(updatedBusinessProfile, customer);
    }

    public void updateBusinessProfileForCustomer(final BusinessProfile businessProfile, final Customer customer) throws  ExecutionException, InterruptedException, TimeoutException {
        final List<Subscription> subscriptions = getSubscriptions(customer.getCustId());
        boolean isValid = performValidations(businessProfile, subscriptions);

        if (isValid) {
            customer.setBusinessProfile(businessProfile);
            customerRepository.save(customer);
            return;
        }

        throw new ValidationException("Invalid Business Profile");
    }

    private List<Subscription> getSubscriptions(final String customerId) {
        return subscriptionRepository.findByCustomerIdAndSkStartingWith(customerId, Constants.PRODUCT_PREFIX);
    }

    private Customer getCustomerFromDb(final String custId) {
        return customerRepository.findByCustIdAndSk(custId, Constants.CUSTOMER_PREFIX + custId);
    }

    private boolean performValidations(final BusinessProfile businessProfile, final List<Subscription> subscriptions) throws ExecutionException, InterruptedException, TimeoutException {
        final List<CompletableFuture<Boolean>> completableFutures = performValidationsAsynchronously(businessProfile, subscriptions);

        for (final CompletableFuture<Boolean> completableFuture : completableFutures) {
            if (!completableFuture.get(7, TimeUnit.SECONDS)) {
                return false;
            }
        }
        return true;
    }

    private BusinessProfile patchBusinessProfile(final BusinessProfile businessProfile, final Map<String, Object> updates) {
        final Map<String, Object> updatedBusinessProfile = new ObjectMapper().convertValue(businessProfile, Map.class);
        updatedBusinessProfile.putAll(updates);

        return new ObjectMapper().convertValue(updatedBusinessProfile, BusinessProfile.class);
    }


    private List<CompletableFuture<Boolean>> performValidationsAsynchronously(final BusinessProfile businessProfile, final List<Subscription> subscriptions) {
        final List<CompletableFuture<Boolean>> completableFutures = new ArrayList<>();

        for (final Subscription subscription : subscriptions) {
            final String productId = subscription.getProductId();
            final Product product = productRepository.findBySkAndProductTypeNotNull(Constants.PRODUCT_PREFIX + productId);

            final IValidationService validationController = productServiceResolver.getProductServiceResolver(product);
            completableFutures.add(validationController.validateBusinessProfile(businessProfile));
        }
        return completableFutures;
    }
}
