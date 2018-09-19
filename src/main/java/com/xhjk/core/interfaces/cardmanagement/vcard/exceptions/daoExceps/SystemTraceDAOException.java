package com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.daoExceps;

import com.xhjk.core.interfaces.cardmanagement.vcard.Utils.ExceptionCustomConfigUtils;
import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.LogSerialException;

public class SystemTraceDAOException extends BaseDAOException {

    private String errorCode;
    private String errorMsg;

    public final static String SYSTEMTRACE_DAO_INSERT_ERROR = "0001";

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public SystemTraceDAOException(String errorCode) {
        this.errorCode = errorCode;
        this.errorMsg = ExceptionCustomConfigUtils.analyzeErrorCode4ErrorMsg(LogSerialException.class, errorCode);
    }

    public SystemTraceDAOException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public SystemTraceDAOException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public SystemTraceDAOException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = ExceptionCustomConfigUtils.analyzeErrorCode4ErrorMsg(LogSerialException.class, errorCode);
    }

    public SystemTraceDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }
}
