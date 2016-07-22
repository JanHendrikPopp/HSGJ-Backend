package de.hb.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import de.hb.util.ValidationCodes;


@Entity
public class Contact {

	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	private Long id;
	
	@Transient
	private String modelType = "contact";
	
	private Boolean active = true;
	
	@NotNull
	@NotEmpty(message = ValidationCodes.CONTACT_FIRSTNAME_NOTEMPTY)
	private String firstname;
	
	@NotNull
	@NotEmpty(message = ValidationCodes.CONTACT_LASTNAME_NOTEMPTY)
	private String lastname;
	
	@NotNull
	@NotEmpty(message = ValidationCodes.CONTACT_PHONE_NOTEMPTY)
	private String phone;
	
	@NotNull
	@NotEmpty(message = ValidationCodes.CONTACT_PHONE_NOTEMPTY)
	private String mail;
	
	@NotNull(message = ValidationCodes.CONTACT_TYPE_NOTEMPTY)
	private ContactType type;

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

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public ContactType getType() {
		return type;
	}

	public void setType(ContactType type) {
		this.type = type;
	}
	
}
