/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.com.tigo.da.responses;

import hn.com.tigo.da.dto.DetailsReprocessDTO;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 *
 * @author Laurent G. Caceres
 */
public class DetailsReprocessResponse {

    @ApiModelProperty(value = "code", dataType = "String", allowableValues = "string values available", example = "0")
    private String code;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "string values available", example = "Success")
    private String description;
    private List<DetailsReprocessDTO> detailsReprocess;

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

    public List<DetailsReprocessDTO> getDetailsReprocess() {
        return detailsReprocess;
    }

    public void setDetailsReprocess(List<DetailsReprocessDTO> detailsReprocess) {
        this.detailsReprocess = detailsReprocess;
    }

}
