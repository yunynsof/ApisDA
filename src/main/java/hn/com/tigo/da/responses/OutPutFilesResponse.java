
package hn.com.tigo.da.responses;

import hn.com.tigo.da.dto.OutputfileDTO;
import io.swagger.annotations.ApiModelProperty;

public class OutPutFilesResponse {
    @ApiModelProperty(value = "code", dataType = "String", allowableValues = "string values available", example = "0")
    private String code;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "string values available", example = "Success")
    private String description;
    private OutputfileDTO outPutFiles;

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

    public OutputfileDTO getOutPutFiles() {
        return outPutFiles;
    }

    public void setOutPutFiles(OutputfileDTO outPutFiles) {
        this.outPutFiles = outPutFiles;
    }
}
