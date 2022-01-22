package com.sample.subscription.external.service;

import com.sample.subscription.model.BusinessProfile;

import java.util.concurrent.CompletableFuture;

public interface IValidationService {

    CompletableFuture<Boolean> validateBusinessProfile(BusinessProfile businessProfile);
}
