package com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.entitys;

import java.io.Serializable;
import java.util.Objects;

/**
 * JPA复合ID类
 */
public class SystemTraceID implements Serializable {

    private String sysSn;
    private String txnDate;

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

    @Override
    public int hashCode() {
        return Objects.hash(sysSn, txnDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemTraceID that = (SystemTraceID) o;
        return Objects.equals(sysSn, that.sysSn) &&
                Objects.equals(txnDate, that.txnDate);
    }
}
