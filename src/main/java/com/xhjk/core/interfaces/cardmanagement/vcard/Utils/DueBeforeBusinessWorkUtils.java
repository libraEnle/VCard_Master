package com.xhjk.core.interfaces.cardmanagement.vcard.Utils;


import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.CommonException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Component
@ConfigurationProperties(prefix = "due-before-work-process")
public class DueBeforeBusinessWorkUtils {

    private HashMap<String, HashMap<String, HashMap<String, String>>> streamMap;
    private HashMap<String, HashMap<String, String>> streamEndMap;
    private HashMap<String, List<DueBeforeBusi>> streamClsMap;
    private HashMap<String, DueAfterBusi> streamEndClsMap;

    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String METHOD = "method";
    public static final String PROCESSOR = "processor";
    public static final String LEVEL = "level";

    public static final String END_PROCESSOR = "processor";
    public static final String END_PARAM = "param";
    public static final String END_RETURNMETHOD = "returnMethod";
    public static final String END_THROWMETHOD = "throwMethod";

    public HashMap<String, List<DueBeforeBusi>> getStreamClsMap() {
        return streamClsMap;
    }

    public HashMap<String, DueAfterBusi> getStreamEndClsMap() {
        return streamEndClsMap;
    }

    public void setStreamEndMap(HashMap<String, HashMap<String, String>> streamEndMap) {
        this.streamEndMap = streamEndMap;
        if (!ObjectUtils.isEmpty(this.streamEndMap)) {

            Set<String> tmpKeySet = this.streamEndMap.keySet();
            for (String tmpProceClsSplName : tmpKeySet) {

                HashMap<String, String> tmpCataMap = this.streamEndMap.get(tmpProceClsSplName);
                DueAfterBusi tmpDue = null;
                if (!ObjectUtils.isEmpty(tmpCataMap)) {

                    if (ObjectUtils.isEmpty(tmpDue)) {
                        tmpDue = new DueAfterBusi();
                    }
                    String tmpEndProcessClsStr = tmpCataMap.get(END_PROCESSOR);
                    String tmpEndParamClsStr = tmpCataMap.get(END_PARAM);
                    String tmpEndReturnMethod = tmpCataMap.get(END_RETURNMETHOD);
                    String tmpEndThrowMethod = tmpCataMap.get(END_THROWMETHOD);

                    tmpDue.setParam(this.transClsFromClsName(tmpEndParamClsStr));
                    tmpDue.setProcessor(this.transClsFromClsName(tmpEndProcessClsStr));
                    tmpDue.setReturnMethod(tmpEndReturnMethod);
                    tmpDue.setThrowMethod(tmpEndThrowMethod);

                }

                if (!ObjectUtils.isEmpty(tmpProceClsSplName) && !ObjectUtils.isEmpty(tmpDue)) {
                    if (ObjectUtils.isEmpty(this.streamEndClsMap)) {
                        this.streamEndClsMap = new HashMap<String, DueAfterBusi>();
                    }
                    if (!this.streamEndClsMap.containsKey(tmpProceClsSplName)) {
                        this.streamEndClsMap.put(tmpProceClsSplName, tmpDue);
                    }
                }

            }

        }
    }

    public void setStreamMap(HashMap<String, HashMap<String, HashMap<String, String>>> streamMap) {
        this.streamMap = streamMap;
        if (!ObjectUtils.isEmpty(this.streamMap)) {
            for (String tmpBusCodeKey : this.streamMap.keySet()) {

                if (!ObjectUtils.isEmpty(tmpBusCodeKey)) {


                    HashMap<String, HashMap<String, String>> tmpProcessMap = this.streamMap.get(tmpBusCodeKey);
                    List<HashMap<String, String>> tmpSortProceInfoMapLit = this.getSortedProcessorsInfoMapList(tmpProcessMap);
                    List<DueBeforeBusi> tmpDueList = this.transferProceMap(tmpSortProceInfoMapLit);

                    if (ObjectUtils.isEmpty(this.streamClsMap)) {
                        this.streamClsMap = new HashMap<String, List<DueBeforeBusi>>();
                    }

                    if (!this.streamClsMap.containsKey(tmpBusCodeKey)) {
                        this.streamClsMap.put(tmpBusCodeKey, tmpDueList);
                    }
                }

            }
        }
    }

