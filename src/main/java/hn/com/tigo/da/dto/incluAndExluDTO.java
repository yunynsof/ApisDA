
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;

public class incluAndExluDTO {
    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty(hidden = true)
    private String numpla;
    @ApiModelProperty(value = "typeTran", dataType = "String", allowableValues = "string values available", example = "1")
    private String typeTran;
    @ApiModelProperty(value = "anexo", dataType = "String", allowableValues = "string values available", example = "63256784")
    private String anexo;
    @ApiModelProperty(value = "noCard", dataType = "String", allowableValues = "string values available", example = "938406944233")
    private String noCard;
    @ApiModelProperty(value = "cardIssueDate", dataType = "String", allowableValues = "string values available", example = "10")
    private String cardIssueDate;
    @ApiModelProperty(value = "expDate", dataType = "String", allowableValues = "string values available", example = "10")
    private String expDate;
    @ApiModelProperty(value = "status", dataType = "String", allowableValues = "string values available", example = "1")
    private int status;
    @ApiModelProperty(value = "cycle", dataType = "String", allowableValues = "string values available", example = "04")
    private String cycle;
    @ApiModelProperty(hidden = true)
    private String dateString;
    @ApiModelProperty(hidden = true)
    private String fileName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumpla() {
        return numpla;
    }

    public void setNumpla(String numpla) {
        this.numpla = numpla;
    }

    public String getTypeTran() {
        return typeTran;
    }

    public void setTypeTran(String typeTran) {
        this.typeTran = typeTran;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public String getNoCard() {
        return noCard;
    }

    public void setNoCard(String noCard) {
        this.noCard = noCard;
    }

    public String getCardIssueDate() {
        return cardIssueDate;
    }

    public void setCardIssueDate(String cardIssueDate) {
        this.cardIssueDate = cardIssueDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
