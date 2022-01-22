package com.sample.subscription.external.service;

import com.sample.subscription.model.BusinessProfile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Slf4j
@AllArgsConstructor
public abstract class AbstractValidationService implements IValidationService {

    @Autowired
    private final RetryTemplate retryTemplate;

    @Retryable
    private CompletableFuture<Boolean> validateBusinessProfileWithRetries(final BusinessProfile businessProfile) {
        return retryTemplate.execute(context -> {
            if (context.getRetryCount() != 0) {
                log.info("Retrying " + getClass().getSimpleName() + " " + context.getRetryCount());
            }

            final HttpEntity<BusinessProfile> request = getHttpEntityRequest(businessProfile);
            return CompletableFuture.completedFuture(new RestTemplate()
                    .postForObject(getBaseUrl() + "/business-profile", request, Boolean.class));
        });
    }

    @Async
    public CompletableFuture<Boolean> validateBusinessProfile(final BusinessProfile businessProfile) {
        return validateBusinessProfileWithRetries(businessProfile);
    }

    protected abstract String getBaseUrl();

    private HttpEntity<BusinessProfile> getHttpEntityRequest(BusinessProfile businessProfile) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(businessProfile, headers);
    }
}
