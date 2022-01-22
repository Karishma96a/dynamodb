package com.sample.subscription.external.service;

import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
public class TSheetsService extends AbstractValidationService {

    public TSheetsService(final RetryTemplate retryTemplate) {
        super(retryTemplate);
    }

    @Override
    protected String getBaseUrl() {
        return "http://localhost:9093/validate";
    }
}
