package org.example.exception;

public class InvalidChangeAttemptException extends RuntimeException{
    public InvalidChangeAttemptException(){

    }
    public InvalidChangeAttemptException(Throwable cause){
        super(cause);
    }
}
