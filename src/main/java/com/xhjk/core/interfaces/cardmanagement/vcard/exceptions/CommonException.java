package com.xhjk.core.interfaces.cardmanagement.vcard.exceptions;

import com.xhjk.core.interfaces.cardmanagement.vcard.Utils.ExceptionCustomConfigUtils;

public class CommonException extends RuntimeException {

    private String errorCode;
    private String errorMsg;

    public final static String COMMON_CANNOT_COMPARE = "0001";
    public final static String COMMON_CLASS_NOTFOUND = "0002";

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public CommonException(String errorCode) {
        this.errorCode = errorCode;
        this.errorMsg = ExceptionCustomConfigUtils.analyzeErrorCode4ErrorMsg(LogSerialException.class, errorCode);
    }

    public CommonException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public CommonException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public CommonException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = ExceptionCustomConfigUtils.analyzeErrorCode4ErrorMsg(LogSerialException.class, errorCode);
    }

    public CommonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }
}
