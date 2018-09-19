package com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.respositories.Utils;

import com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.respositories.specification.CommonSpecification;
import org.springframework.data.jpa.domain.Specification;

public class CommSearchSpecificationUtils {



    public static <T> Specification getDefaultSearchSpecification(T searchCriteria) {

        return new CommonSpecification<T>(searchCriteria);

    }

}
