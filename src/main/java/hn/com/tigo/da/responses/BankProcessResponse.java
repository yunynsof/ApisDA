
package hn.com.tigo.da.responses;

import hn.com.tigo.da.dto.BankProcessDTO;
import hn.com.tigo.da.dto.BankProcessModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class BankProcessResponse {
    @ApiModelProperty(value = "code", dataType = "String", allowableValues = "string values available", example = "0")
    private String code;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "string values available", example = "Success")
    private String description;
    private List<BankProcessDTO> bankProcessCycles;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BankProcessDTO> getBankProcessCycles() {
        return bankProcessCycles;
    }

    public void setBankProcessCycles(List<BankProcessDTO> bankProcessCycles) {
        this.bankProcessCycles = bankProcessCycles;
    }
}
