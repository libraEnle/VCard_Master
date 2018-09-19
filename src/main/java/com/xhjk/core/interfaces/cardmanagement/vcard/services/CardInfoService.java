package com.xhjk.core.interfaces.cardmanagement.vcard.services;

import com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.entitys.CardInfo;

import gov.datacenter.services.LivelihoodCardInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 卡信息操作服务类(测试用)
 */
public interface CardInfoService {

    public List<CardInfo> searchCardListByCertainCardNo(String cardNo) throws Exception;

    public List<CardInfo> searchCardInfoListByQuery(String cardNo,String cardSeq) throws Exception;

    public List<CardInfo> searchCardInfoByNameQuery(String cardNo) throws Exception;

    public Page<CardInfo> searchCardByDefaultCondition(CardInfo cardInfo, Pageable page) throws Exception;

    public LivelihoodCardInfo getCardInfo(String certNo) throws Exception;
}
