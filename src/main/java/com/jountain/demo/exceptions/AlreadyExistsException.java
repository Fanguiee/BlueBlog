package com.jountain.demo.exceptions;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String s) {
        super(s);
    }
}
