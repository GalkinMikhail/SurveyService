package com.example.fabriquetesttask.exception;

public class ResourceNotFoundException extends AbstractException {

    public ResourceNotFoundException(String msg,String techInfo) {
        super(msg,techInfo);
    }
}