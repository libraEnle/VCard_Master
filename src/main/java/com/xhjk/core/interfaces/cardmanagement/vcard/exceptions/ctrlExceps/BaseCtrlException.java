package com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.ctrlExceps;

public class BaseCtrlException extends RuntimeException {
    public BaseCtrlException() {
    }

    public BaseCtrlException(String message) {
        super(message);
    }

    public BaseCtrlException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseCtrlException(Throwable cause) {
        super(cause);
    }

    public BaseCtrlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
