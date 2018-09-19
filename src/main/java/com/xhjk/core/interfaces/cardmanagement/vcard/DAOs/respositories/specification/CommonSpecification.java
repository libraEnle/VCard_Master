package com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.respositories.specification;

import com.xhjk.core.interfaces.cardmanagement.vcard.Utils.BeanSearchUtils;
import com.xhjk.core.interfaces.cardmanagement.vcard.Utils.SpecificationConfigUtils;
import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.SpecificationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * JPA自定义查询条件
 * @param <T>
 */
public class CommonSpecification<T> implements Specification {

    private static SpecificationConfigUtils configUtils;

    static {
        configUtils = BeanSearchUtils.getBean(SpecificationConfigUtils.class);
    }

    private static final String FIELD_DEFAULT_SEARCH = "DEFAULT";
    private static final String FIELD_LIKE_SEARCH = "LIKE";
    private static final String LIKE_PATTERN = "%";

    private T searchCriteria;
    private CriteriaQuery criteriaQuery;
    private CriteriaBuilder criteriaBuilder;
    private Root root;

    public CommonSpecification(T searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = null;
        this.criteriaBuilder = criteriaBuilder;
        this.criteriaQuery = criteriaQuery;
        this.root = root;

        HashMap<String, List<Field>> tmpSearchCriteriaFieldList = this.analyzeSearchWhole(this.searchCriteria);
        predicates = this.constructPredicateConditionList(tmpSearchCriteriaFieldList, this.searchCriteria);

        Predicate[] tmpPredicateArr = this.getFinalPredicateArrFromList(predicates);

        return predicates.isEmpty() && tmpPredicateArr.length > 0 ? this.criteriaBuilder.conjunction() : this.criteriaBuilder.and(tmpPredicateArr);
    }

    /**
     * 转换Predicate列表为数组
     *
     * @param predicates
     * @return
     */
    private Predicate[] getFinalPredicateArrFromList(List<Predicate> predicates) {
        Predicate[] tmpPredicateArr = null;
        if (!predicates.isEmpty()) {
            tmpPredicateArr = new Predicate[predicates.size()];
            int tmpArrIndex = 0;
            for (Predicate tmpPredicate : predicates) {
                if (!ObjectUtils.isEmpty(tmpPredicate)) {
                    tmpPredicateArr[tmpArrIndex] = tmpPredicate;
                    tmpArrIndex++;
                }
            }
        }
        return tmpPredicateArr;
    }

    /**
     * 整体构建查询条件
     *
     * @param fieldMap
     * @param searchCriteria
     * @return
     */
    private List<Predicate> constructPredicateConditionList(HashMap<String, List<Field>> fieldMap, T searchCriteria) {

        List<Predicate> tmpPredicates = new ArrayList<Predicate>();

        this.constructLikePredicate(searchCriteria, fieldMap.get(FIELD_LIKE_SEARCH), tmpPredicates);
        this.constructDefaultPredicate(searchCriteria, fieldMap.get(FIELD_DEFAULT_SEARCH), tmpPredicates);

        return tmpPredicates;

    }

    /**
     * 通过默认查询字段列表，构建默认查询条件
     *
     * @param searchCriteria
     * @param defualtFieldList
     * @param predicateList
     * @return
     */
    private boolean constructDefaultPredicate(T searchCriteria, List<Field> defualtFieldList, List<Predicate> predicateList) {
        boolean tmpConstructResult = false;

        for (Field tmpField : defualtFieldList) {
            if (ObjectUtils.isEmpty(tmpField)) {
                continue;
            }

            Class tmpFieldValueCls = tmpField.getType().getClass();
            String tmpFieldName = tmpField.getName();
            tmpField.setAccessible(true);
            Object tmpFieldVal = null;

            try {
                tmpFieldVal = tmpField.get(searchCriteria);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new SpecificationException(SpecificationException.SEARCH_CUSTOM_ORI_EXCPT);
            }

            if (!ObjectUtils.isEmpty(tmpFieldVal)) {
                predicateList.add(this.criteriaBuilder.equal(this.root.get(tmpFieldName), tmpFieldVal));
            }

        }

        tmpConstructResult = true;

        return tmpConstructResult;
    }

