package com.hsstudy.GuessMyMBTI.api.exception;

public class CEmailSignupFailedException extends RuntimeException{
    public CEmailSignupFailedException() {
        super();
    }

    public CEmailSignupFailedException(String message) {
        super(message);
    }

    public CEmailSignupFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}