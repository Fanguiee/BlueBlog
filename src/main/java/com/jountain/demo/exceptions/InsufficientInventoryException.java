package com.jountain.demo.exceptions;

public class InsufficientInventoryException extends RuntimeException {
    public InsufficientInventoryException(String s) {
        super(s);
    }
}
