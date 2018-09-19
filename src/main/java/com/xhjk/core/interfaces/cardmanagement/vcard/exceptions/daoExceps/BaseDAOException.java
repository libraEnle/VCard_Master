package com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.daoExceps;

public class BaseDAOException extends RuntimeException{
    public BaseDAOException() {
    }

    public BaseDAOException(String message) {
        super(message);
    }

    public BaseDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseDAOException(Throwable cause) {
        super(cause);
    }

    public BaseDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
