package com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.respositories;

import com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.entitys.CardInfo;
import com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.entitys.CardInfoId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardInfoRepository extends BaseDAORepository<CardInfo,CardInfoId> {

    public List<CardInfo> findByCardNo(String cardNo);

    @Query("select c from CardInfo c where c.cardNo=:cardNo and c.cardSeq=:cardSeq")
    public List<CardInfo> withCardNoAndCardSeqQuery(@Param("cardNo")String cardNo, @Param("cardSeq")String cardSeq);

    public List<CardInfo> withCardNoQuery(String cardNo);

}