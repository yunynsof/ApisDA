
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author KerimFortin
 */
public class ApproveCyclesDTO extends DTO{
    @ApiModelProperty(value = "uuid", dataType = "String", allowableValues = "string values available", example = "u323d23-3245-6423-6fdfwe6")
    private String uuid;
    @ApiModelProperty(value = "idProcess", dataType = "String", allowableValues = "string values available", example = "778312aas-21345-3554-323adass")
    private String idProcess;
    @ApiModelProperty(value = "userRequest", dataType = "String", allowableValues = "string values available", example = "kfortin")
    private String userRequest;
    @ApiModelProperty(value = "dateRequestString", dataType = "String", allowableValues = "string values available", example = "05-05-2022 12:01:00")
    private String dateRequestString;
    @ApiModelProperty(value = "commentRequest", dataType = "String", allowableValues = "string values available", example = "Solicitud de aplicacion de pagos")
    private String commentRequest;
    @ApiModelProperty(value = "userApprove", dataType = "String", allowableValues = "string values available", example = "Edwin.Zambrano")
    private String userApprove;
    @ApiModelProperty(value = "dateApproveString", dataType = "String", allowableValues = "string values available", example = "05-05-2022 12:04:00")
    private String dateApproveString;
    @ApiModelProperty(value = "commentApprove", dataType = "String", allowableValues = "string values available", example = "Se aprueba la aprobacion de pagos")
    private String commentApprove;
    @ApiModelProperty(value = "status", dataType = "String", allowableValues = "string values available", example = "1")
    private int status;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(String idProcess) {
        this.idProcess = idProcess;
    }

    public String getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(String userRequest) {
        this.userRequest = userRequest;
    }

    public String getDateRequestString() {
        return dateRequestString;
    }

    public void setDateRequestString(String dateRequestString) {
        this.dateRequestString = dateRequestString;
    }

    public String getCommentRequest() {
        return commentRequest;
    }

    public void setCommentRequest(String commentRequest) {
        this.commentRequest = commentRequest;
    }

    public String getUserApprove() {
        return userApprove;
    }

    public void setUserApprove(String userApprove) {
        this.userApprove = userApprove;
    }

    public String getDateApproveString() {
        return dateApproveString;
    }

    public void setDateApproveString(String dateApproveString) {
        this.dateApproveString = dateApproveString;
    }

    public String getCommentApprove() {
        return commentApprove;
    }

    public void setCommentApprove(String commentApprove) {
        this.commentApprove = commentApprove;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
