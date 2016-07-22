package de.hb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@NotNull
	@RestResource(exported = false)
	private Tenant tenant;
	
	@NotNull
	@Type(type="timestamp")
	private Date start;
	
	@NotNull
	@Type(type="timestamp")
	private Date end;
	
	@OneToOne
	private Invoice invoice;
	
	@OneToMany
	private List<Inventory> inventories = new ArrayList<Inventory>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}
	
}
