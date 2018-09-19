package com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.entitys;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name="CardInfo.withCardNoQuery",query = "select c from CardInfo c where c.cardNo=?1")
@Entity
@Table(name = "CMS_M1_CARD_INFO")
@IdClass(CardInfoId.class)
public class CardInfo implements Serializable {
    @Id
    private String cardNo;
    @Id
    private String cardSeq;

    private String cardProdId;
    private String bankCardNo;
    private String issFlg;
//    private Date expDate;

    private String expDate;

    private String cardStat;
    private String custId;
    private String issOrg;
    private String opnCardTlrId;
//    private Date opnCardDate;
//    private Date clsCardDate;

    private String opnCardDate;
    private String clsCardDate;

    private String lastUpdTlrNo;

//    private Time lastUpdDateTime;
    private String lastUpdDateTime;

    private String remark;
    private String misc1;
    private String misc2;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardSeq() {
        return cardSeq;
    }

    public void setCardSeq(String cardSeq) {
        this.cardSeq = cardSeq;
    }

    public String getCardProdId() {
        return cardProdId;
    }

    public void setCardProdId(String cardProdId) {
        this.cardProdId = cardProdId;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getIssFlg() {
        return issFlg;
    }

    public void setIssFlg(String issFlg) {
        this.issFlg = issFlg;
    }


    public String getCardStat() {
        return cardStat;
    }

    public void setCardStat(String cardStat) {
        this.cardStat = cardStat;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getIssOrg() {
        return issOrg;
    }

    public void setIssOrg(String issOrg) {
        this.issOrg = issOrg;
    }

    public String getOpnCardTlrId() {
        return opnCardTlrId;
    }

    public void setOpnCardTlrId(String opnCardTlrId) {
        this.opnCardTlrId = opnCardTlrId;
    }


    public String getLastUpdTlrNo() {
        return lastUpdTlrNo;
    }

    public void setLastUpdTlrNo(String lastUpdTlrNo) {
        this.lastUpdTlrNo = lastUpdTlrNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMisc1() {
        return misc1;
    }

    public void setMisc1(String misc1) {
        this.misc1 = misc1;
    }

    public String getMisc2() {
        return misc2;
    }

    public void setMisc2(String misc2) {
        this.misc2 = misc2;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getOpnCardDate() {
        return opnCardDate;
    }

    public void setOpnCardDate(String opnCardDate) {
        this.opnCardDate = opnCardDate;
    }

    public String getClsCardDate() {
        return clsCardDate;
    }

    public void setClsCardDate(String clsCardDate) {
        this.clsCardDate = clsCardDate;
    }

    public String getLastUpdDateTime() {
        return lastUpdDateTime;
    }

    public void setLastUpdDateTime(String lastUpdDateTime) {
        this.lastUpdDateTime = lastUpdDateTime;
    }
}
