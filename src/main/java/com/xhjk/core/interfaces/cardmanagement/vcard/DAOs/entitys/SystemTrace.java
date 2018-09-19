package com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cms_sys_trace")
@IdClass(SystemTraceID.class)
public class SystemTrace implements Serializable {

    @Id
    private String sysSn;
    @Id
    private String txnDate;

    private String txnTime;
    private String txnCode;
    private String txnChnl;
    private String fronSn;
    private String fronDate;
    private String fronTime;
    private String lostFlg;
    private String chgFlg;
    private String dpstFlg;
    private String cardNo;
    private String newCardNo;
    private String custNo;
    private String docuNum;
    private String amtFlag;
    private String txnAmt;
    private String rtnAmt;
    private String transAmt;
    private String acctBal;
    private String txnOrgId;
    private String txnTermId;
    private String txnTlrNo;
    private String authTlrNo;
    private String backSn;
    private String backDate;
    private String backTime;
    private String backRepCode;
    private String backRepDes;
    private String cupsDate;
    private String cupsSn;
    private String bankCardNo;
    private String flatCost;
    private String fee;
    private String rspCode;
    private String repDes;
    private String revFlag;
    private String canlFlag;
    private String oriTxnDate;
    private String oriSysSn;
    private String lastUpdTlrNo;
    private String lastUpdDateTime;
    private String rmkInfo;
    private String orderNo;
    private String oriOrderNo;
    private String misc1;
    private String misc2;
    private String misc3;
    private String misc4;
    private String cardSeq;
    private String flatCostFlag;
    private String discountAmt;
    private String description;

    public String getSysSn() {
        return sysSn;
    }

    public void setSysSn(String sysSn) {
        this.sysSn = sysSn;
    }

