package com.yash.pms.exception;

public class ProductAlreadyExistException extends Exception {
    public ProductAlreadyExistException(String msg) {
    	super(msg);
    }
}
