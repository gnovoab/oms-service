
//Namespae
package com.ecommerce.oms.exception;

/**
 * Class that represents an exception when a resource is not found
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public ResourceNotFoundException(final Throwable cause) {
        super(cause);
    }
}
