package org.example.monad.ex02;

public class GenericException extends RuntimeException {

    public GenericException(String message){
        super(message);
    }

    public GenericException(Throwable t){
        super(t);
    }
}
