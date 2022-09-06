package hn.com.tigo.da.utils;

import java.util.ArrayList;
import java.util.List;

import com.tigo.josm.gateway.services.order.additionalparameterdto.v1.AdditionalParameters;
import com.tigo.josm.gateway.services.order.additionalparameterdto.v1.Parameter;
import com.tigo.josm.gateway.services.order.simpleorderrequest.v1.SimpleOrderRequest;

import hn.com.tigo.resources.constants.DAListenerConstants;


public class ApiDAUtils {

	public static SimpleOrderRequest getRequestOrder(final String accountCode,
			AdditionalParameters additionalParameters, String comments, int channelId, long productId) {
		SimpleOrderRequest simpleOrderRequest = new SimpleOrderRequest();
		simpleOrderRequest.setChannelId(channelId);
		simpleOrderRequest.setSubscriberId(accountCode);
		simpleOrderRequest.setProductId(productId);
		simpleOrderRequest.setQuantity(DAListenerConstants.QUANTITY);
		simpleOrderRequest.setComment(comments);

		simpleOrderRequest.setAdditionalParameters(additionalParameters);

		return simpleOrderRequest;
	}

	public static AdditionalParameters getAdditionalParameters(List<Parameter> parameter) {
		AdditionalParameters additionalParameters = new AdditionalParameters();
		for (int i = 0; i < parameter.size(); i++) {
			additionalParameters.getParameter().add(i, parameter.get(i));
		}
		return additionalParameters;
	}

	public static List<Parameter> obtainParameter(List<Parameter> parameter, String key, String value) {
		Parameter parameter1 = new Parameter();
		if (value == null || value.equals(""))
			value = "0";

		parameter1.setKey(key);
		parameter1.setValue(value);

		int i = (parameter.size());
		parameter.add(i, parameter1);
		return parameter;
	}
	
    public static List<Parameter> obtainParameters(String accountCode, String subscriberId, String invoiceId, String date,
			String amount, String bankProcessor, String payTypeEocMsg) {
		List<Parameter> parameter = new ArrayList<Parameter>();

		parameter = obtainParameter(parameter, DAListenerConstants.ID_TYPE,
				DAListenerConstants.ACCTCODE);

		parameter = obtainParameter(parameter, DAListenerConstants.ID, accountCode);

		parameter = obtainParameter(parameter, DAListenerConstants.SUBSCRIBERID, subscriberId);

		parameter = obtainParameter(parameter, DAListenerConstants.PAY_TYPE_EOC,
				payTypeEocMsg);

		parameter = obtainParameter(parameter, DAListenerConstants.DOCUMENT_NUMBER, invoiceId);

		parameter = obtainParameter(parameter, DAListenerConstants.PAY_DATE, date);

		parameter = obtainParameter(parameter, DAListenerConstants.AMOUNT, amount);

		parameter = obtainParameter(parameter, DAListenerConstants.CURRENCY,
				DAListenerConstants.CURRENCY_TYPE);

		parameter = obtainParameter(parameter, DAListenerConstants.PAY_CHANNEL, DAListenerConstants.DA);

		parameter = obtainParameter(parameter, DAListenerConstants.BANK, bankProcessor);

		parameter = obtainParameter(parameter, DAListenerConstants.RESPONSE,
				DAListenerConstants.SUCCESS);

		return parameter;
	}
	
}
