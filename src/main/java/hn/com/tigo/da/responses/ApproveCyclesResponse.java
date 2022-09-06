
package hn.com.tigo.da.responses;

import hn.com.tigo.da.dto.ApproveCyclesDTO;
import io.swagger.annotations.ApiModelProperty;

public class ApproveCyclesResponse {
    @ApiModelProperty(value = "code", dataType = "String", allowableValues = "string values available", example = "0")
    private String code;
    @ApiModelProperty(value = "description", dataType = "String", allowableValues = "string values available", example = "Success")
    private String description;
    private ApproveCyclesDTO solicitudAprobacion;
    @ApiModelProperty(value = "permiso", dataType = "Boolean", allowableValues = "boolean values available", example = "true")
    private boolean permiso;

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

    public ApproveCyclesDTO getSolicitudAprobacion() {
        return solicitudAprobacion;
    }

    public void setSolicitudAprobacion(ApproveCyclesDTO solicitudAprobacion) {
        this.solicitudAprobacion = solicitudAprobacion;
    }

    public boolean isPermiso() {
        return permiso;
    }

    public void setPermiso(boolean permiso) {
        this.permiso = permiso;
    }
}
