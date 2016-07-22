package de.hb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hb.model.Inventory;
import de.hb.model.Invoice;
import de.hb.model.Reservation;
import de.hb.repositories.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository rep;
	
	@Autowired
	private InvoiceService invoiceService;
	
	public List<Reservation> getAllReservations() {
		return (List<Reservation>) rep.findAll();
	}
	
	public Reservation initReservation(Reservation reservation) {
		return rep.save(reservation);
	}
	
	public Reservation addInventory(Reservation reservation, Inventory inventory) {
		List<Inventory> inventories = new ArrayList<Inventory>();
		inventories.addAll(reservation.getInventories());
		inventories.add(inventory);
		reservation.setInventories(inventories);
		return rep.save(reservation);
	}
	
	public Reservation removeInventory(Reservation reservation, Inventory inventory) {
		List<Inventory> inventories = new ArrayList<Inventory>();
		inventories.addAll(reservation.getInventories());
		inventories.remove(inventory);
		reservation.setInventories(inventories);
		return rep.save(reservation);
	}
	
	public Reservation endReservation(Reservation reservation) {
		Invoice invoice = invoiceService.createInvoice(reservation.getInventories());
		reservation.setInvoice(invoice);
		return rep.save(reservation);
	}
	
	public Reservation cancelReservation(Reservation reservation) {
		return rep.save(reservation);
	}
}