    private Class transClsFromClsName(String clsName) {
        Class tmpCls = null;
        try {
            tmpCls = this.getClass().getClassLoader().loadClass(clsName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new CommonException(CommonException.COMMON_CLASS_NOTFOUND);
        }
        return tmpCls;
    }

    private List<DueBeforeBusi> transferProceMap(List<HashMap<String, String>> dueMapInfoLit) {
        List<DueBeforeBusi> tmpDueLit = null;

        for (int tmpProceIdx = 0; tmpProceIdx < dueMapInfoLit.size(); tmpProceIdx++) {
            HashMap<String, String> tmpProceInfoMap = dueMapInfoLit.get(tmpProceIdx);
            if (!ObjectUtils.isEmpty(tmpProceInfoMap)) {
                DueBeforeBusi tmpDue = new DueBeforeBusi();

                String tmpFrom = tmpProceInfoMap.get(FROM);
                String tmpTo = tmpProceInfoMap.get(TO);
                String tmpProcessor = tmpProceInfoMap.get(PROCESSOR);
                String tmpMethod = tmpProceInfoMap.get(METHOD);
                String tmpLevel = tmpProceInfoMap.get(LEVEL);

                Class tmpFromClass = this.transClsFromClsName(tmpFrom);
                Class tmpToClass = this.transClsFromClsName(tmpTo);
                Class tmpProcClass = this.transClsFromClsName(tmpProcessor);

                tmpDue.setFrom(tmpFromClass);
                tmpDue.setTo(tmpToClass);
                tmpDue.setProcessor(tmpProcClass);
                tmpDue.setMethod(tmpMethod);
                tmpDue.setLevel(tmpLevel);

                if (ObjectUtils.isEmpty(tmpDueLit)) {
                    tmpDueLit = new ArrayList<DueBeforeBusi>();
                }
                tmpDueLit.add(tmpDue);
            }

        }

        return tmpDueLit;
    }

    /**
     * 排序业务前作业流程类信息
     *
     * @param processMap
     * @return
     */
    private List<HashMap<String, String>> getSortedProcessorsInfoMapList(HashMap<String, HashMap<String, String>> processMap) {

        List<HashMap<String, String>> tmpSortProcessList = new ArrayList<HashMap<String, String>>();
        Map<Object, Object> tmpTransferResultMap = new HashMap<Object, Object>();

        Set<String> tmpKeySet = processMap.keySet();
        for (String tmpKeyStr : tmpKeySet) {
            Object tmpValue = processMap.get(tmpKeyStr);
            Object tmpKey = (Object) tmpKeyStr;

            tmpTransferResultMap.put(tmpKey, tmpValue);
        }

        Map<Object, Object> tmpSortedMap = CommonUtils.sortMapByKey(tmpTransferResultMap);
        Set<Map.Entry<Object, Object>> tmpEntrySet = tmpSortedMap.entrySet();

        Iterator<Map.Entry<Object, Object>> tmpIterator = tmpEntrySet.iterator();
        while (tmpIterator.hasNext()) {

            Map.Entry<Object, Object> tmpEntry = tmpIterator.next();
            String tmpKey = (String) tmpEntry.getKey();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> tmpKey: " + tmpKey);
            HashMap<String, String> tmpValue = (HashMap<String, String>) tmpEntry.getValue();

            tmpSortProcessList.add(tmpValue);

        }


        return tmpSortProcessList;
    }

    /**
     * 内部类，处理业务请求后期工作配置
     */
    public class DueAfterBusi {
        private Class processor;
        private Class param;
        private String returnMethod;
        private String throwMethod;

        public Class getProcessor() {
            return processor;
        }

        public void setProcessor(Class processor) {
            this.processor = processor;
        }

        public Class getParam() {
            return param;
        }

        public void setParam(Class param) {
            this.param = param;
        }

        public String getReturnMethod() {
            return returnMethod;
        }

        public void setReturnMethod(String returnMethod) {
            this.returnMethod = returnMethod;
        }

        public String getThrowMethod() {
            return throwMethod;
        }

        public void setThrowMethod(String throwMethod) {
            this.throwMethod = throwMethod;
        }
    }

    /**
     * 内部类，处理请求前期工作配置
     */
    public class DueBeforeBusi {
        private Class from;
        private Class to;
        private Class processor;
        private String method;
        private String level;

        public static final String RECORD = "record";

        public Class getFrom() {
            return from;
        }

        public void setFrom(Class from) {
            this.from = from;
        }

        public Class getTo() {
            return to;
        }

        public void setTo(Class to) {
            this.to = to;
        }

        public Class getProcessor() {
            return processor;
        }

        public void setProcessor(Class processor) {
            this.processor = processor;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }

}
