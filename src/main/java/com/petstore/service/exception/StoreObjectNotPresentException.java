package com.petstore.service.exception;

public class StoreObjectNotPresentException extends Throwable{

    public StoreObjectNotPresentException(String message) {
        super(message);
    }
}
