package com.udacity.jwdnd.course1.cloudstorage.Exception;

public class CredentialException extends RuntimeException{
    public CredentialException(String message) {
        super(message);
    }
    public CredentialException(String message, Throwable cause) {
        super(message, cause);
    }
}
