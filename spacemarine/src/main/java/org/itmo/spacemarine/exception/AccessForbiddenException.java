package org.itmo.spacemarine.exception;

public class AccessForbiddenException extends RuntimeException{

    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message) {
        super(message);
    }
}
