
package hn.com.tigo.da.responses;

import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;

public class LogErrorResponse {
    @ApiModelProperty(value = "code", dataType = "String", allowableValues = "string values available", example = "0")
    private String code;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "string values available", example = "Success")
    private String description;
    private ArrayList<String> logError;
    

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

    public ArrayList<String> getLogError() {
        return logError;
    }

    public void setLogError(ArrayList<String> logError) {
        this.logError = logError;
    }
}
