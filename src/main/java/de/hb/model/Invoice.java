package de.hb.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
public class Invoice {

	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Type(type="timestamp")
	private Date date;
	
	@OneToMany
	private List<InvoicePosition> positions;
	
	private int brutto;
	
	private int netto;

	public Invoice() {
		
	}
	
	public Invoice(List<InvoicePosition> positions) {
		this.positions = positions;
		this.date = new Date();
		
		int netto = 0;
		
		for (int i = 0; i < positions.size(); i++) {
			netto = netto + positions.get(i).getBrutto();
		}
		
		this.netto = netto;
		this.brutto = (int) (netto * (1.19));
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<InvoicePosition> getPositions() {
		return positions;
	}

	public void setPositions(List<InvoicePosition> positions) {
		this.positions = positions;
	}

	public int getBrutto() {
		return brutto;
	}

	public void setBrutto(int brutto) {
		this.brutto = brutto;
	}

	public int getNetto() {
		return netto;
	}

	public void setNetto(int netto) {
		this.netto = netto;
	}
	
}
