
package hn.com.tigo.da.dto;

import hn.com.tigo.da.dto.DTO;
import io.swagger.annotations.ApiModelProperty;

public class ProcessDetailPayDTO extends DTO{
    @ApiModelProperty(value = "id", dataType = "String", allowableValues = "string values available", example = "88323d-2345-3563-3dsfer57")
    private String id;
    @ApiModelProperty(value = "idBankProcess", dataType = "String", allowableValues = "string values available", example = "3455663we-43465-3563-3dsfer57")
    private String idBankProcess;
    @ApiModelProperty(value = "status", dataType = "String", allowableValues = "string values available", example = "1")
    private int status;
    @ApiModelProperty(value = "acctCode", dataType = "String", allowableValues = "string values available", example = "4332358756")
    private String acctCode;
    @ApiModelProperty(value = "subscriberId", dataType = "String", allowableValues = "string values available", example = "99998888")
    private String subscriberId;
    @ApiModelProperty(value = "noCard", dataType = "String", allowableValues = "string values available", example = "*******9234")
    private String noCard;
    @ApiModelProperty(value = "ammount", dataType = "String", allowableValues = "string values available", example = "100.00")
    private String ammount;
    @ApiModelProperty(value = "commentPay", dataType = "String", allowableValues = "string values available", example = "Pagos aplicados")
    private String commentPay;
    @ApiModelProperty(value = "paymentId", dataType = "String", allowableValues = "string values available", example = "1")
    private String paymentId;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "string values available", example = "06-04-2022 12:01:03")
    private String createdString;
    @ApiModelProperty(value = "groupPayment", dataType = "String", allowableValues = "string values available", example = "0")
    private String groupPayment;
    @ApiModelProperty(value = "fileName", dataType = "String", allowableValues = "string values available", example = "fileName.txt")
    private String fileName;
    @ApiModelProperty(value = "bank", dataType = "String", allowableValues = "string values available", example = "Bac")
    private String bank;
    @ApiModelProperty(value = "cycle", dataType = "String", allowableValues = "string values available", example = "20220504")
    private String cycle;
    @ApiModelProperty(value = "bankId", dataType = "String", allowableValues = "string values available", example = "1000")
    private String bankId;
    @ApiModelProperty(value = "errorBank", dataType = "String", allowableValues = "string values available", example = "")
    private String errorBank;
    @ApiModelProperty(value = "codeErrorBank", dataType = "String", allowableValues = "string values available", example = "OK")
    private String codeErrorBank;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdBankProcess() {
        return idBankProcess;
    }

    public void setIdBankProcess(String idBankProcess) {
        this.idBankProcess = idBankProcess;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAcctCode() {
        return acctCode;
    }

    public void setAcctCode(String acctCode) {
        this.acctCode = acctCode;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getNoCard() {
        return noCard;
    }

    public void setNoCard(String noCard) {
        this.noCard = noCard;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public String getCommentPay() {
        return commentPay;
    }

    public void setCommentPay(String commentPay) {
        this.commentPay = commentPay;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCreatedString() {
        return createdString;
    }

    public void setCreatedString(String createdString) {
        this.createdString = createdString;
    }

    public String getGroupPayment() {
        return groupPayment;
    }

    public void setGroupPayment(String groupPayment) {
        this.groupPayment = groupPayment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getErrorBank() {
        return errorBank;
    }

    public void setErrorBank(String errorBank) {
        this.errorBank = errorBank;
    }

    public String getCodeErrorBank() {
        return codeErrorBank;
    }

    public void setCodeErrorBank(String codeErrorBank) {
        this.codeErrorBank = codeErrorBank;
    }
}
