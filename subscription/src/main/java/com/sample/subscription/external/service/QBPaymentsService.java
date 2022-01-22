package com.sample.subscription.external.service;

import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
public class QBPaymentsService extends AbstractValidationService {

    public QBPaymentsService(final RetryTemplate retryTemplate) {
        super(retryTemplate);
    }

    @Override
    protected String getBaseUrl() {
        return "http://localhost:9092/validate";
    }
}
