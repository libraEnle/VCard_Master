package com.xhjk.core.interfaces.cardmanagement.vcard.services.impl;

import com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.entitys.SystemTrace;
import com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.respositories.CmsSysTraceRepository;
import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.servExceps.SystemTraceServException;
import com.xhjk.core.interfaces.cardmanagement.vcard.services.SystemTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


@Service("systemTraceService")
public class SystemTraceServiceImpl implements SystemTraceService {

    @Autowired
    private CmsSysTraceRepository systemTraceRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public SystemTrace insertCertainSystemTrace(SystemTrace systemTrace) {
        return this.insertSystemTrace(systemTrace);
    }

    @Override
    public SystemTrace DueBeforeBusinessWork(SystemTrace requestObj) throws Exception {
        return this.insertSystemTrace(requestObj);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Object updateResult(SystemTrace requestObj, Object returnResult) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> returnResult: " + returnResult);
        requestObj.setRspCode("0000");
        this.systemTraceRepository.save(requestObj);
        return requestObj;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Object updateResultWithError(SystemTrace requestObj, Throwable error) throws Exception {
        error.printStackTrace();
        requestObj.setRspCode("0001");
        requestObj.setRepDes(error.getMessage());
        this.systemTraceRepository.save(requestObj);
        return requestObj;
    }

    private SystemTrace insertSystemTrace(SystemTrace systemTrace) {

        if (!ObjectUtils.isEmpty(systemTrace)) {
            return systemTrace;
        } else {
            throw new SystemTraceServException(SystemTraceServException.SYSTEMTRACE_PARAM_EMPTY_ERROR);
        }
    }
}
