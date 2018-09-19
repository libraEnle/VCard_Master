package com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.servExceps;

public class BaseServException extends RuntimeException{
    public BaseServException() {
    }

    public BaseServException(String message) {
        super(message);
    }

    public BaseServException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseServException(Throwable cause) {
        super(cause);
    }

    public BaseServException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
