
package hn.com.tigo.da.dto;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

public class CardInfoDTO extends DTO {
    @ApiModelProperty(hidden = true)
    public String id;
    @ApiModelProperty(value = "name", dataType = "String", allowableValues = "string values available", example = "Test Name")
    public String name;
    @ApiModelProperty(value = "noCard", dataType = "String", allowableValues = "string values available", example = "04912496402322")
    public String noCard;
    @ApiModelProperty(value = "typeCard", dataType = "String", allowableValues = "string values available", example = "Diners Club")
    public String typeCard;
    @ApiModelProperty(value = "modalityCard", dataType = "String", allowableValues = "string values available", example = "C")
    public String modalityCard;
    @ApiModelProperty(value = "year", dataType = "String", allowableValues = "string values available", example = "2023")
    public String year;
    @ApiModelProperty(value = "month", dataType = "String", allowableValues = "string values available", example = "06")
    public String month;
    @ApiModelProperty(value = "issuingBank", dataType = "String", allowableValues = "string values available", example = "Bac")
    public String issuingBank;
    @ApiModelProperty(value = "processorBank", dataType = "String", allowableValues = "string values available", example = "Bac")
    public String processorBank;
    @ApiModelProperty(value = "groupPayment", dataType = "String", allowableValues = "string values available", example = "0")
    public String groupPayment;
    @ApiModelProperty(value = "cycle", dataType = "String", allowableValues = "string values available", example = "04")
    public String cycle;
    @ApiModelProperty(hidden = true)
    public Date created;
    @ApiModelProperty(hidden = true)
    public String dateString;
    @ApiModelProperty(value = "status", dataType = "String", allowableValues = "string values available", example = "1")
    public String status;
    @ApiModelProperty(value = "acctCode", dataType = "String", allowableValues = "string values available", example = "4000165999")
    public String acctCode;
    @ApiModelProperty(value = "subscriberId", dataType = "String", allowableValues = "string values available", example = "99998888")
    public String subscriberId;
    @ApiModelProperty
    public String[] email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoCard() {
        return noCard;
    }

    public void setNoCard(String noCard) {
        this.noCard = noCard;
    }

    public String getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(String typeCard) {
        this.typeCard = typeCard;
    }

    public String getModalityCard() {
        return modalityCard;
    }

    public void setModalityCard(String modalityCard) {
        this.modalityCard = modalityCard;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getIssuingBank() {
        return issuingBank;
    }

    public void setIssuingBank(String issuingBank) {
        this.issuingBank = issuingBank;
    }

    public String getProcessorBank() {
        return processorBank;
    }

    public void setProcessorBank(String processorBank) {
        this.processorBank = processorBank;
    }

    public String getGroupPayment() {
        return groupPayment;
    }

    public void setGroupPayment(String groupPayment) {
        this.groupPayment = groupPayment;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }
}
