package com.sample.subscription.exception;

public class UnsupportedProductException extends RuntimeException {

    public UnsupportedProductException(final String message) {
        super(message);
    }
}
