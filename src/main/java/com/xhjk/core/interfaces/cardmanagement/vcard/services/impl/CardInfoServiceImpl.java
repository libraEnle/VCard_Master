package com.xhjk.core.interfaces.cardmanagement.vcard.services.impl;

import com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.entitys.CardInfo;
import com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.respositories.CardInfoRepository;
import com.xhjk.core.interfaces.cardmanagement.vcard.Utils.WSXHJKClientUtils;
import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.daoExceps.BaseDAOException;
import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.daoExceps.CardInfoDAOException;
import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.servExceps.CardInfoServException;
import com.xhjk.core.interfaces.cardmanagement.vcard.services.CardInfoService;
import gov.datacenter.services.LivelihoodCardInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service("cardInfoService")
public class CardInfoServiceImpl implements CardInfoService {

    @Autowired
    private CardInfoRepository cardInfoRepository;

    @Autowired
    private WSXHJKClientUtils wsClient;

    @Override
    public List<CardInfo> searchCardListByCertainCardNo(String cardNo) throws Exception {


        List<CardInfo> tmpFoundResultList = null;

        try {

            try {
                tmpFoundResultList = this.cardInfoRepository.findByCardNo(cardNo);
            } catch (Exception e) {
                e.printStackTrace();
                throw new CardInfoDAOException(e, CardInfoDAOException.CARDINFO_DAO_SEARCH_ERROR);
            }

            if (tmpFoundResultList != null && tmpFoundResultList.size() > 0) {
                Iterator<CardInfo> tmpCardIterator = tmpFoundResultList.iterator();

                while (tmpCardIterator.hasNext()) {
                    CardInfo tmpCardInfo = tmpCardIterator.next();
                    if (tmpCardInfo != null) {
                        String tmpCardNo = tmpCardInfo.getCardNo();
                        String tmpCardSeq = tmpCardInfo.getCardSeq();

                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> cardNo + cardSeq: [" + tmpCardNo + " + " + tmpCardSeq + "]");
                    }
                }
            }
        } catch (BaseDAOException daoEx) {
            throw daoEx;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CardInfoServException(e, CardInfoServException.CARDINFO_SEARCH_ERROR);
        }

        return tmpFoundResultList;
    }

    @Override
    public List<CardInfo> searchCardInfoListByQuery(String cardNo, String cardSeq) {

        List<CardInfo> tmpFoundCardInfoList = this.cardInfoRepository.withCardNoAndCardSeqQuery(cardNo, cardSeq);
        return tmpFoundCardInfoList;
    }

    @Override
    public List<CardInfo> searchCardInfoByNameQuery(String cardNo) {

        List<CardInfo> tmpFoundCardInfoList = this.cardInfoRepository.withCardNoQuery(cardNo);
        return tmpFoundCardInfoList;
    }

    @Override
    public Page<CardInfo> searchCardByDefaultCondition(CardInfo cardInfo, Pageable page) {
        Page<CardInfo> tmpFoundCardInfoList = this.cardInfoRepository.withCustomQueryByDefaultQuery(cardInfo, page);
        return tmpFoundCardInfoList;
    }

    @Override
    public LivelihoodCardInfo getCardInfo(String certNo) throws Exception {
        return this.wsClient.getDocuWebServRep(certNo);
    }
}
