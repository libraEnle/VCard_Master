package com.xhjk.core.interfaces.cardmanagement.vcard.services;

/**
 * 继承此接口，可得服务，可完成日志记录等非业务操作的操作
 * @param <T>
 */
public interface BaseBusinService<T extends Object> {

    /**
     * 核心业务操作前，非业务操作实现
     * @param requestObj
     * @return
     * @throws Exception
     */
    public Object DueBeforeBusinessWork(T requestObj) throws Exception;

    /**
     * 核心业务操作成功完成后，非业务操作实现
     * @param requestObj
     * @param returnResult
     * @return
     * @throws Exception
     */
    public Object updateResult(T requestObj, Object returnResult) throws Exception;

    /**
     * 核心业务操作失败，报异常，非业务操作实现
     * @param requestObj
     * @param error
     * @return
     * @throws Exception
     */
    public Object updateResultWithError(T requestObj, Throwable error) throws Exception;

}
