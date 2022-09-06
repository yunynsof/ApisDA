/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.com.tigo.da.utils;

import static hn.com.tigo.da.utils.ValidateTypeCard.detectarTipo;
import tigo.com.invoice.utils.GenericException;

/**
 *
 * @author laure
 */
public class ValidateCard {

    public boolean validarTarjeta(String numTarjeta) {

        int LONGITUD_VALIDA_TARJETAS = 16;
        int LONGITUD_VALIDA_TARJETAS_AMERICAN = 15;
        int LONGITUD_VALIDA_TARJETAS_DINNER = 14;
        boolean result = true;
        CardType tipo = detectarTipo(numTarjeta);

        switch (tipo) {
            case VISA:
            case MASTERCARD:
            case DISCOVER:
            case PALACIO:
                result = (numTarjeta.length() == LONGITUD_VALIDA_TARJETAS) ? true : false;
                break;
            case AMERICAN:
                result = (numTarjeta.length() == LONGITUD_VALIDA_TARJETAS_AMERICAN) || (numTarjeta.length() == LONGITUD_VALIDA_TARJETAS) ? true : false;
                break;
            case DINNERS:
                result = (numTarjeta.length() == LONGITUD_VALIDA_TARJETAS_DINNER) ? true : false;
                break;
            default:
                result = false;
        }
        return result;

    }
}
