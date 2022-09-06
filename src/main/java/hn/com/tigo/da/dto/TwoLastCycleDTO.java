
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;

public class TwoLastCycleDTO extends DTO{
    @ApiModelProperty(value = "cycle", dataType = "String", allowableValues = "String values available", example = "1000")
    private String cycle;
    @ApiModelProperty(value = "idCycle", dataType = "String", allowableValues = "String values available", example = "1000")
    private String idCycle;
    @ApiModelProperty(value = "fileName", dataType = "String", allowableValues = "String values available", example = "Bac_20220504_0001.xlsx")
    private String fileName;

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getIdCycle() {
        return idCycle;
    }

    public void setIdCycle(String idCycle) {
        this.idCycle = idCycle;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
