package de.hb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InvoicePosition {

	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String description;
	
	private int taxRate = 19;
	
	private int netto;
	
	private int brutto;
	
	public InvoicePosition(Inventory inventory) {
		this.name = inventory.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(int taxRate) {
		this.taxRate = taxRate;
	}

	public int getNetto() {
		return netto;
	}

	public void setNetto(int netto) {
		this.netto = netto;
	}

	public int getBrutto() {
		return brutto;
	}

	public void setBrutto(int brutto) {
		this.brutto = brutto;
	}
	
}
