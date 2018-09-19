package com.xhjk.core.interfaces.cardmanagement.vcard.exceptions;

import com.xhjk.core.interfaces.cardmanagement.vcard.Utils.ExceptionCustomConfigUtils;

public class SpecificationException extends RuntimeException {

    private String errorCode;
    private String errorMsg;

    public static final String SEARCH_CUSTOM_FIELDLIST_EMPTY = "0001";
    public static final String SEARCH_CUSTOM_CONFIGMAP_EMPTY = "0002";
    public static final String SEARCH_CUSTOM_CONFIG_EMPTY = "0003";
    public static final String SEARCH_CUSTOM_LIKEPATTERN_NOTSTR = "0004";
    public static final String SEARCH_CUSTOM_ORI_EXCPT = "0005";
    public static final String CONFIG_SPECIFI_CLASS_NOTFOUND = "0006";
    public static final String CONFIG_SPECIFI_FIELD_NOTFOUND = "0007";

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public SpecificationException(String errorCode) {
        this.errorCode = errorCode;
        this.errorMsg = ExceptionCustomConfigUtils.analyzeErrorCode4ErrorMsg(SpecificationException.class, this.errorCode);
    }

    public SpecificationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public SpecificationException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public SpecificationException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = ExceptionCustomConfigUtils.analyzeErrorCode4ErrorMsg(SpecificationException.class, this.errorCode);
    }

    public SpecificationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

}
