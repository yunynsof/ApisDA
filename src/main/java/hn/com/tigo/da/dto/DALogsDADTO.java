package hn.com.tigo.da.dto;

import java.util.Date;


public class DALogsDADTO extends DTO {

	private String id;
	private int typeError;
	private Date created;
	private String detailError;
	private String reference;
	private String srt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTypeError() {
		return typeError;
	}

	public void setTypeError(int typeError) {
		this.typeError = typeError;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getDetailError() {
		return detailError;
	}

	public void setDetailError(String detailError) {
		this.detailError = detailError;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getSrt() {
		return srt;
	}

	public void setSrt(String srt) {
		this.srt = srt;
	}

}
