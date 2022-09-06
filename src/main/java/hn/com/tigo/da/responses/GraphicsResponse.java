
package hn.com.tigo.da.responses;

import hn.com.tigo.da.dto.StatsCycleDTO;
import io.swagger.annotations.ApiModelProperty;

public class GraphicsResponse {
    @ApiModelProperty(value = "code", dataType = "String", allowableValues = "string values available", example = "0")
    private String code;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "string values available", example = "Success")
    private String description;
    private StatsCycleDTO statsCycle;

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

    public StatsCycleDTO getStatsCycle() {
        return statsCycle;
    }

    public void setStatsCycle(StatsCycleDTO statsCycle) {
        this.statsCycle = statsCycle;
    }
}
