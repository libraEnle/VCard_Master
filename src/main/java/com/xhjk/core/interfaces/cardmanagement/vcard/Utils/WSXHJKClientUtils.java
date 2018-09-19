package com.xhjk.core.interfaces.cardmanagement.vcard.Utils;

import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.WSXHJKClientException;
import gov.datacenter.services.Error;
import gov.datacenter.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class WSXHJKClientUtils {

    @Autowired
    private WSXHJKClientConfig wsClientConfig;

    private static WSServiceAgentImplService WSServiceStatic = new WSServiceAgentImplService();
    private static WSServiceAgent WSServiceAgentStatic = WSServiceStatic.getWSServiceAgentPort();

    private static final String Seperator = "/";

    private static final String ACKCODE_FAIL_CODE = "FAILURE";
    private static final String ACKCODE_SUCC_CODE = "SUCCESS";

    /**
     * 调用WS获取民生卡信息
     * @param certNo
     * @return
     */
    public LivelihoodCardInfo getDocuWebServRep(String certNo) {

        LivelihoodCardInfo livelihoodInfo = null;
        LivelihoodCardQueryRequest tmpCardQuery = new LivelihoodCardQueryRequest();
        tmpCardQuery.setSfzjhm(certNo);
        tmpCardQuery.setTicket(this.wsClientConfig.getTicket());

        LivelihoodCardQueryResponse tmpResp = WSServiceAgentStatic.getLivelihoodCardInfo(tmpCardQuery);
        if(this.chekWSResp(tmpResp)){
            livelihoodInfo = tmpResp.getInfo();
        }

        return livelihoodInfo;

    }

    /**
     * 验证WS返回数据是否正确
     * @param livelihoodResp
     * @return
     */
    private boolean chekWSResp(LivelihoodCardQueryResponse livelihoodResp) {

        boolean chekResult = false;

        if (!ObjectUtils.isEmpty(livelihoodResp)) {

            String tmpAckCode = livelihoodResp.getAckCode().value();
            if (!ObjectUtils.isEmpty(tmpAckCode)) {
                if (tmpAckCode.trim().equalsIgnoreCase(ACKCODE_SUCC_CODE)) {
                    LivelihoodCardInfo livelihoodInfo = livelihoodResp.getInfo();
                    if (!ObjectUtils.isEmpty(livelihoodInfo)) {
                        chekResult = true;
                    } else {
                        throw new WSXHJKClientException(WSXHJKClientException.WSCLIENT_SUCCESS_NORESULT);
                    }
                } else if (tmpAckCode.trim().equalsIgnoreCase(ACKCODE_FAIL_CODE)) {
                    List<Error> tmpErrorLit = livelihoodResp.getErrors();
                    if (!ObjectUtils.isEmpty(tmpErrorLit)) {
                        StringBuilder tmpErrorLitBuilder = new StringBuilder();
                        for (Error tmpEr : tmpErrorLit) {

                            if (!ObjectUtils.isEmpty(tmpEr) && !ObjectUtils.isEmpty(tmpEr.getMessage())) {
                                tmpErrorLitBuilder.append(tmpEr.getMessage()).append(Seperator);
                            }

                        }

                        throw new WSXHJKClientException(tmpErrorLitBuilder.toString(), WSXHJKClientException.WSCLIENT_FAIL_WITHERINFLIT);
                    } else {
                        throw new WSXHJKClientException(WSXHJKClientException.WSCLIENT_FAIL_WITHOUTERINFLIT);
                    }
                } else {
                    throw new WSXHJKClientException(WSXHJKClientException.WSCLIENT_FAIL_NOSUCHERCODE);
                }
            }

        } else {
            throw new WSXHJKClientException(WSXHJKClientException.WSCLIENT_FAIL_NORESP);
        }

        return chekResult;

    }
}