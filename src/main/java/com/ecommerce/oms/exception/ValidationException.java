package com.ecommerce.oms.exception;

/**
 * Class that represents an exception when a validation error occurs
 */
public class ValidationException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param message
     */
    public ValidationException(String message) {
        super(message);
    }
}