    /**
     * 通过like字段列表，构建like的查询条件
     *
     * @param searchCriteria
     * @param likeFieldList
     * @param predicateList
     * @return
     */
    private boolean constructLikePredicate(T searchCriteria, List<Field> likeFieldList, List<Predicate> predicateList) {
        boolean tmpConstructResult = false;

        for (Field tmpField : likeFieldList) {
            if (ObjectUtils.isEmpty(tmpField)) {
                continue;
            }

            Class tmpFieldValueCls = tmpField.getType();
            if (tmpFieldValueCls == String.class) {

                try {
                    tmpField.setAccessible(true);
                    Object tmpFieldVal = tmpField.get(searchCriteria);
                    String tmpFieldName = tmpField.getName();

                    if (!ObjectUtils.isEmpty(tmpFieldVal)) {
                        predicateList.add(this.criteriaBuilder.like(this.root.get(tmpFieldName), this.getLikePattern(tmpFieldVal)));
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new SpecificationException(SpecificationException.SEARCH_CUSTOM_ORI_EXCPT);
                }

            }

        }

        tmpConstructResult = true;

        return tmpConstructResult;
    }

    /**
     * 获取无需like的字段列表
     *
     * @param searchCriteria
     * @param likeFieldList
     * @return
     */
    private List<Field> analyzeSearchDefault(T searchCriteria, List<Field> likeFieldList) {

        List<Field> tmpWholeFieldList = Arrays.asList(searchCriteria.getClass().getDeclaredFields());
        List<Field> tmpWholeResultFieldList = null;
        if (!ObjectUtils.isEmpty(tmpWholeFieldList)) {
            if (!ObjectUtils.isEmpty(likeFieldList)) {

                tmpWholeResultFieldList = new ArrayList<Field>();

                for (Field tmpWholeField : tmpWholeFieldList) {

                    if (!ObjectUtils.isEmpty(tmpWholeField) && !likeFieldList.contains(tmpWholeField)) {
                        tmpWholeResultFieldList.add(tmpWholeField);
                    }

                }

                tmpWholeFieldList = tmpWholeResultFieldList;

            }
        }
        return tmpWholeFieldList;

    }

    /**
     * 判断查询的字段列表总数，作为判断是否查找字段正确
     *
     * @param likeFieldList
     * @param defaultFieldList
     * @return
     */
    private int judgeAnalyzeFieldListSize(List<Field> likeFieldList, List<Field> defaultFieldList) {

        int tmpFieldSize = 0;

        tmpFieldSize += ObjectUtils.isEmpty(likeFieldList) ? 0 : likeFieldList.size();
        tmpFieldSize += ObjectUtils.isEmpty(defaultFieldList) ? 0 : defaultFieldList.size();

        return tmpFieldSize;

    }

    /**
     * 通过分析，获取like与不需要like的字段列表，组成Map，反馈
     *
     * @param searchCriteria
     * @return
     */
    private HashMap<String, List<Field>> analyzeSearchWhole(T searchCriteria) {

        HashMap<String, List<Field>> tmpWholeSearchMap = null;
        List<Field> tmpLikeSearchFieldList = this.analyzeSearchCriteriaLike(searchCriteria);
        List<Field> tmpDefaultSearchFieldList = this.analyzeSearchDefault(searchCriteria, tmpLikeSearchFieldList);

        if (this.judgeAnalyzeFieldListSize(tmpLikeSearchFieldList, tmpDefaultSearchFieldList) == 0) {
            throw new SpecificationException(SpecificationException.SEARCH_CUSTOM_FIELDLIST_EMPTY);
        } else {
            if (StringUtils.isEmpty(tmpWholeSearchMap)) {
                tmpWholeSearchMap = new HashMap<String, List<Field>>();
            }

            tmpWholeSearchMap.put(FIELD_DEFAULT_SEARCH, tmpDefaultSearchFieldList);
            tmpWholeSearchMap.put(FIELD_LIKE_SEARCH, tmpLikeSearchFieldList);
        }

        return tmpWholeSearchMap;

    }

    /**
     * 分析配置文件中特定类型查询，获取like的字段列表
     *
     * @param searchCriteria
     * @return
     */
    private List<Field> analyzeSearchCriteriaLike(T searchCriteria) {

        List<Field> tmpFieldList = null;

        if (!ObjectUtils.isEmpty(this.configUtils) && !ObjectUtils.isEmpty(this.configUtils.getSpecifiConfigMap())) {

            HashMap<Class, List<Field>> tmpSpecificationMap = this.configUtils.getSpecifiConfigMap();
            Class tmpSearchCriteriaClass = searchCriteria.getClass();
            tmpFieldList = tmpSpecificationMap.get(tmpSearchCriteriaClass);

                    /*if (ObjectUtils.isEmpty(tmpFieldList)) {
                        throw new SpecificationException(SpecificationException.SEARCH_CUSTOM_FIELDLIST_EMPTY);
                    }*/

        } else if (!ObjectUtils.isEmpty(this.configUtils) && ObjectUtils.isEmpty(this.configUtils.getSpecifiConfigMap())) {

            throw new SpecificationException(SpecificationException.SEARCH_CUSTOM_CONFIGMAP_EMPTY);

        } else {
            throw new SpecificationException(SpecificationException.SEARCH_CUSTOM_CONFIG_EMPTY);
        }

        return tmpFieldList;
    }

    /**
     * 组装like内容
     *
     * @param columnVal
     * @return
     */
    private String getLikePattern(Object columnVal) {

        String tmpColumnVal = "";
        if (columnVal.getClass() == String.class) {
            tmpColumnVal = (String) columnVal;
        } else {
            throw new SpecificationException(SpecificationException.SEARCH_CUSTOM_LIKEPATTERN_NOTSTR);
        }
        return LIKE_PATTERN + columnVal + LIKE_PATTERN;

    }
}
