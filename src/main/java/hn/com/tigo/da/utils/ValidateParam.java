/**
 * this class validate the input parameters in the api.
 * Class ValidateParam.
 *
 * @author Kerim Fortin
 * @since 26/6/2020
 */
package hn.com.tigo.da.utils;

import tigo.com.invoice.utils.Constants;
import tigo.com.invoice.utils.GenericException;
import tigo.com.invoice.validation.ValidateGeneric;

public class ValidateParam {

    public ValidateParam() {
    }

    public void validatedGetCardInfoByClientApi(final String type, final String param) throws GenericException {
        ValidateGeneric.validateParameter("type", type, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("param", param, Constants.ALFANUMERIC, true);
    }

    public void validatedSelectCardInfo(final String id) throws GenericException {
        ValidateGeneric.validateParameter("id", id, Constants.ALFANUMERIC_EXTENDED, true);
    }

    public void validatedCreateMPApi(final String name, final String noCard, final String typeCard, final String modalityCard, final String year, final String month, final String issuingBank, final String processorBank, final String groupPayment, final String cycle, final String status, final String acctCode, final String subscriberId) throws GenericException {
        ValidateGeneric.validateParameter("name", name.toUpperCase(), Constants.ALFANUMERIC_ALL, true);
        ValidateGeneric.validateParameter("noCard", noCard, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("typeCard", typeCard, Constants.ALFANUMERIC_EXTENDED, true);
        ValidateGeneric.validateParameter("modalityCard", modalityCard, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("year", year, Constants.DIGITS_4, true);
        ValidateGeneric.validateParameter("month", month, Constants.DIGITS_2, true);
        ValidateGeneric.validateParameter("issuingBank", issuingBank, Constants.ALFANUMERIC_EXTENDED, true);
        ValidateGeneric.validateParameter("processorBank", processorBank, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("groupPayment", groupPayment, Constants.NUMERIC, true);
        ValidateGeneric.validateParameter("cycle", cycle, Constants.DIGITS_2, true);
        ValidateGeneric.validateParameter("status", status, Constants.NUMERIC, true);
        ValidateGeneric.validateParameter("acctCode", acctCode, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("subscriberId", subscriberId, Constants.ALFANUMERIC, false);
    }

    public void validatedUpdateMPApi(final String id) throws GenericException {
        ValidateGeneric.validateParameter("id", id, Constants.ALFANUMERIC_ALL, true);
    }

    public void validatedCreateIncluExcluApi(final String id, final String numpla, final String type_tran, final String anexo, final String noCard, final String cardIsueDate, final String expDate, final String status, final String cycle) throws GenericException {
        ValidateGeneric.validateParameter("numpla", numpla, Constants.ALFANUMERIC, false);
        ValidateGeneric.validateParameter("type_tran", type_tran, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("anexo", anexo, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("noCard", noCard, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("cardIsueDate", cardIsueDate, Constants.ALFANUMERIC, false);
        ValidateGeneric.validateParameter("expDate", expDate, Constants.ALFANUMERIC, false);
        ValidateGeneric.validateParameter("status", status, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("cycle", cycle, Constants.ALFANUMERIC, true);
    }

    public void validatedUpdateIncluExcluApi(final String id, final String numpla, final String type_tran, final String anexo, final String noCard, final String cardIsueDate, final String expDate, final String status, final String cycle) throws GenericException {
        ValidateGeneric.validateParameter("id", id, Constants.ALFANUMERIC_ALL, true);
        ValidateGeneric.validateParameter("numpla", numpla, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("type_tran", type_tran, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("anexo", anexo, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("noCard", noCard, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("cardIsueDate", cardIsueDate, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("expDate", expDate, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("status", status, Constants.ALFANUMERIC, true);
        ValidateGeneric.validateParameter("cycle", cycle, Constants.ALFANUMERIC, true);
    }

    public void validatedGetDetailProcess(final String process, final String type, final String param) throws GenericException {
        ValidateGeneric.validateParameter("process", process, Constants.ALFANUMERIC_EXTENDED, true);
        ValidateGeneric.validateParameter("type", type, Constants.ALFANUMERIC_EXTENDED, true);
        ValidateGeneric.validateParameter("param", param, Constants.ALFANUMERIC_EXTENDED, true);
    }

    public void validatedInsertProcessBank(final String bankId, final String cycle, final String user) throws GenericException {
        ValidateGeneric.validateParameter("bankId", bankId, Constants.ALFANUMERIC_EXTENDED, true);
        ValidateGeneric.validateParameter("cycle", cycle, Constants.ALFANUMERIC_EXTENDED, true);
        ValidateGeneric.validateParameter("user", user, Constants.ALFANUMERIC_EXTENDED, true);
    }

    public void validatedUpdateProcessBank(final String id, final String status) throws GenericException {
        ValidateGeneric.validateParameter("id", id, Constants.ALFANUMERIC_EXTENDED, true);
        ValidateGeneric.validateParameter("status", status, Constants.ALFANUMERIC_EXTENDED, true);
    }

    public void validatedDeleteMp(final String cycle, final String bank, final String errorBank) throws GenericException {
        ValidateGeneric.validateParameter("cycle", cycle, Constants.ALFANUMERIC_EXTENDED, true);
        ValidateGeneric.validateParameter("bank", bank, Constants.ALFANUMERIC_EXTENDED, true);
        ValidateGeneric.validateParameter("errorBank", errorBank, Constants.ALFANUMERIC_EXTENDED, true);
    }

    public void validatedIdProcessBank(final String id) throws GenericException {
        ValidateGeneric.validateParameter("id", id, Constants.ALFANUMERIC_EXTENDED, true);
    }

    public void validatedSelectDeleteMP(final String cycle, final String bankId, final String error) throws GenericException {
        ValidateGeneric.validateParameter("cycle", cycle, Constants.ALFANUMERIC_EXTENDED, true);
        ValidateGeneric.validateParameter("bankId", bankId, Constants.ALFANUMERIC_EXTENDED, true);
        ValidateGeneric.validateParameter("error", error, Constants.ALFANUMERIC_EXTENDED, true);
    }

    public void validatedSelectDetailsReprocess(final String id) throws GenericException {
        ValidateGeneric.validateParameter("id", id, Constants.ALFANUMERIC_EXTENDED, true);
    }

}
