
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;


public class ConfigStatusDTO extends DTO{
    @ApiModelProperty(value = "code", dataType = "Integer", allowableValues = "Integer values available", example = "4")
    private long code;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "String values available", example = "Pagos Aplicados")
    private String description;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
