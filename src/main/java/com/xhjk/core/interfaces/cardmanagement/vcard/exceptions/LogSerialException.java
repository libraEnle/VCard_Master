package com.xhjk.core.interfaces.cardmanagement.vcard.exceptions;

import com.xhjk.core.interfaces.cardmanagement.vcard.Utils.ExceptionCustomConfigUtils;

public class LogSerialException extends RuntimeException {

    private String errorCode;
    private String errorMsg;

    public final static String LOGRECORD_ASPECT_GETMETHOD_NOSUCHMETHOD = "0001";
    public final static String LOGRECORD_DAO_INSERT_FAIL = "0002";
    public final static String LOGRECORD_DAO_INSERT_NOPARAMTRACE = "0003";
    public final static String LOGRECORD_CONFIG_NOSUCHCLS = "0004";
    public final static String LOGRECORD_CONFIG_DUPLICATEBUSCODE = "0005";
    public final static String LOGRECORD_PROCESS_DUE_EXCEP = "0006";

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public LogSerialException(String errorCode) {
        this.errorCode = errorCode;
        this.errorMsg = ExceptionCustomConfigUtils.analyzeErrorCode4ErrorMsg(LogSerialException.class, errorCode);
    }

    public LogSerialException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public LogSerialException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public LogSerialException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = ExceptionCustomConfigUtils.analyzeErrorCode4ErrorMsg(LogSerialException.class, errorCode);
    }

    public LogSerialException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }
}
