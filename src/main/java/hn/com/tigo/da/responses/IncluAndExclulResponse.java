
package hn.com.tigo.da.responses;

import hn.com.tigo.da.dto.IncluAndExcluDTO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class IncluAndExclulResponse {
    @ApiModelProperty(value = "code", dataType = "String", allowableValues = "string values available", example = "0")
    private String code;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "string values available", example = "Success")
    private String description;
    private List<IncluAndExcluDTO> incluAndExclu;

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

    public List<IncluAndExcluDTO> getIncluAndExclu() {
        return incluAndExclu;
    }

    public void setIncluAndExclu(List<IncluAndExcluDTO> incluAndExclu) {
        this.incluAndExclu = incluAndExclu;
    }
}
