/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.com.tigo.da.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 *
 * @author Laurent G. Caceres
 */
public class DetailsReprocessDTO extends DTO {

    @ApiModelProperty(value = "clientName", dataType = "String", allowableValues = "string values available", example = "Test Name")
    public String clientName;

    @ApiModelProperty(value = "account", dataType = "String", allowableValues = "string values available", example = "9000251924")
    public String account;

    @ApiModelProperty(value = "subscriber", dataType = "String", allowableValues = "string values available", example = "99998888")
    public String subscriber;

    @ApiModelProperty(value = "cycle", dataType = "String", allowableValues = "string values available", example = "04")
    public String cycle;

    @ApiModelProperty(hidden = true)
    public String created;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


}
