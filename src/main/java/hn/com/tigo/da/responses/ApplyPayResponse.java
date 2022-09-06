package hn.com.tigo.da.responses;

import java.util.List;

import hn.com.tigo.da.dto.DABankProcessDetailPayDTO;
import io.swagger.annotations.ApiModelProperty;

public class ApplyPayResponse {

	@ApiModelProperty(value = "code", dataType = "String", allowableValues = "string values available", example = "0")
	private String code;
	@ApiModelProperty(value = "description", dataType = "String", allowableValues = "string values available", example = "Success")
	private String description;

	private List<DABankProcessDetailPayDTO> bankProccesDetailPay;

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

	public List<DABankProcessDetailPayDTO> getBankProccesDetailPay() {
		return bankProccesDetailPay;
	}

	public void setBankProccesDetailPay(List<DABankProcessDetailPayDTO> bankProccesDetailPay) {
		this.bankProccesDetailPay = bankProccesDetailPay;
	}

}
