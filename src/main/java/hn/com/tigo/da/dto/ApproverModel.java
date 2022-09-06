
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;

public class ApproverModel {
    @ApiModelProperty(value = "typeAprobacion", dataType = "String", allowableValues = "String values available", example = "1")
    private String typeAprobacion;
    @ApiModelProperty(value = "idProcess", dataType = "String", allowableValues = "String values available", example = "13c3f1a-5456-4567-13c3f1a13c3f1a")
    private String idProcess;
    @ApiModelProperty(value = "cycle", dataType = "String", allowableValues = "String values available", example = "20220505")
    private String cycle;
    @ApiModelProperty(value = "type", dataType = "Integer", allowableValues = "Integer values available", example = "3")
    private Long type;
    @ApiModelProperty(value = "user", dataType = "String", allowableValues = "String values available", example = "Edwin.Zambrano")
    private String user;
    @ApiModelProperty(value = "comment", dataType = "String", allowableValues = "String values available", example = "Solicitud de reproceso de ciclo.")
    private String comment;

    public String getTypeAprobacion() {
        return typeAprobacion;
    }

    public void setTypeAprobacion(String typeAprobacion) {
        this.typeAprobacion = typeAprobacion;
    }

    public String getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(String idProcess) {
        this.idProcess = idProcess;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
