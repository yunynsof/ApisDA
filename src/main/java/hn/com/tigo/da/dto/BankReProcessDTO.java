
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;

public class BankReProcessDTO extends DTO{
    @ApiModelProperty(value = "id", dataType = "String", allowableValues = "Integer values available", example = "1000")
    private String id;
    @ApiModelProperty(value = "status", dataType = "String", allowableValues = "Integer values available", example = "0")
    private long status;
    @ApiModelProperty(value = "base64Bank", dataType = "String", allowableValues = "String values available", example = "B4wdxasd8434...")
    private String base64Bank;
    @ApiModelProperty(value = "fileNameBank", dataType = "String", allowableValues = "String values available", example = "Bac3395sdf.txt")
    private String fileNameBank;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getBase64Bank() {
        return base64Bank;
    }

    public void setBase64Bank(String base64Bank) {
        this.base64Bank = base64Bank;
    }

    public String getFileNameBank() {
        return fileNameBank;
    }

    public void setFileNameBank(String fileNameBank) {
        this.fileNameBank = fileNameBank;
    }
}
