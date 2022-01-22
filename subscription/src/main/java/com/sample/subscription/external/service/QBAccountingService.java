package com.sample.subscription.external.service;

import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
public class QBAccountingService extends AbstractValidationService {

    public QBAccountingService(final RetryTemplate retryTemplate) {
        super(retryTemplate);
    }

    @Override
    protected String getBaseUrl() {
        return "http://localhost:9090/validate";
    }
}
