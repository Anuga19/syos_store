package com.syos.exceptions;

public class OrderPersistenceException extends RuntimeException {
    public OrderPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}