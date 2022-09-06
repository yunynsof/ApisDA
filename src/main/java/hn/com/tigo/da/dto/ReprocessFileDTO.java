package hn.com.tigo.da.dto;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


public class ReprocessFileDTO extends DTO {
    @ApiModelProperty(hidden = true)
    /** Attribute that determine id. */
    private String id; // Tipo DB: VARCHAR2

    @ApiModelProperty(value = "fileName", dataType = "String", allowableValues = "string values available", example = "Bac_20220605.xlsx")
    /** Attribute that determine fileName. */
    private String fileName; // Tipo DB: VARCHAR2

    @ApiModelProperty(hidden = true)
    /** Attribute that determine created. */
    private Date created; // Tipo DB: VARCHAR2

    @ApiModelProperty(value = "fileB64", dataType = "String", allowableValues = "string values available", example = "UEsDBBs9fjsddasd...")
    /** Attribute that determine fileB64. */
    private String fileB64; // Tipo DB: VARCHAR2

    @ApiModelProperty(value = "user", dataType = "String", allowableValues = "string values available", example = "Edwin.Zambrano")
    /** Attribute that determine user. */
    private String user; // Tipo DB: VARCHAR2
    
    @ApiModelProperty(value = "cycle", dataType = "String", allowableValues = "string values available", example = "20220605")
    /** Attribute that determine cycle. */
    private String cycle; // Tipo DB: VARCHAR2
    
    @ApiModelProperty(value = "numRegister", dataType = "Number", allowableValues = "Number values available", example = "99")
    private long numRegister;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getFileB64() {
        return fileB64;
    }

    public void setFileB64(String fileB64) {
        this.fileB64 = fileB64;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public long getNumRegister() {
        return numRegister;
    }

    public void setNumRegister(long numRegister) {
        this.numRegister = numRegister;
    }
}
