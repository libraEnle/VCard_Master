package com.xhjk.core.interfaces.cardmanagement.vcard.services;

import com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.entitys.SystemTrace;

/**
 * 流水日志操作服务类(测试用)
 */
public interface SystemTraceService extends BaseBusinService<SystemTrace>{

    public SystemTrace insertCertainSystemTrace(SystemTrace systemTrace) throws Exception;

}
