
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;

public class StatsCycleDTO extends DTO{
    @ApiModelProperty(value = "countCLient", dataType = "Integer", allowableValues = "Integer values available", example = "898")
    private int countClient;
    @ApiModelProperty(value = "countDetail", dataType = "Integer", allowableValues = "Integer values available", example = "800")
    private int countDetail;
    @ApiModelProperty(value = "paymentApplied", dataType = "Integer", allowableValues = "Integer values available", example = "100")
    private int paymentApplied;
    @ApiModelProperty(value = "errorPayment", dataType = "Integer", allowableValues = "Integer values available", example = "100")
    private int errorPayment;
    @ApiModelProperty(value = "reversePayments", dataType = "Integer", allowableValues = "Integer values available", example = "0")
    private int reversePayments;
    @ApiModelProperty(value = "jsonError", dataType = "String", allowableValues = "string values available", example = "{}")
    private String jsonError;

    public int getCountClient() {
        return countClient;
    }

    public void setCountClient(int countClient) {
        this.countClient = countClient;
    }

    public int getCountDetail() {
        return countDetail;
    }

    public void setCountDetail(int countDetail) {
        this.countDetail = countDetail;
    }

    public int getPaymentApplied() {
        return paymentApplied;
    }

    public void setPaymentApplied(int paymentApplied) {
        this.paymentApplied = paymentApplied;
    }

    public int getReversePayments() {
        return reversePayments;
    }

    public void setReversePayments(int reversePayments) {
        this.reversePayments = reversePayments;
    }

    public String getJsonError() {
        return jsonError;
    }

    public void setJsonError(String jsonError) {
        this.jsonError = jsonError;
    }

    public int getErrorPayment() {
        return errorPayment;
    }

    public void setErrorPayment(int errorPayment) {
        this.errorPayment = errorPayment;
    }
}
