
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;

public class ConfigCyclesDTO extends DTO{
    @ApiModelProperty(value = "cycleCbs", dataType = "Integer", allowableValues = "Integer values available", example = "26")
    private long cycleCbs;
    @ApiModelProperty(value = "bu", dataType = "String", allowableValues = "string values available", example = "Movil")
    private String bu;
    @ApiModelProperty(value = "cycleBac", dataType = "Integer", allowableValues = "Integer values available", example = "64")
    private long cycleBac;
    @ApiModelProperty(value = "cycleAs", dataType = "String", allowableValues = "string values available", example = "V")
    private String cycleAs;

    public long getCycleCbs() {
        return cycleCbs;
    }

    public void setCycleCbs(long cycleCbs) {
        this.cycleCbs = cycleCbs;
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu;
    }

    public long getCycleBac() {
        return cycleBac;
    }

    public void setCycleBac(long cycleBac) {
        this.cycleBac = cycleBac;
    }

    public String getCycleAs() {
        return cycleAs;
    }

    public void setCycleAs(String cycleAs) {
        this.cycleAs = cycleAs;
    }
}
