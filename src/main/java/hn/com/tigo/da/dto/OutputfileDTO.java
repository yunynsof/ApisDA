
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;

public class OutputfileDTO extends DTO{
    @ApiModelProperty(value = "fileBase64", dataType = "String", allowableValues = "Integer values available", example = "B3diqewNuwwe5...")
    private String fileBase64;
    @ApiModelProperty(value = "fileName", dataType = "String", allowableValues = "Integer values available", example = "fileName.txt")
    private String fileName;
    @ApiModelProperty(value = "fileBase64Exclu", dataType = "String", allowableValues = "Integer values available", example = "B4545diqewNuwwe5...")
    private String fileBase64Exclu;
    @ApiModelProperty(value = "fileNameExclu", dataType = "String", allowableValues = "Integer values available", example = "fileName.txt")
    private String fileNameExclu;

    public String getFileBase64() {
        return fileBase64;
    }

    public void setFileBase64(String fileBase64) {
        this.fileBase64 = fileBase64;
    }

    public String getFileBase64Exclu() {
        return fileBase64Exclu;
    }

    public void setFileBase64Exclu(String fileBase64Exclu) {
        this.fileBase64Exclu = fileBase64Exclu;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileNameExclu() {
        return fileNameExclu;
    }

    public void setFileNameExclu(String fileNameExclu) {
        this.fileNameExclu = fileNameExclu;
    }
}
