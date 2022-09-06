
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;

public class BankDeleteModel {
    @ApiModelProperty(value = "subscriber", dataType = "String", allowableValues = "string formar subscriber",example="99999999")
    private String subscriber;
    @ApiModelProperty(value = "account", dataType = "String", allowableValues = "string formar account",example="6015991328")
    private String account;
    @ApiModelProperty(value = "bindNumber", dataType = "String", allowableValues = "string formar bindNumber",example="1800")
    private String bindNumber;
    @ApiModelProperty(value = "expirationDate", dataType = "String", allowableValues = "string formar expirationDate",example="20200905")
    private String expirationDate;
    @ApiModelProperty(value = "cycle", dataType = "String", allowableValues = "string formar cycle",example="20200726")
    private String cycle;
    @ApiModelProperty(value = "bank", dataType = "String", allowableValues = "string formar bank",example="FIC")
    private String bank;
    @ApiModelProperty(value = "amount", dataType = "String", allowableValues = "string formar amount",example="9948.64")
    private String amount;
    @ApiModelProperty(value = "transactionDate", dataType = "String", allowableValues = "string formar transactionDate",example="20200904")
    private String transactionDate;
    @ApiModelProperty(value = "error", dataType = "String", allowableValues = "string formar error",example="05")
    private String error;
    @ApiModelProperty(value = "message", dataType = "String", allowableValues = "string formar message",example="La cuenta no tiene disponible")
    private String message;
    /**
     * @return the subscriber
     */
    public final String getSubscriber() {
            return subscriber;
    }
    /**
     * @param subscriber the subscriber to set
     */
    public final void setSubscriber(String subscriber) {
            this.subscriber = subscriber;
    }
    /**
     * @return the account
     */
    public final String getAccount() {
            return account;
    }
    /**
     * @param account the account to set
     */
    public final void setAccount(String account) {
            this.account = account;
    }
    /**
     * @return the bindNumber
     */
    public final String getBindNumber() {
            return bindNumber;
    }
    /**
     * @param bindNumber the bindNumber to set
     */
    public final void setBindNumber(String bindNumber) {
            this.bindNumber = bindNumber;
    }
    /**
     * @return the expirationDate
     */
    public final String getExpirationDate() {
            return expirationDate;
    }
    /**
     * @param expirationDate the expirationDate to set
     */
    public final void setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
    }
    /**
     * @return the cycle
     */
    public final String getCycle() {
            return cycle;
    }
    /**
     * @param cycle the cycle to set
     */
    public final void setCycle(String cycle) {
            this.cycle = cycle;
    }
    /**
     * @return the bank
     */
    public final String getBank() {
            return bank;
    }
    /**
     * @param bank the bank to set
     */
    public final void setBank(String bank) {
            this.bank = bank;
    }
    /**
     * @return the amount
     */
    public final String getAmount() {
            return amount;
    }
    /**
     * @param amount the amount to set
     */
    public final void setAmount(String amount) {
            this.amount = amount;
    }
    /**
     * @return the transactionDate
     */
    public final String getTransactionDate() {
            return transactionDate;
    }
    /**
     * @param transactionDate the transactionDate to set
     */
    public final void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
    }
    /**
     * @return the error
     */
    public final String getError() {
            return error;
    }
    /**
     * @param error the error to set
     */
    public final void setError(String error) {
            this.error = error;
    }
    /**
     * @return the message
     */
    public final String getMessage() {
            return message;
    }
    /**
     * @param message the message to set
     */
    public final void setMessage(String message) {
            this.message = message;
    }
}
