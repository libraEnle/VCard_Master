package com.xhjk.core.interfaces.cardmanagement.vcard.Utils;

import com.xhjk.core.interfaces.cardmanagement.vcard.POs.IBaseComparable;
import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.CommonException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CommonUtils {

    public static Map<Object, Object> sortMapByKey(Map<Object, Object> unsortedMap) {
        Map<Object, Object> sortMap = new TreeMap<Object, Object>(new SortMapByKeyComparetor());
        sortMap.putAll(unsortedMap);
        return sortMap;
    }

    public static void main(String[] args){
        HashMap<Object,Object> testSort=new HashMap<Object,Object>();
        testSort.put("1","suibian");
        testSort.put("3","main");
        testSort.put("2","testSort");
        testSort.put("9","ain");
        testSort.put("6","fuck");

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> testSort: "+testSort);

        Map<Object,Object> tmpSortedMap=CommonUtils.sortMapByKey(testSort);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> tmpSortedMap: "+tmpSortedMap);
    }

}

class SortMapByKeyComparetor implements Comparator<Object> {

    public int compare(Object compare0, Object compare1) {

        if (compare0 instanceof String && compare1 instanceof String) {
            String tmpCompare0Str = (String) compare0;
            String tmpCompare1Str = (String) compare1;

            return tmpCompare0Str.compareTo(tmpCompare1Str);
        } else if (compare0 instanceof Integer && compare1 instanceof Integer) {
            Integer tmpCompare0Int = (Integer) compare0;
            Integer tmpCompare1Int = (Integer) compare1;

            return tmpCompare0Int - tmpCompare1Int;
        } else if (compare0 instanceof Double && compare1 instanceof Double) {
            Double tmpCompare0Dlb = (Double) compare0;
            Double tmpCompare1Dlb = (Double) compare1;

            int tmpCompareResult = tmpCompare0Dlb - tmpCompare1Dlb >= 0 ? (tmpCompare0Dlb - tmpCompare1Dlb > 0 ? 1 : 0) : -1;

            return tmpCompareResult;
        } else if (compare0 instanceof Float && compare1 instanceof Float) {
            Float tmpCompare0Flt = (Float) compare0;
            Float tmpCompare1Flt = (Float) compare1;

            int tmpCompareResult = tmpCompare0Flt - tmpCompare1Flt >= 0 ? (tmpCompare0Flt - tmpCompare1Flt > 0 ? 1 : 0) : -1;
            return tmpCompareResult;
        } else if (compare0 instanceof IBaseComparable && compare1 instanceof IBaseComparable) {

            IBaseComparable tmpComparable0 = (IBaseComparable) compare0;
            IBaseComparable tmpComparable1 = (IBaseComparable) compare1;

            Float tmpCompare0Flt = tmpComparable0.getCompareHashCode();
            Float tmpCompare1Flt = tmpComparable1.getCompareHashCode();

            int tmpCompareResult = tmpCompare0Flt - tmpCompare1Flt >= 0 ? (tmpCompare0Flt - tmpCompare1Flt > 0 ? 1 : 0) : -1;
            return tmpCompareResult;
        } else {
            throw new CommonException(CommonException.COMMON_CANNOT_COMPARE);
        }
    }

}
