package com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.respositories.impl;


import com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.respositories.BaseDAORepository;
import com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.respositories.Utils.CommSearchSpecificationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * JPA自定义Repository 创造自定义JPA方法
 * @param <T>
 * @param <ID>
 */
public class BaseDAORepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseDAORepository<T,ID> {

    public BaseDAORepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
    }

    @Override
    public Page<T> withCustomQueryByDefaultQuery(T queryCriteria, Pageable pageable) {
        return findAll(CommSearchSpecificationUtils.getDefaultSearchSpecification(queryCriteria),pageable);
    }
}
