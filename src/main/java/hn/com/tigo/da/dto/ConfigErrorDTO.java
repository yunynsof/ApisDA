
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;

public class ConfigErrorDTO extends DTO{
    @ApiModelProperty(value = "bankId", dataType = "String", allowableValues = "String values available", example = "1000")
    private String bankId;
    @ApiModelProperty(value = "bankCode", dataType = "String", allowableValues = "String values available", example = "01")
    private String bankCode;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "String values available", example = "Datos del afiliado erróneos")
    private String description;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
