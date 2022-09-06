/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.com.tigo.da.utils;

/**
 *
 * @author Laurent G. Caceres
 */
public class ValidateTypeCard {
    
     public static CardType detectarTipo(String noCard) {

        String VISA_REGEX = "^4[0-9]{1,15}(?:[0-9]{3})?$";

        String MASTER_CARD_REGEX = "^5[1-5][0-9]{1,15}$";

        String AMERICAN_EXPRESS_REGEX = "^3[47][0-9]{1,14}$";

        String DINERS_CLUB_REGEX = "^3(?:0[0-5]|[68][0-9])[0-9]{1,13}$";

        String DISCOVER_REGEX = "^6(?:011|5[0-9]{2})[0-9]{1,15}$";

        String PALACIO_REGEX = "^6(?:520|5[0-9]{2})[0-9]{1,15}$";

        if (noCard.matches(VISA_REGEX)) {
            System.out.println("La tarjeta es visa");
            return CardType.VISA;
        } else if (noCard.matches(MASTER_CARD_REGEX)) {
            System.out.println("La tarjeta es Mastercard");
            return CardType.MASTERCARD;
        } else if (noCard.matches(DINERS_CLUB_REGEX)) {
            System.out.println("La tarjeta es diners club");
            return CardType.DINNERS;
        } else if (noCard.matches(DISCOVER_REGEX)) {
            System.out.println("La tarjeta es discover");
            return CardType.DISCOVER;
        } else if (noCard.matches(AMERICAN_EXPRESS_REGEX)) {
            System.out.println("La tarjeta es american express");
            return CardType.AMERICAN;
        } else if (noCard.matches(PALACIO_REGEX)) {
            System.out.println("La tarjeta es palacio del hierro");
            return CardType.PALACIO;
        }
        return CardType.INVALIDO;
    }
    
}
