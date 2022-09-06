
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;

public class ConfigYearsDTO extends DTO{
    @ApiModelProperty(value = "id", dataType = "Integer", allowableValues = "Integer values available", example = "1")
    private long id;
    @ApiModelProperty(value = "year", dataType = "String", allowableValues = "Integer values available", example = "2022")
    private String year;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
