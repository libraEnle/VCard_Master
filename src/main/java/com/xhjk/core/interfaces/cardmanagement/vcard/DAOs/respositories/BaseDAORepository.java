package com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.respositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseDAORepository<T,ID extends Serializable> extends JpaRepository<T,ID>,JpaSpecificationExecutor<T> {

    Page<T> withCustomQueryByDefaultQuery(T queryCriteria, Pageable pageable);

}
