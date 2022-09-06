
package hn.com.tigo.da.dto;

import java.util.Date;

/**
 * ParametersModel.
 * class that defines the ParametersModel model to manipulate the entity to the database
 * @author Kerim Fortin
 */
public class ParametersDTO extends DTO {

	/** Attribute that determine id. */
	private long idApplication;
	/** Attribute that determine name. */
	private String name;

	/** Attribute that determine value. */
	private String value;

	/** Attribute that determine description. */
	private String description;

	/** Attribute that determine createdDate. */
	private Date createdDate;

	/**
	 * Instantiates a new parameters model.
	 */
	public ParametersDTO() {
	}

	/**
	 * @return the idApplication
	 */
	public final long getIdApplication() {
		return idApplication;
	}

	/**
	 * @param idApplication the idApplication to set
	 */
	public final void setIdApplication(long idApplication) {
		this.idApplication = idApplication;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public final void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the createdDate
	 */
	public final Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public final void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
