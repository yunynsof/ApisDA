
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;

public class ConfigBanksDTO extends DTO{
    @ApiModelProperty(value = "idBank", dataType = "Integer", allowableValues = "Integer values available", example = "1000")
    private long idBank;
    @ApiModelProperty(value = "bank", dataType = "String", allowableValues = "String values available", example = "Bac")
    private String bank;

    public long getIdBank() {
        return idBank;
    }

    public void setIdBank(long idBank) {
        this.idBank = idBank;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
