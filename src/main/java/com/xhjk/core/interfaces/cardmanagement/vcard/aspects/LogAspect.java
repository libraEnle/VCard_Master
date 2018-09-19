package com.xhjk.core.interfaces.cardmanagement.vcard.aspects;

import com.xhjk.core.interfaces.cardmanagement.vcard.Utils.BeanSearchUtils;
import com.xhjk.core.interfaces.cardmanagement.vcard.Utils.DueBeforeBusinessWorkUtils;
import com.xhjk.core.interfaces.cardmanagement.vcard.annotations.BusiSerialRecord;
import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.LogSerialException;
import com.xhjk.core.interfaces.cardmanagement.vcard.services.BaseBusinService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 日志等非业务操作启动类
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private DueBeforeBusinessWorkUtils dueBeforeUtils;

    private static Map<Thread, Object> threadManageMap = new ConcurrentHashMap<Thread, Object>();

    @Pointcut("@annotation(com.xhjk.core.interfaces.cardmanagement.vcard.annotations.BusiSerialRecord)")
    public void recordBusinLog() {
    }

    @Before(value = "recordBusinLog()")
    public void doBefore(JoinPoint point) {

        Object[] tmpArgements = point.getArgs();
        Method tmpMethod = this.getMethod(point);
        if (!ObjectUtils.isEmpty(tmpMethod)) {

            BusiSerialRecord tmpRecordAnno = tmpMethod.getAnnotation(BusiSerialRecord.class);
            this.dueBeforeBusinessWork(tmpRecordAnno, tmpArgements);

        }
    }

    @AfterReturning(value = "recordBusinLog()", returning = "returnVal")
    public void doAfterReturning(Object returnVal) {
        Object tmpCurThreadObj = this.getCurThreadObj();
        DueBeforeBusinessWorkUtils.DueAfterBusi tmpDue = this.getDueAfterBusinessWork();

        try {
            Method tmpMethod = tmpDue.getProcessor().getDeclaredMethod(tmpDue.getReturnMethod(), tmpDue.getParam(), Object.class);
            BaseBusinService tmpBaseService = (BaseBusinService) BeanSearchUtils.getBean(tmpDue.getProcessor());

            tmpMethod.invoke(tmpBaseService, tmpCurThreadObj, returnVal);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new LogSerialException(e, LogSerialException.LOGRECORD_ASPECT_GETMETHOD_NOSUCHMETHOD);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new LogSerialException(e, LogSerialException.LOGRECORD_ASPECT_GETMETHOD_NOSUCHMETHOD);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new LogSerialException(e, LogSerialException.LOGRECORD_ASPECT_GETMETHOD_NOSUCHMETHOD);
        }
    }

    @AfterThrowing(value = "recordBusinLog()", throwing = "excp")
    public void doAfterThrowing(Throwable excp) {
        Object tmpCurThreadObj = this.getCurThreadObj();
        DueBeforeBusinessWorkUtils.DueAfterBusi tmpDue = this.getDueAfterBusinessWork();

        try {
            Method tmpMethod = tmpDue.getProcessor().getDeclaredMethod(tmpDue.getThrowMethod(), tmpDue.getParam(), Throwable.class);
            BaseBusinService tmpBaseService = (BaseBusinService) BeanSearchUtils.getBean(tmpDue.getProcessor());

            tmpMethod.invoke(tmpBaseService, tmpCurThreadObj, excp);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new LogSerialException(e, LogSerialException.LOGRECORD_ASPECT_GETMETHOD_NOSUCHMETHOD);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new LogSerialException(e, LogSerialException.LOGRECORD_ASPECT_GETMETHOD_NOSUCHMETHOD);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new LogSerialException(e, LogSerialException.LOGRECORD_ASPECT_GETMETHOD_NOSUCHMETHOD);
        }
    }

    private DueBeforeBusinessWorkUtils.DueAfterBusi getDueAfterBusinessWork() {

        DueBeforeBusinessWorkUtils.DueAfterBusi tmpDueResult = null;

        Object tmpCurThreadObj = this.getCurThreadObj();
        HashMap<String, DueBeforeBusinessWorkUtils.DueAfterBusi> tmpEndProcMap = this.dueBeforeUtils.getStreamEndClsMap();

        if (!ObjectUtils.isEmpty(tmpEndProcMap) && !ObjectUtils.isEmpty(tmpEndProcMap.get(tmpCurThreadObj.getClass().getSimpleName()))) {
            tmpDueResult = tmpEndProcMap.get(tmpCurThreadObj.getClass().getSimpleName());
        }

        return tmpDueResult;
    }

    /**
     * @param recordAnno
     * @param methodParams
     * @return
     */
    private boolean dueBeforeBusinessWork(BusiSerialRecord recordAnno, Object[] methodParams) {

        boolean dueResult = false;

        if (!ObjectUtils.isEmpty(recordAnno) &&
                !ObjectUtils.isEmpty(this.dueBeforeUtils) &&
                !ObjectUtils.isEmpty(this.dueBeforeUtils.getStreamClsMap())) {

            HashMap<String, List<DueBeforeBusinessWorkUtils.DueBeforeBusi>> tmpDueMap = this.dueBeforeUtils.getStreamClsMap();

            String tmpBusCode = recordAnno.busCode();
            Serializable tmpMethodParam = (Serializable) methodParams[0];

            if (!ObjectUtils.isEmpty(tmpMethodParam) && !ObjectUtils.isEmpty(tmpBusCode) && !ObjectUtils.isEmpty(tmpDueMap.get(tmpBusCode))) {
                List<DueBeforeBusinessWorkUtils.DueBeforeBusi> tmpClsList = tmpDueMap.get(tmpBusCode);
                for (int tmpDueSeq = 0; tmpDueSeq < tmpClsList.size(); tmpDueSeq++) {

                    DueBeforeBusinessWorkUtils.DueBeforeBusi tmpDueInfo = tmpClsList.get(tmpDueSeq);
                    Class<?> tmpDueServiceCls = tmpDueInfo.getProcessor();
                    Class<?> tmpFromCls = tmpDueInfo.getFrom();
                    Class<?> tmpToCls = tmpDueInfo.getTo();
                    String tmpMetod = tmpDueInfo.getMethod();

                    try {

                        Object tmpParam = null;
                        if (!ObjectUtils.isEmpty(tmpMetod)) {

                            try {

                                tmpParam = tmpToCls.newInstance();
                                Method tmpTransferMetod = tmpToCls.getDeclaredMethod(tmpMetod, tmpFromCls);
                                tmpTransferMetod.invoke(tmpParam, tmpMethodParam);

                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                                throw new LogSerialException(LogSerialException.LOGRECORD_ASPECT_GETMETHOD_NOSUCHMETHOD);
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                                throw new LogSerialException(LogSerialException.LOGRECORD_ASPECT_GETMETHOD_NOSUCHMETHOD);
                            }

                        }

                        BaseBusinService tmpBaseService = (BaseBusinService) BeanSearchUtils.getBean(tmpDueServiceCls);
                        try {
                            Object tmpProcessResult = tmpBaseService.DueBeforeBusinessWork(tmpParam);

                            if (!ObjectUtils.isEmpty(tmpDueInfo.getLevel()) &&
                                    tmpDueInfo.getLevel().trim().equalsIgnoreCase(DueBeforeBusinessWorkUtils.DueBeforeBusi.RECORD)) {

                                if (ObjectUtils.isEmpty(this.getCurThreadObj())) {
                                    LogAspect.threadManageMap.put(Thread.currentThread(), tmpProcessResult);
                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    } catch (InstantiationException e) {
                        e.printStackTrace();
                        throw new LogSerialException(LogSerialException.LOGRECORD_PROCESS_DUE_EXCEP);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new LogSerialException(LogSerialException.LOGRECORD_PROCESS_DUE_EXCEP);
                    }

                }
            }

        }

        return dueResult;
    }

    /**
     * 获取方法信息
     *
     * @param point
     * @return
     */
    private Method getMethod(JoinPoint point) {
        MethodSignature tmpMethodSignature = (MethodSignature) point.getSignature();
        Class tmpTargetCls = point.getTarget().getClass();
        try {
            return tmpTargetCls.getMethod(tmpMethodSignature.getName(), tmpMethodSignature.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new LogSerialException(LogSerialException.LOGRECORD_ASPECT_GETMETHOD_NOSUCHMETHOD);
        }
    }

    /**
     * 获取当前线程记录下的操作对象
     *
     * @return
     */
    private Object getCurThreadObj() {
        Object tmpCurThreadObj = null;
        Thread tmpCurThread = Thread.currentThread();
        if (!ObjectUtils.isEmpty(LogAspect.threadManageMap) && !ObjectUtils.isEmpty(LogAspect.threadManageMap.get(tmpCurThread))) {
            tmpCurThreadObj = LogAspect.threadManageMap.get(tmpCurThread);
        }
        return tmpCurThreadObj;
    }

}
