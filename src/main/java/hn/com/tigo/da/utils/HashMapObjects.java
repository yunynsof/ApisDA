
package hn.com.tigo.da.utils;

import java.util.HashMap;

public class HashMapObjects {
    public static HashMap<String, String> getCardInfoMap(){
        HashMap<String, String> cardInfo = new HashMap<String, String>();
        cardInfo.put("name", "NAME");
        cardInfo.put("noCard", "NO_CARD");
        cardInfo.put("typeCard", "TYPE_CARD");
        cardInfo.put("modalityCard", "MODALITY_CARD");
        cardInfo.put("year", "YEAR");
        cardInfo.put("month", "MONTH");
        cardInfo.put("issuingBank", "ISSUING_BANK");
        cardInfo.put("processorBank", "PROCESSOR_BANK");
        cardInfo.put("groupPayment", "GROUP_PAYMENT");
        cardInfo.put("cycle", "CYCLE");
        cardInfo.put("status", "STATUS");
        cardInfo.put("acctCode", "ACCTCODE");
        cardInfo.put("subscriberId", "SUBSCRIBER_ID");
        return cardInfo;
    }
}