    public String getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    public String getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }

    public String getTxnCode() {
        return txnCode;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public String getTxnChnl() {
        return txnChnl;
    }

    public void setTxnChnl(String txnChnl) {
        this.txnChnl = txnChnl;
    }

    public String getFronSn() {
        return fronSn;
    }

    public void setFronSn(String fronSn) {
        this.fronSn = fronSn;
    }

    public String getFronDate() {
        return fronDate;
    }

    public void setFronDate(String fronDate) {
        this.fronDate = fronDate;
    }

    public String getFronTime() {
        return fronTime;
    }

    public void setFronTime(String fronTime) {
        this.fronTime = fronTime;
    }

    public String getLostFlg() {
        return lostFlg;
    }

    public void setLostFlg(String lostFlg) {
        this.lostFlg = lostFlg;
    }

    public String getChgFlg() {
        return chgFlg;
    }

    public void setChgFlg(String chgFlg) {
        this.chgFlg = chgFlg;
    }

    public String getDpstFlg() {
        return dpstFlg;
    }

    public void setDpstFlg(String dpstFlg) {
        this.dpstFlg = dpstFlg;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getNewCardNo() {
        return newCardNo;
    }

    public void setNewCardNo(String newCardNo) {
        this.newCardNo = newCardNo;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getDocuNum() {
        return docuNum;
    }

    public void setDocuNum(String docuNum) {
        this.docuNum = docuNum;
    }

    public String getAmtFlag() {
        return amtFlag;
    }

    public void setAmtFlag(String amtFlag) {
        this.amtFlag = amtFlag;
    }

    public String getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    public String getRtnAmt() {
        return rtnAmt;
    }

    public void setRtnAmt(String rtnAmt) {
        this.rtnAmt = rtnAmt;
    }

    public String getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(String transAmt) {
        this.transAmt = transAmt;
    }

    public String getAcctBal() {
        return acctBal;
    }

    public void setAcctBal(String acctBal) {
        this.acctBal = acctBal;
    }

    public String getTxnOrgId() {
        return txnOrgId;
    }

    public void setTxnOrgId(String txnOrgId) {
        this.txnOrgId = txnOrgId;
    }

    public String getTxnTermId() {
        return txnTermId;
    }

    public void setTxnTermId(String txnTermId) {
        this.txnTermId = txnTermId;
    }

    public String getTxnTlrNo() {
        return txnTlrNo;
    }

    public void setTxnTlrNo(String txnTlrNo) {
        this.txnTlrNo = txnTlrNo;
    }

    public String getAuthTlrNo() {
        return authTlrNo;
    }

    public void setAuthTlrNo(String authTlrNo) {
        this.authTlrNo = authTlrNo;
    }

    public String getBackSn() {
        return backSn;
    }

    public void setBackSn(String backSn) {
        this.backSn = backSn;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    public String getBackRepCode() {
        return backRepCode;
    }

    public void setBackRepCode(String backRepCode) {
        this.backRepCode = backRepCode;
    }

    public String getBackRepDes() {
        return backRepDes;
    }

    public void setBackRepDes(String backRepDes) {
        this.backRepDes = backRepDes;
    }

    public String getCupsDate() {
        return cupsDate;
    }

    public void setCupsDate(String cupsDate) {
        this.cupsDate = cupsDate;
    }

    public String getCupsSn() {
        return cupsSn;
    }

    public void setCupsSn(String cupsSn) {
        this.cupsSn = cupsSn;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getFlatCost() {
        return flatCost;
    }

    public void setFlatCost(String flatCost) {
        this.flatCost = flatCost;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getRepDes() {
        return repDes;
    }

    public void setRepDes(String repDes) {
        this.repDes = repDes;
    }

    public String getRevFlag() {
        return revFlag;
    }

    public void setRevFlag(String revFlag) {
        this.revFlag = revFlag;
    }

    public String getCanlFlag() {
        return canlFlag;
    }

    public void setCanlFlag(String canlFlag) {
        this.canlFlag = canlFlag;
    }

    public String getOriTxnDate() {
        return oriTxnDate;
    }

    public void setOriTxnDate(String oriTxnDate) {
        this.oriTxnDate = oriTxnDate;
    }

    public String getOriSysSn() {
        return oriSysSn;
    }

    public void setOriSysSn(String oriSysSn) {
        this.oriSysSn = oriSysSn;
    }

    public String getLastUpdTlrNo() {
        return lastUpdTlrNo;
    }

    public void setLastUpdTlrNo(String lastUpdTlrNo) {
        this.lastUpdTlrNo = lastUpdTlrNo;
    }

    public String getLastUpdDateTime() {
        return lastUpdDateTime;
    }

    public void setLastUpdDateTime(String lastUpdDateTime) {
        this.lastUpdDateTime = lastUpdDateTime;
    }

    public String getRmkInfo() {
        return rmkInfo;
    }

    public void setRmkInfo(String rmkInfo) {
        this.rmkInfo = rmkInfo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOriOrderNo() {
        return oriOrderNo;
    }

    public void setOriOrderNo(String oriOrderNo) {
        this.oriOrderNo = oriOrderNo;
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

    public String getMisc3() {
        return misc3;
    }

    public void setMisc3(String misc3) {
        this.misc3 = misc3;
    }

    public String getMisc4() {
        return misc4;
    }

    public void setMisc4(String misc4) {
        this.misc4 = misc4;
    }

    public String getCardSeq() {
        return cardSeq;
    }

    public void setCardSeq(String cardSeq) {
        this.cardSeq = cardSeq;
    }

    public String getFlatCostFlag() {
        return flatCostFlag;
    }

    public void setFlatCostFlag(String flatCostFlag) {
        this.flatCostFlag = flatCostFlag;
    }

    public String getDiscountAmt() {
        return discountAmt;
    }

    public void setDiscountAmt(String discountAmt) {
        this.discountAmt = discountAmt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void transferFrom(Object obj) {

        if (obj instanceof CardInfo) {

            CardInfo tmpCardInfo = (CardInfo) obj;

            this.cardNo = tmpCardInfo.getCardNo();
            this.cardSeq = tmpCardInfo.getCardSeq();
            this.txnDate = "20180912";
            this.sysSn = "testSysSn";
        }
    }

    public void transferFrom(CardInfo cardInfo) {

        this.cardNo = cardInfo.getCardNo();
        this.cardSeq = cardInfo.getCardSeq();
        this.txnDate = "20180912";
        this.sysSn = "testSysSn";
        this.txnTime = "120754";
        this.txnCode = "0988";
        this.txnChnl = "1";
        this.fronSn = "4234324";
        this.fronDate = "20180913";
        this.fronTime = "121145";
        this.txnTermId = "44324";
        this.txnTlrNo = "0033";

    }
}
