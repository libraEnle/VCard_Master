package com.xhjk.core.interfaces.cardmanagement.vcard.Utils;

import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.SpecificationException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

@Component
@ConfigurationProperties(prefix = "specification-config")
public class SpecificationConfigUtils {

    private Map<String, List<String>> configMap;
    private HashMap<Class, List<Field>> specifiConfigMap;

    public void setConfigMap(Map<String, List<String>> configMap) {
        this.configMap = configMap;
        if (!ObjectUtils.isEmpty(this.configMap)) {

            if (ObjectUtils.isEmpty(this.specifiConfigMap)) {
                this.specifiConfigMap = new HashMap<Class, List<Field>>();
            }

            Set<String> tmpKeySet = this.configMap.keySet();
            for (String tmpClassKey : tmpKeySet) {

                Class tmpSpecificationConfig = null;
                try {
                    tmpSpecificationConfig = this.getClass().getClassLoader().loadClass(tmpClassKey);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new SpecificationException(SpecificationException.CONFIG_SPECIFI_CLASS_NOTFOUND);
                }

                if(!ObjectUtils.isEmpty(tmpSpecificationConfig)){
                    List<String> tmpFieldNameList = this.configMap.get(tmpClassKey);

                    if (!ObjectUtils.isEmpty(tmpFieldNameList)) {

                        List<Field> tmpFieldObjList = new ArrayList<Field>();
                        for (String tmpFieldName : tmpFieldNameList) {
                            if (!ObjectUtils.isEmpty(tmpFieldName)) {

                                Field tmpField = null;
                                try{
                                    tmpField = tmpSpecificationConfig.getDeclaredField(tmpFieldName);
                                }catch(Exception e){
                                    e.printStackTrace();
                                    throw new SpecificationException(SpecificationException.CONFIG_SPECIFI_FIELD_NOTFOUND);
                                }
                                if(!ObjectUtils.isEmpty(tmpField)){
                                    tmpFieldObjList.add(tmpField);
                                }
                            }
                        }

                        if (!ObjectUtils.isEmpty(tmpFieldObjList)) {
                            this.specifiConfigMap.put(tmpSpecificationConfig, tmpFieldObjList);
                        }
                    }
                }


            }
        }
    }

    public HashMap<Class, List<Field>> getSpecifiConfigMap() {
        return specifiConfigMap;
    }

    public static void main(String[] args) {
        String testEmptyStr = null;
        List<String> testEmptyList = null;
        Map<String, String> testEmptyMap = null;
        Integer testEmptyInt = null;
        Object testEnptyObj = null;

        if (StringUtils.isEmpty(testEmptyStr)) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> testEmptyStr is empty");
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> testEmptyStr is not empty");
        }

        if (StringUtils.isEmpty(testEmptyList)) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> testEmptyList is empty");
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> testEmptyList is not empty");
        }

        if (StringUtils.isEmpty(testEmptyMap)) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> testEmptyMap is empty");
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> testEmptyMap is not empty");
        }

        if (StringUtils.isEmpty(testEmptyInt)) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> testEmptyInt is empty");
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> testEmptyInt is not empty");
        }

        if (StringUtils.isEmpty(testEnptyObj)) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> testEnptyObj is empty");
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> testEnptyObj is not empty");
        }


        if (ObjectUtils.isEmpty(testEmptyStr)) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> ObjectUtils testEmptyStr is empty");
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> ObjectUtils testEmptyStr is not empty");
        }

        if (ObjectUtils.isEmpty(testEmptyList)) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> ObjectUtils testEmptyList is empty");
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> ObjectUtils testEmptyList is not empty");
        }

        if (ObjectUtils.isEmpty(testEmptyMap)) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> ObjectUtils testEmptyMap is empty");
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> ObjectUtils testEmptyMap is not empty");
        }

        if (ObjectUtils.isEmpty(testEmptyInt)) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> ObjectUtils testEmptyInt is empty");
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> ObjectUtils testEmptyInt is not empty");
        }

        if (ObjectUtils.isEmpty(testEnptyObj)) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> ObjectUtils testEnptyObj is empty");
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> ObjectUtils testEnptyObj is not empty");
        }

    }
}
