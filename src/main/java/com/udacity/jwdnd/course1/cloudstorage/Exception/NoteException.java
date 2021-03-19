package com.udacity.jwdnd.course1.cloudstorage.Exception;

public class NoteException extends RuntimeException{
    public NoteException(String message) {
        super(message);
    }
    public NoteException(String message, Throwable cause) {
        super(message, cause);
    }
}

