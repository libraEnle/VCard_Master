package com.xhjk.core.interfaces.cardmanagement.vcard.exceptions;

import com.xhjk.core.interfaces.cardmanagement.vcard.Utils.ExceptionCustomConfigUtils;

public class WSXHJKClientException extends RuntimeException {

    private String errorCode;
    private String errorMsg;

    public static final String WSCLIENT_SUCCESS_NORESULT = "0001";
    public static final String WSCLIENT_FAIL_WITHERINFLIT = "0002";
    public static final String WSCLIENT_FAIL_WITHOUTERINFLIT = "0003";
    public static final String WSCLIENT_FAIL_NOSUCHERCODE = "0004";
    public static final String WSCLIENT_FAIL_NORESP = "0005";

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public WSXHJKClientException(String errorCode) {
        this.errorCode = errorCode;
        this.errorMsg = ExceptionCustomConfigUtils.analyzeErrorCode4ErrorMsg(SpecificationException.class, this.errorCode);
    }

    public WSXHJKClientException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public WSXHJKClientException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public WSXHJKClientException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = ExceptionCustomConfigUtils.analyzeErrorCode4ErrorMsg(SpecificationException.class, this.errorCode);
    }

    public WSXHJKClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

}