
package hn.com.tigo.da.responses;

import hn.com.tigo.da.dto.CardInfoDTO;
import hn.com.tigo.da.dto.ProcessDetailPayDTO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class SelectPayResponse {
    @ApiModelProperty(value = "code", dataType = "String", allowableValues = "string values available", example = "0")
    private String code;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "string values available", example = "Success")
    private String description;
    private List<ProcessDetailPayDTO> processDetailPay;

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

    public List<ProcessDetailPayDTO> getProcessDetailPay() {
        return processDetailPay;
    }

    public void setProcessDetailPay(List<ProcessDetailPayDTO> processDetailPay) {
        this.processDetailPay = processDetailPay;
    }
}
