package com.xhjk.core.interfaces.cardmanagement.vcard.DAOs.entitys;


import java.io.Serializable;
import java.util.Objects;

/**
 * JPA复合ID类
 */
public class CardInfoId implements Serializable {

    private String cardNo;
    private String cardSeq;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardInfoId that = (CardInfoId) o;
        return Objects.equals(cardNo, that.cardNo) &&
                Objects.equals(cardSeq, that.cardSeq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNo, cardSeq);
    }

    public static void main(String[] args){
        CardInfoId tmpCardInfoId0=new CardInfoId();
        CardInfoId tmpCardInfoId1=new CardInfoId();

        String tmp0CardNo="1234567890";
        String tmp0CardSeq="1";
        String tmp1CardNo="1234567890";
        String tmp1CardSeq="1";

        tmpCardInfoId0.setCardNo(tmp0CardNo);
        tmpCardInfoId0.setCardSeq(tmp0CardSeq);

        tmpCardInfoId1.setCardNo(tmp1CardNo);
        tmpCardInfoId1.setCardSeq(tmp1CardSeq);

        System.out.println(">>>>>>>>>>>>> tmpCardInfoId0.hashCode(): "+tmpCardInfoId0.hashCode());
        System.out.println(">>>>>>>>>>>>> tmpCardInfoId0.hashCode(): "+tmpCardInfoId1.hashCode());

        if(tmpCardInfoId0.equals(tmpCardInfoId1)){
            System.out.println(">>>>>>>>>>>>>>>>> the same");
        }else{
            System.out.println(">>>>>>>>>>>>>>>>> not the same");
        }

    }
}
