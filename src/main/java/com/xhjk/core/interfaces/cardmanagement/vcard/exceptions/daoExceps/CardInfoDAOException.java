package com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.daoExceps;

import com.xhjk.core.interfaces.cardmanagement.vcard.Utils.ExceptionCustomConfigUtils;
import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.LogSerialException;

public class CardInfoDAOException extends BaseDAOException {

    private String errorCode;
    private String errorMsg;

    public final static String CARDINFO_DAO_INSERT_ERROR = "0001";
    public final static String CARDINFO_DAO_SEARCH_ERROR = "0002";

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public CardInfoDAOException(String errorCode) {
        this.errorCode = errorCode;
        this.errorMsg = ExceptionCustomConfigUtils.analyzeErrorCode4ErrorMsg(LogSerialException.class, errorCode);
    }

    public CardInfoDAOException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public CardInfoDAOException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public CardInfoDAOException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = ExceptionCustomConfigUtils.analyzeErrorCode4ErrorMsg(LogSerialException.class, errorCode);
    }

    public CardInfoDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }
}
