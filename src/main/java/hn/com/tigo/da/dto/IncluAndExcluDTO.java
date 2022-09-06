
package hn.com.tigo.da.dto;

import hn.com.tigo.da.entity.*;
import hn.com.tigo.da.dto.DTO;
import io.swagger.annotations.ApiModelProperty;
import java.sql.Date;

public class IncluAndExcluDTO extends DTO{
    @ApiModelProperty(hidden = true)
    private String id;
    @ApiModelProperty(hidden = true)
    private String numpla;
    @ApiModelProperty(value = "typeTran", dataType = "String", allowableValues = "string values available", example = "1")
    private String type_tran;
    @ApiModelProperty(value = "anexo", dataType = "String", allowableValues = "string values available", example = "63256784")
    private String anexo;
    @ApiModelProperty(value = "noCard", dataType = "String", allowableValues = "string values available", example = "938406944233")
    private String noCard;
    @ApiModelProperty(value = "cardIssueDate", dataType = "String", allowableValues = "string values available", example = "10")
    private String cardIsueDate;
    @ApiModelProperty(value = "expDate", dataType = "String", allowableValues = "string values available", example = "10")
    private String expDate;
    @ApiModelProperty(value = "status", dataType = "String", allowableValues = "string values available", example = "1")
    private long status;
    @ApiModelProperty(value = "cycle", dataType = "String", allowableValues = "string values available", example = "04")
    private String cycle;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "string values available", example = "06-04-2022 12:01:03")
    private String createdString;
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

    public String getType_tran() {
        return type_tran;
    }

    public void setType_tran(String type_tran) {
        this.type_tran = type_tran;
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

    public String getCardIsueDate() {
        return cardIsueDate;
    }

    public void setCardIsueDate(String cardIsueDate) {
        this.cardIsueDate = cardIsueDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCreatedString() {
        return createdString;
    }

    public void setCreatedString(String createdString) {
        this.createdString = createdString;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
