package de.hb.model;
import javax.annotation.RegEx;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import de.hb.util.ValidationCodes;

@Entity
public class Tenant {

	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	private Long id;
	
	@Transient
	private String modelType = "tenant";
	
	private Boolean active = true;
	
	@NotNull
	@NotEmpty(message = ValidationCodes.TENANT_NAME_NOTEMPTY)
	private String name;
	
	@NotNull
	@NotEmpty(message = ValidationCodes.TENANT_STREET_NOTEMPTY)
	private String street;
	
	@NotNull
	@NotEmpty(message = ValidationCodes.TENANT_POSTCODE_NOTEMPTY)
	@Length(min = 5, max = 5, message= ValidationCodes.TENANT_POSTCODE_NOTVALID)
	@Pattern(regexp = "^[0-9]+", message = ValidationCodes.TENANT_POSTCODE_NOTVALID)
	private String postcode;
	
	@NotNull
	@NotEmpty(message = ValidationCodes.TENANT_COUNTRY_NOTEMPTY)
	private String country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public Boolean getActive() {
		return active;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	} 
	 
}
