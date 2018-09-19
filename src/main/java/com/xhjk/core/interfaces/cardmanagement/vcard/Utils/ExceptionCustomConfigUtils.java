package com.xhjk.core.interfaces.cardmanagement.vcard.Utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;

@Component
@ConfigurationProperties(prefix = "exception-custom")
public class ExceptionCustomConfigUtils {

    private HashMap<String, HashMap<String, String>> exceptionMap;
    public static HashMap<String, HashMap<String, String>> EXCEP_MAP;

//    public HashMap<String, HashMap<String, String>> getExceptionMap() {
//        return exceptionMap;
//    }

    public void setExceptionMap(HashMap<String, HashMap<String, String>> exceptionMap) {
        this.exceptionMap = exceptionMap;
        EXCEP_MAP = exceptionMap;
    }

    public static String analyzeErrorCode4ErrorMsg(Class excepCls, String errorCode) {
        String tmpErrorMsg = null;
        if (!ObjectUtils.isEmpty(EXCEP_MAP)) {

            HashMap<String, HashMap<String, String>> tmpExcepConfigMap = EXCEP_MAP;
            HashMap<String, String> tmpCertainErrorMsgMap = tmpExcepConfigMap.get(excepCls.getClass().getSimpleName());

            if (!ObjectUtils.isEmpty(tmpCertainErrorMsgMap)) {
                tmpErrorMsg = tmpCertainErrorMsgMap.get(errorCode);
            }

            if (ObjectUtils.isEmpty(tmpErrorMsg)) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> errorCode: " + errorCode + "; errorMsg: empty, please check ExceptionCustomConfig.yml");
            }

        }

        return tmpErrorMsg;
    }
}
