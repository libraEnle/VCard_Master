package com.xhjk.core.interfaces.cardmanagement.vcard.controllers;

import com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.entitys.CardInfo;
import com.xhjk.core.interfaces.cardmanagement.vcard.Utils.SpecificationConfigUtils;
import com.xhjk.core.interfaces.cardmanagement.vcard.annotations.BusiSerialRecord;
import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.ctrlExceps.CardCtrlException;
import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.daoExceps.BaseDAOException;
import com.xhjk.core.interfaces.cardmanagement.vcard.exceptions.servExceps.BaseServException;
import com.xhjk.core.interfaces.cardmanagement.vcard.services.CardInfoService;
import gov.datacenter.services.LivelihoodCardInfo;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
public class VcardController {

    private final static Logger logger=LoggerFactory.getLogger(VcardController.class);

    @Autowired
    private StringEncryptor encryptor;

    @Resource(name = "cardInfoService")
    private CardInfoService cardInfoService;

    @Autowired
    private SpecificationConfigUtils specifiConfigUtil;

    @RequestMapping("/test")
    public HashMap getConfigInfo() {

        System.out.println(">>>>>>>>>>>>>>>>>>> getConfigMap(): " + this.specifiConfigUtil.getSpecifiConfigMap());

        this.logger.info(">>>>>>>>>>>>>>>>>>> getConfigMap(): " + this.specifiConfigUtil.getSpecifiConfigMap());
        this.logger.debug(">>>>>>>>>>>>>>>>>>>>>>>> getDebug info");

        String url = encryptor.encrypt("jdbc:oracle:thin:@10.4.161.34:1521:ORA11G");
        String name = encryptor.encrypt("xhjk");
        String password = encryptor.encrypt("1234554321");
        System.out.println(url+"----------------");
        System.out.println(name+"----------------");
        System.out.println(password+"----------------");

        this.logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> url: ["+url+"]");
        this.logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> name: ["+name+"]");
        this.logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> password: ["+password+"]");

        return this.specifiConfigUtil.getSpecifiConfigMap();
    }

    @RequestMapping("/testMsg")
    public String getTestMsg(@Value("${test.msg}") String preDefindMsg, @Value("${spring.config.location}") String confLocation) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>> preDefindMsg: " + preDefindMsg);
        System.out.println(">>>>>>>>>>>>>>>>>>>>> spring.config.location: " + confLocation);
        return preDefindMsg;
    }

    @RequestMapping("/testSearchCardList")
    public List<CardInfo> getCardInfoListByCertainCardNo(String cardNo) throws Exception {

        List<CardInfo> tmpCardListResult = null;
        try {
            tmpCardListResult = this.cardInfoService.searchCardListByCertainCardNo(cardNo);
        } catch (BaseDAOException baseDaoE) {
            throw baseDaoE;
        } catch (BaseServException baseServE) {
            throw baseServE;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CardCtrlException(e, CardCtrlException.CARDINFO_SEARCH_ERROR);
        }

        return tmpCardListResult;
    }

    @RequestMapping("/testQuery")
    public List<CardInfo> getCardListInfoByNoAndSeq(@RequestParam(name = "cardNo") String cardNo, @RequestParam(name = "cardSeq") String cardSeq) throws Exception {

        List<CardInfo> tmpCardListResult = this.cardInfoService.searchCardInfoListByQuery(cardNo, cardSeq);
        return tmpCardListResult;
    }

    @RequestMapping("/testNameQuery")
    public List<CardInfo> getCardListInfoByNameQuery(@RequestParam(name = "cardNo") String cardNo) throws Exception {

        List<CardInfo> tmpCardListResult = this.cardInfoService.searchCardInfoByNameQuery(cardNo);
        return tmpCardListResult;

    }

    @RequestMapping("/testDefaultQuery")
    public Page<CardInfo> getCardListInfoByDefaultQuery(@RequestParam(name = "cardNo") String cardNo) throws Exception {

        CardInfo tmpCardInfoCriteria = new CardInfo();
        tmpCardInfoCriteria.setCardNo(cardNo);
        Pageable tmpPage = new PageRequest(0, 10);

        Page<CardInfo> tmpCardPageResult = this.cardInfoService.searchCardByDefaultCondition(tmpCardInfoCriteria, tmpPage);
        return tmpCardPageResult;

    }

    @BusiSerialRecord(busCode = "1019", desc = "test due before business process")
    @RequestMapping("/testAddCard")
    public String addCardInfo(CardInfo cardInfo) {

        System.out.println(">>>>>>>>>>>>>>>>>> cardInfo: " + cardInfo);

        return "syccessful";
    }

    @RequestMapping("/getCardInfo")
    public LivelihoodCardInfo getXHJKCardInfo(@RequestParam(name = "certNo") String certNo)  throws Exception {

        return this.cardInfoService.getCardInfo(certNo);

    }

}
