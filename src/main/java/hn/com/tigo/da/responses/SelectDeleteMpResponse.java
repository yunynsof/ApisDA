
package hn.com.tigo.da.responses;

import hn.com.tigo.da.dto.DeleteMpDTO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class SelectDeleteMpResponse {
    @ApiModelProperty(value = "code", dataType = "String", allowableValues = "string values available", example = "0")
    private String code;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "string values available", example = "Success")
    private String description;
    private List<DeleteMpDTO> SeletectDeleteMp;

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

    public List<DeleteMpDTO> getSeletectDeleteMp() {
        return SeletectDeleteMp;
    }

    public void setSeletectDeleteMp(List<DeleteMpDTO> SeletectDeleteMp) {
        this.SeletectDeleteMp = SeletectDeleteMp;
    }
}
