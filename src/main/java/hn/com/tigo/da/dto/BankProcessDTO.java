
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;

public class BankProcessDTO extends DTO{
    @ApiModelProperty(value = "id", dataType = "String", allowableValues = "Integer values available", example = "423sfd-324234-234234-jsadasd23g")
    private String id;
    @ApiModelProperty(value = "bankId", dataType = "String", allowableValues = "Integer values available", example = "1000")
    private long bankId;
    @ApiModelProperty(value = "cycle", dataType = "String", allowableValues = "string values available", example = "20220605")
    private String cycle;
    @ApiModelProperty(hidden = true)
    private String sequence;
    @ApiModelProperty(hidden = true)
    private String ammount;
    @ApiModelProperty(value = "countClient", dataType = "Integer", allowableValues = "Integer values available", example = "99")
    private long countCLient;
    @ApiModelProperty(value = "status", dataType = "Integer", allowableValues = "Integer values available", example = "0")
    private long status; 
    @ApiModelProperty(value = "statusDesc", dataType = "String", allowableValues = "String values available", example = "Pendiente generacion archivo de salida")
    private String statusDesc; 
    @ApiModelProperty(value = "createdString", dataType = "String", allowableValues = "String values available", example = "2022-06-30 26:29:54")
    private String createdString;
    @ApiModelProperty(hidden = true)
    private String dateEndString;
    @ApiModelProperty(hidden = true)
    private String dateInitString;
    @ApiModelProperty(hidden = true)
    private String uuidPending;
    @ApiModelProperty(hidden = true)
    private String invoiceBase64;
    @ApiModelProperty(value = "fileNameDA", dataType = "String", allowableValues = "string values available", example = "Bac_20220506.txt")
    private String fileNameDA;
    @ApiModelProperty(hidden = true)
    private String fileNameBank;
    @ApiModelProperty(hidden = true)
    private String uuidIncluExclu;
    @ApiModelProperty(value = "user", dataType = "String", allowableValues = "string values available", example = "Edwin.Zambrano")
    private String user;
    @ApiModelProperty(hidden = true)
    private String error;
    @ApiModelProperty(hidden = true)
    private String base64Bank;
    @ApiModelProperty(hidden = true)
    private String reProcess;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }

    public long getCountCLient() {
        return countCLient;
    }

    public void setCountCLient(long countCLient) {
        this.countCLient = countCLient;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getCreatedString() {
        return createdString;
    }

    public void setCreatedString(String createdString) {
        this.createdString = createdString;
    }

    public String getDateEndString() {
        return dateEndString;
    }

    public void setDateEndString(String dateEndString) {
        this.dateEndString = dateEndString;
    }

    public String getDateInitString() {
        return dateInitString;
    }

    public void setDateInitString(String dateInitString) {
        this.dateInitString = dateInitString;
    }

    public String getUuidPending() {
        return uuidPending;
    }

    public void setUuidPending(String uuidPending) {
        this.uuidPending = uuidPending;
    }

    public String getInvoiceBase64() {
        return invoiceBase64;
    }

    public void setInvoiceBase64(String invoiceBase64) {
        this.invoiceBase64 = invoiceBase64;
    }

    public String getFileNameDA() {
        return fileNameDA;
    }

    public void setFileNameDA(String fileNameDA) {
        this.fileNameDA = fileNameDA;
    }

    public String getFileNameBank() {
        return fileNameBank;
    }

    public void setFileNameBank(String fileNameBank) {
        this.fileNameBank = fileNameBank;
    }

    public String getUuidIncluExclu() {
        return uuidIncluExclu;
    }

    public void setUuidIncluExclu(String uuidIncluExclu) {
        this.uuidIncluExclu = uuidIncluExclu;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getBase64Bank() {
        return base64Bank;
    }

    public void setBase64Bank(String base64Bank) {
        this.base64Bank = base64Bank;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getReProcess() {
        return reProcess;
    }

    public void setReProcess(String reProcess) {
        this.reProcess = reProcess;
    }
}
