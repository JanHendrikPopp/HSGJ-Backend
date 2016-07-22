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
public class Inventory {

	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	private Long id;
	
	@Transient
	private String modelType = "inventory";
	
	private Boolean active = true;
	
	@NotNull
	@NotEmpty(message = ValidationCodes.INVENTORY_NAME_NOTEMPTY)
	private String name;
	
	@NotNull(message = ValidationCodes.INVENTORY_TYPE_NOTEMPTY)
	private InventoryType type;

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

	public InventoryType getType() {
		return type;
	}

	public void setType(InventoryType type) {
		this.type = type;
	}

}
